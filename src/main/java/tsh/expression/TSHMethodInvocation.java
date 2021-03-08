package tsh.expression;

import tsh.*;
import tsh.entity.TMethodData;
import tsh.exception.EvalError;
import tsh.exception.ReflectError;
import tsh.exception.TargetError;
import tsh.exception.UtilEvalError;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TSHMethodInvocation extends SimpleNode {

    public TSHMethodInvocation(String id) {
        super(id);
    }

    TSHAmbiguousName getNameNode() {
        return (TSHAmbiguousName) jjtGetChild(0);
    }


    TSHArguments getArgsNode(int i) {
        return (TSHArguments) jjtGetChild(i);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        TSHAmbiguousName nameNode = getNameNode();
        if (namespace.getParent() != null && namespace.getParent().isClass
                && (nameNode.text.equals("super") || nameNode.text.equals("this"))) {
            return Primitive.VOID;
        }
        Name name = nameNode.getName(namespace);
        try {
            Object result = null;
            TshMethod method = namespace.getMethod(nameNode.text.trim(), new Class[]{null});
            if (method != null && method.methodData != null && method.methodData.invocation != null) {                  // 如果有注解方法调用
                Object[] args = this.getArgsNode(1).getArguments(callstack, interpreter);
                result = invoke(method, args, callstack, interpreter, false);
            } else {
                for (int i = 1; i < jjtGetNumChildren(); i++) {         //处理 a(1) 和 a(1)(2)...(3)类型的方法调用
                    Object[] args = getArgsNode(i).getArguments(callstack, interpreter);
                    if (i == 1) {
                        result = name.invokeMethod(interpreter, args, callstack, this);
                    } else {
                        result = ((TshMethod) result).invokeNew(args, interpreter, callstack, this);
                    }
                }
            }
            return result;
        } catch (ReflectError e) {
            throw new EvalError("Error in method invocation: " + e.getMessage(), this, callstack);
        } catch (InvocationTargetException e) {
            String msg = "Method Invocation " + name;
            Throwable te = e.getTargetException();
            boolean isNative = true;
            if (te instanceof EvalError)
                if (te instanceof TargetError)
                    isNative = ((TargetError) te).inNativeCode();
                else
                    isNative = false;

            throw new TargetError(msg, te, this, callstack, isNative);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    public Object invoke(TshMethod method, Object[] argValues, CallStack callstack, Interpreter interpreter, boolean flag) throws EvalError, UtilEvalError {
        Object result = null;
        if (method != null && method.methodData != null && method.methodData.invocation != null) {                  // 如果有注解方法调用
            TSHMethodInvocation methodInvocation = method.methodData.invocation;
            method.methodData = null;                                   // 防止递归调用
            String methodName = getMethodName(methodInvocation);
            List<String> aMethodNames = new ArrayList<>();
            List<String> aVariableNames = new ArrayList<>();
            try {
                NameSpace namespace = new NameSpace(callstack.top(), methodName);
                callstack.push(namespace);
                result = namespace.getMethod(methodName, new Class[]{null});
                for (int i = 0; i < methodInvocation.jjtGetNumChildren(); i++) {      //
                    if (i == methodInvocation.jjtGetNumChildren() - 1) {
                        result = invoke(((TshMethod) result), new Object[]{method}, callstack, interpreter, true);
                        removeNotNeed(callstack.top(), aMethodNames, aVariableNames);
                    } else {
                        Object[] args = methodInvocation.getArgsNode(i + 1).getArguments(callstack, interpreter);
                        result = invoke(((TshMethod) result), args, callstack, interpreter, true);
                        removeNotNeed(callstack.top(), aMethodNames, aVariableNames);
                    }
                }
                result = invoke(((TshMethod) result), argValues, callstack, interpreter, true);
            } catch (UtilEvalError utilEvalError) {
                utilEvalError.printStackTrace();
            } catch (EvalError evalError) {
                evalError.printStackTrace();
            } finally {
                method.methodData = new TMethodData(methodInvocation);                   //防止递归调用，先设置为空，再恢复
                NameSpace pop = callstack.pop();
                if (flag) {
                    NameSpace top = callstack.top();
                    for (String name : pop.getVariableNames()) {
                        if (aVariableNames.contains(name)) {
                            continue;
                        }
                        Object value = pop.getVariable(name);
                        Object old = top.getVariable(name);
                        top.setVariable(name, old == null || old == Primitive.VOID ? value : old, false);
                    }
                    for (String name : pop.getMethodNames()) {
                        if (aMethodNames.contains(name)) {
                            continue;
                        }
                        TshMethod value = pop.getMethod(name, new Class[]{null});
                        TshMethod old = top.getMethod(name, new Class[]{null});
                        value = old == null ? value : old;
                        if (value != null) {
                            top.setMethod(name, value);
                        }
                    }
                }
            }
            return result;
        } else {
            result = method.invokeNew(argValues, interpreter, callstack, this, true);
        }
        return result;
    }


    public void removeNotNeed(NameSpace parent, List<String> methodNames, List<String> variableNames) {
        for (String name : parent.getVariableNames()) {
            variableNames.add(name);
        }
        for (String name : parent.getMethodNames()) {
            methodNames.add(name);
        }
    }

    public String getMethodName(TSHMethodInvocation methodInvocation) {
        TSHAmbiguousName annotaionName = methodInvocation.getNameNode();    //注解名称
        return annotaionName.text.trim();
    }


}
