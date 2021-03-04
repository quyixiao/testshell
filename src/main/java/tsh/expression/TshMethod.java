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
import tsh.exception.EvalError;
import tsh.exception.ReflectError;
import tsh.exception.TargetError;
import tsh.exception.UtilEvalError;
import tsh.util.Reflect;
import tsh.util.StringUtil;
import tsh.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
public class TshMethod implements java.io.Serializable {
    /*
        This is the namespace in which the method is set.
        It is a back-reference for the node, which needs to execute under this
        namespace.  It is not necessary to declare this transient, because
        we can only be saved as part of our namespace anyway... (currently).
    */
    NameSpace declaringNameSpace;


    private String name;
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

    public TshMethod(TSHMethodDeclaration method, NameSpace declaringNameSpace, Object modifiers) {
        this(method.methodName, null, method.paramsNode.getParamNames(), null, method.blockNode, declaringNameSpace, modifiers);
    }


    public TshMethod(String name, Class returnType, String[] paramNames, Class[] paramTypes, TSHBlock methodBody, NameSpace declaringNameSpace, Object modifiers) {
        this.name = name;
        this.creturnType = returnType;
        this.paramNames = paramNames;
        if (paramNames != null)
            this.numArgs = paramNames.length;
        this.cparamTypes = paramTypes;
        this.methodBody = methodBody;
        this.declaringNameSpace = declaringNameSpace;
    }

    /*
        Create a BshMethod that delegates to a real Java method upon invocation.
        This is used to represent imported object methods.
    */
    public TshMethod(Method method, Object object) {
        this(method.getName(), method.getReturnType(), null/*paramNames*/,
                method.getParameterTypes(), null/*method.block*/,
                null/*declaringNameSpace*/, null/*modifiers*/);

        this.javaMethod = method;
        this.javaObject = object;
    }


    /**
     * Get the argument types of this method.
     * loosely typed (untyped) arguments will be represented by null argument
     * types.
     */
	/*
		Note: bshmethod needs to re-evaluate arg types here
		This is broken.
	*/
    public Class[] getParameterTypes() {
        return cparamTypes;
    }

    public String[] getParameterNames() {
        return paramNames;
    }

    /**
     * Get the return type of the method.
     *
     * @return Returns null for a loosely typed return value,
     * Void.TYPE for a void return type, or the Class of the type.
     */
	/*
		Note: bshmethod needs to re-evaluate the method return type here.
		This is broken.
	*/
    public Class getReturnType() {
        return creturnType;
    }


    public String getName() {
        return name;
    }

    /**
     * Invoke the declared method with the specified arguments and interpreter
     * reference.  This is the simplest form of invoke() for BshMethod
     * intended to be used in reflective style access to bsh scripts.
     */
    public Object invoke(
            Object[] argValues, Interpreter interpreter)
            throws EvalError {
        return invoke(argValues, interpreter, null, null, false);
    }

    /**
     * Invoke the bsh method with the specified args, interpreter ref,
     * and callstack.
     * callerInfo is the node representing the method invocation
     * It is used primarily for debugging in order to provide access to the
     * text of the construct that invoked the method through the namespace.
     *
     * @param callerInfo is the BeanShell AST node representing the method
     *                   invocation.  It is used to print the line number and text of
     *                   errors in EvalError exceptions.  If the node is null here error
     *                   messages may not be able to point to the precise location and text
     *                   of the error.
     * @param callstack  is the callstack.  If callstack is null a new one
     *                   will be created with the declaring namespace of the method on top
     *                   of the stack (i.e. it will look for purposes of the method
     *                   invocation like the method call occurred in the declaring
     *                   (enclosing) namespace in which the method is defined).
     */
    public Object invoke(
            Object[] argValues, Interpreter interpreter, CallStack callstack,
            SimpleNode callerInfo)
            throws EvalError {
        return invoke(argValues, interpreter, callstack, callerInfo, false);
    }

