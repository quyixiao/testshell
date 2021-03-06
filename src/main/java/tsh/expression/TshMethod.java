/*****************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                *
 * or more contributor license agreements.  See the NOTICE file              *
 * distributed with this work for additional information                     *
 * regarding copyright ownership.  The ASF licenses this file                *
 * to you under the Apache License, Version 2.0 (the                         *
 * "License"); you may not use this file except in compliance                *
 * with the License.  You may obtain a copy of the License at                *
 *                                                                           *
 *     http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing,                *
 * software distributed under the License is distributed on an               *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                    *
 * KIND, either express or implied.  See the License for the                 *
 * specific language governing permissions and limitations                   *
 * under the License.                                                        *
 *                                                                           *
 *                                                                           *
 * This file is part of the BeanShell Java Scripting distribution.           *
 * Documentation and updates may be found at http://www.beanshell.org/       *
 * Patrick Niemeyer (pat@pat.net)                                            *
 * Author of Learning Java, O'Reilly & Associates                            *
 *                                                                           *
 *****************************************************************************/


package tsh.expression;


import tsh.*;
import tsh.constant.ParserConstants;
import tsh.entity.TMethodData;
import tsh.entity.TVar;
import tsh.exception.EvalError;
import tsh.exception.ReflectError;
import tsh.exception.TargetError;
import tsh.exception.UtilEvalError;
import tsh.util.Reflect;
import tsh.util.TStringUtil;
import tsh.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents an instance of a bsh method declaration in a particular
 * namespace.  This is a thin wrapper around the BSHMethodDeclaration
 * with a pointer to the declaring namespace.
 * <p>
 * <p>
 * When a method is located in a subordinate namespace or invoked from an
 * arbitrary namespace it must nonetheless execute with its 'super' as the
 * context in which it was declared.
 * <p/>
 */
/*
	Note: this method incorrectly caches the method structure.  It needs to
	be cleared when the classloader changes.
*/
public class TshMethod implements ParserConstants, java.io.Serializable {
    /*
        This is the namespace in which the method is set.
        It is a back-reference for the node, which needs to execute under this
        namespace.  It is not necessary to declare this transient, because
        we can only be saved as part of our namespace anyway... (currently).
    */
    NameSpace declaringNameSpace;

    public String name;
    private Class creturnType;

    // Arguments
    private String[] paramNames;
    private int numArgs;
    private Class[] cparamTypes;

    // Scripted method body
    TSHBlock methodBody;

    // Java Method, for a BshObject that delegates to a real Java method
    private Method javaMethod;
    private Object javaObject;
    public TVar[] defaultValues;
    public TMethodData methodData;


    public TshMethod(TSHMethodDeclaration method, NameSpace declaringNameSpace, TMethodData methodData) {
        this(method.methodName, Object.class, method.paramsNode.getParamNames(), method.paramsNode.paramTypes, method.blockNode,
                declaringNameSpace, method.paramsNode.getParamDefaultValues(), methodData);
    }

    public TshMethod(String name, Class returnType, String[] paramNames, Class[] paramTypes, TSHBlock methodBody,
                     NameSpace declaringNameSpace, TVar[] defaultValues) {
        this(name, returnType, paramNames, paramTypes, methodBody, declaringNameSpace, defaultValues, null);
    }

    public TshMethod(String name, Class returnType, String[] paramNames, Class[] paramTypes, TSHBlock methodBody,
                     NameSpace declaringNameSpace, TVar[] defaultValues, TMethodData methodData) {
        this.name = name;
        this.creturnType = returnType;
        this.paramNames = paramNames;
        if (paramNames != null)
            this.numArgs = paramNames.length;
        this.cparamTypes = paramTypes;
        this.methodBody = methodBody;
        this.declaringNameSpace = declaringNameSpace;
        this.defaultValues = defaultValues;
        this.methodData = methodData;
    }

    /*
        Create a BshMethod that delegates to a real Java method upon invocation.
        This is used to represent imported object methods.
    */
    public TshMethod(Method method, Object object) {
        this(method.getName(), method.getReturnType(), null/*paramNames*/,
                method.getParameterTypes(), null/*method.block*/,
                null/*declaringNameSpace*/, null);

        this.javaMethod = method;
        this.javaObject = object;
    }


