package tsh.expression;

import tsh.*;
import tsh.exception.EvalError;
import tsh.exception.ReflectError;
import tsh.exception.TargetError;
import tsh.exception.UtilEvalError;

import java.lang.reflect.InvocationTargetException;

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
            if (method != null && method.invocation != null) {                  // 如果有注解方法调用
                TSHMethodInvocation methodInvocation = method.invocation;
                method.invocation = null;                                   // 防止递归调用
                TSHAmbiguousName annotaionName = methodInvocation.getNameNode();    //注解名称
                result = namespace.getMethod(annotaionName.text.trim(), new Class[]{null});
                for (int i = 0; i < methodInvocation.jjtGetNumChildren(); i++) {      //
                    if (i == methodInvocation.jjtGetNumChildren() - 1) {
                        result = ((TshMethod) result).invokeNew(new Object[]{method}, interpreter, callstack, this);
                    } else {
                        Object[] args = methodInvocation.getArgsNode(i + 1).getArguments(callstack, interpreter);
                        result = ((TshMethod) result).invokeNew(args, interpreter, callstack, this);
                    }
                }
                Object[] args = this.getArgsNode(1).getArguments(callstack, interpreter);
                result = ((TshMethod) result).invokeNew(args, interpreter, callstack, this);
                method.invocation = methodInvocation;                   //防止递归调用，先设置为空，再恢复
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
}