    /**
     * Invoke the bsh method with the specified args, interpreter ref,
     * and callstack.
     * callerInfo is the node representing the method invocation
     * It is used primarily for debugging in order to provide access to the
     * text of the construct that invoked the method through the namespace.
     *
     * @param callerInfo        is the BeanShell AST node representing the method
     *                          invocation.  It is used to print the line number and text of
     *                          errors in EvalError exceptions.  If the node is null here error
     *                          messages may not be able to point to the precise location and text
     *                          of the error.
     * @param callstack         is the callstack.  If callstack is null a new one
     *                          will be created with the declaring namespace of the method on top
     *                          of the stack (i.e. it will look for purposes of the method
     *                          invocation like the method call occurred in the declaring
     *                          (enclosing) namespace in which the method is defined).
     * @param overrideNameSpace When true the method is executed in the namespace on the top of the
     *                          stack instead of creating its own local namespace.  This allows it
     *                          to be used in constructors.
     */
    Object invoke(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace)
            throws EvalError {
        if (argValues != null)
            for (int i = 0; i < argValues.length; i++) {
                if (argValues[i] == null) {
                    throw new Error("HERE!");
                }
            }
        if (javaMethod != null) {
            try {
                return Reflect.invokeMethod(javaMethod, javaObject, argValues);
            } catch (ReflectError e) {
                throw new EvalError("Error invoking Java method: " + e, callerInfo, callstack);
            } catch (InvocationTargetException e2) {
                throw new TargetError("Exception invoking imported object method.",
                        e2, callerInfo, callstack, true/*isNative*/);
            }
        }

        return invokeImpl(argValues, interpreter, callstack, callerInfo, overrideNameSpace);
    }

    private Object invokeImpl(Object[] argValues, Interpreter interpreter, CallStack callstack,SimpleNode callerInfo, boolean overrideNameSpace)
            throws EvalError {
        Class returnType = getReturnType();
        Class[] paramTypes = getParameterTypes();

        // If null callstack
        if (callstack == null)
            callstack = new CallStack(declaringNameSpace);

        if (argValues == null)
            argValues = new Object[]{};

        // Cardinality (number of args) mismatch
        if (argValues.length != numArgs) {
            throw new EvalError("Wrong number of arguments for local method: " + name, callerInfo, callstack);
        }

        // Make the local namespace for the method invocation
        NameSpace localNameSpace;
        if (overrideNameSpace)
            localNameSpace = callstack.top();
        else {
            localNameSpace = new NameSpace(declaringNameSpace, name);
            localNameSpace.isMethod = true;
        }
        // should we do this for both cases above?
        localNameSpace.setNode(callerInfo);

        // set the method parameters in the local namespace
        for (int i = 0; i < numArgs; i++) {
            try {
                localNameSpace.setLocalVariable(paramNames[i], argValues[i],interpreter.getStrictJava());
            } catch (UtilEvalError e3) {
                throw e3.toEvalError(callerInfo, callstack);
            }

        }

        // Push the new namespace on the call stack
        if (!overrideNameSpace)
            callstack.push(localNameSpace);

        // Invoke the block, overriding namespace with localNameSpace
        Object ret = methodBody.eval(callstack, interpreter, true/*override*/);

        // save the callstack including the called method, just for error mess
        CallStack returnStack = callstack.copy();

        // Get back to caller namespace
        if (!overrideNameSpace)
            callstack.pop();

        ReturnControl retControl = null;
        if (ret instanceof ReturnControl) {
            retControl = (ReturnControl) ret;

            // Method body can only use 'return' statement type return control.
            if (Utils.eq(retControl.kind, retControl.RETURN))
                ret = ((ReturnControl) ret).value;
            else
                // retControl.returnPoint is the Node of the return statement
                throw new EvalError("'continue' or 'break' in method body", retControl.returnPoint, returnStack);

            // Check for explicit return of value from void method type.
            // retControl.returnPoint is the Node of the return statement
            if (returnType == Void.TYPE && ret != Primitive.VOID)
                throw new EvalError("Cannot return value from void method", retControl.returnPoint, returnStack);
        }

        if (returnType != null) {
            // If return type void, return void as the value.
            if (returnType == Void.TYPE)
                return Primitive.VOID;

            // return type is a class
            try {
                ret =
                        // Types.getAssignableForm( ret, (Class)returnType );
                        Types.castObject(ret, returnType, Types.ASSIGNMENT);
            } catch (UtilEvalError e) {
                // Point to return statement point if we had one.
                // (else it was implicit return? What's the case here?)
                SimpleNode node = callerInfo;
                if (retControl != null)
                    node = retControl.returnPoint;
                throw e.toEvalError(
                        "Incorrect type returned from method: "
                                + name + e.getMessage(), node, callstack);
            }
        }

        return ret;
    }

    public boolean hasModifier(String name) {

        return false;
    }

    public String toString() {
        return "Scripted Method: "
                + StringUtil.methodString(name, getParameterTypes());
    }

}