    public Class[] getParameterTypes() {
        return cparamTypes;
    }

    public String[] getParameterNames() {
        return paramNames;
    }

    public Class getReturnType() {
        return creturnType;
    }


    public String getName() {
        return name;
    }

    public Object invokeNew(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo) throws EvalError {
        return invokeImplNew(argValues, interpreter, callstack, callerInfo, false);
    }


    public Object invokeNew(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace) throws EvalError {
        return invokeImplNew(argValues, interpreter, callstack, callerInfo, overrideNameSpace);
    }

    private Object invokeImplNew(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace) throws EvalError {
        Class returnType = getReturnType();

        if (callstack == null)
            callstack = new CallStack(declaringNameSpace);

        if (argValues == null)
            argValues = new Object[]{};


        NameSpace localNameSpace;
        if (overrideNameSpace) {
            localNameSpace = callstack.top();
        } else {
            localNameSpace = new NameSpace(declaringNameSpace, name);
            localNameSpace.isMethod = true;
        }
        localNameSpace.setNode(callerInfo);

        List<TVar> dVs = getSortArgValues(defaultValues);
        List<Object> args = arrayToList(argValues);
        for (int i = 0; i < numArgs; i++) {
            try {
                String paramName = dVs.get(i).getName();
                Object argValue = null;
                TVar tVar = dVs.get(i);
                if (Utils.eq(tVar.getKind(), STAR)) {
                    List<Object> list = new ArrayList<>();
                    for (int j = 0; j < args.size(); j++) {
                        Object o = args.get(j);
                        if (!(o instanceof TVar)) {
                            list.add(o);
                        }
                    }
                    argValue = list;
                } else if (Utils.eq(tVar.getKind(), SSTAR)) {
                    Map<String, Object> map = new LinkedHashMap<>();
                    for (Object o : args) {
                        if (o instanceof TVar) {
                            map.put(((TVar) o).getName(), ((TVar) o).getValue());
                        }
                    }
                    argValue = map;
                } else {
                    int k = 0;
                    for (int j = 0; j < args.size(); j++) {
                        Object o = args.get(j);
                        if (o instanceof TVar) {
                            if (Utils.eq(((TVar) o).getName(), paramName)) {
                                argValue = ((TVar) o).getValue();
                                k = j;
                                break;
                            }
                        }
                    }
                    if (argValue == null) {
                        for (int j = 0; j < args.size(); j++) {
                            Object o = args.get(j);
                            if (!(o instanceof TVar)) {
                                argValue = o;
                                k = j;
                                break;
                            }
                        }
                    }
                    if (argValue != null) {                 //移除掉 args
                        args.remove(k);
                    }
                }
                if (argValue == null) {
                    argValue = dVs.get(i).getValue();                //设置默认值
                }
                if (argValue instanceof TshMethod) {
                    localNameSpace.setMethod(paramName, (TshMethod) argValue);
                } else {
                    localNameSpace.setLocalVariable(paramName, argValue, interpreter.getStrictJava());
                }
            } catch (UtilEvalError e3) {
                throw e3.toEvalError(callerInfo, callstack);
            }
        }

        if (!overrideNameSpace)
            callstack.push(localNameSpace);

        Object ret = methodBody.eval(callstack, interpreter, true/*override*/);
        CallStack returnStack = callstack.copy();

        if (!overrideNameSpace)
            callstack.pop();

        ReturnControl retControl = null;
        if (ret instanceof ReturnControl) {
            retControl = (ReturnControl) ret;

            if (Utils.eq(retControl.kind, retControl.RETURN))
                ret = ((ReturnControl) ret).value;
            else
                throw new EvalError("'continue' or 'break' in method body", retControl.returnPoint, returnStack);

            if (returnType == Void.TYPE && ret != Primitive.VOID)
                throw new EvalError("Cannot return value from void method", retControl.returnPoint, returnStack);
        }

        return ret;
    }


    private List<TVar> getSortArgValues(TVar[] argValues) {
        List<TVar> list = new ArrayList<>();
        if (argValues != null && argValues.length > 0) {
            int i = 0;
            int flag1 = -1;
            int flag2 = -1;
            for (TVar o : argValues) {
                String kind = o.getKind();
                if (TStringUtil.isNotBlank(kind)) {
                    if (Utils.eq(kind, STAR)) {
                        flag1 = i;
                    } else if (Utils.eq(kind, SSTAR)) {
                        flag2 = i;
                    }
                } else {
                    list.add(o);
                }
                i++;
            }
            if (flag1 > -1) {                //将 * args
                list.add(argValues[flag1]);
            }
            if (flag2 > -1) {
                list.add(argValues[flag2]);
            }
        }
        return list;
    }


    public List<Object> arrayToList(Object[] arr) {
        List<Object> list = new ArrayList<>();
        if (arr != null && arr.length > 0) {
            for (Object a : arr) {
                list.add(a);
            }
        }
        return list;
    }


    public Object invoke(Object[] argValues, Interpreter interpreter) throws EvalError {
        return invoke(argValues, interpreter, null, null, false);
    }

    public Object invoke(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo) throws EvalError {
        return invoke(argValues, interpreter, callstack, callerInfo, false);
    }

    Object invoke(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace)
            throws EvalError {
        if (argValues != null)
            for (int i = 0; i < argValues.length; i++) {
                if (argValues[i] == null) {
                    // quyixiao modify null
                    // throw new Error("HERE!");
                }
            }
        if (javaMethod != null) {
            try {
                return Reflect.invokeMethod(javaMethod, javaObject, argValues);
            } catch (ReflectError e) {
                throw new EvalError("Error invoking Java method: " + e, callerInfo, callstack);
            } catch (InvocationTargetException e2) {
                throw new TargetError("Exception invoking imported object method.", e2, callerInfo, callstack, true/*isNative*/);
            }
        }

        return invokeImpl(argValues, interpreter, callstack, callerInfo, overrideNameSpace);
    }

    private Object invokeImpl(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace) throws EvalError {
        Class returnType = getReturnType();
        Class[] paramTypes = getParameterTypes();

        if (callstack == null)
            callstack = new CallStack(declaringNameSpace);

        if (argValues == null)
            argValues = new Object[]{};


        NameSpace localNameSpace;
        if (overrideNameSpace) {
            localNameSpace = callstack.top();
        } else {
            localNameSpace = new NameSpace(declaringNameSpace, name);
            localNameSpace.isMethod = true;
        }
        localNameSpace.setNode(callerInfo);

        for (int i = 0; i < numArgs; i++) {
            try {
                localNameSpace.setLocalVariable(paramNames[i], argValues[i], interpreter.getStrictJava());
            } catch (UtilEvalError e3) {
                throw e3.toEvalError(callerInfo, callstack);
            }
        }

        if (!overrideNameSpace)
            callstack.push(localNameSpace);

        Object ret = methodBody.eval(callstack, interpreter, true/*override*/);

        CallStack returnStack = callstack.copy();

        if (!overrideNameSpace)
            callstack.pop();

        ReturnControl retControl = null;
        if (ret instanceof ReturnControl) {
            retControl = (ReturnControl) ret;

            if (Utils.eq(retControl.kind, retControl.RETURN))
                ret = ((ReturnControl) ret).value;
            else
                throw new EvalError("'continue' or 'break' in method body", retControl.returnPoint, returnStack);

            if (returnType == Void.TYPE && ret != Primitive.VOID)
                throw new EvalError("Cannot return value from void method", retControl.returnPoint, returnStack);
        }

        if (returnType != null) {
            if (returnType == Void.TYPE)
                return Primitive.VOID;

            try {
                ret = Types.castObject(ret, returnType, Types.ASSIGNMENT);
            } catch (UtilEvalError e) {
                SimpleNode node = callerInfo;
                if (retControl != null)
                    node = retControl.returnPoint;
                throw e.toEvalError("Incorrect type returned from method: " + name + e.getMessage(), node, callstack);
            }
        }

        return ret;
    }

    public boolean hasModifier(String name) {

        return false;
    }

    public String toString() {
        return "Scripted Method: "
                + TStringUtil.methodString(name, getParameterTypes());
    }


}
