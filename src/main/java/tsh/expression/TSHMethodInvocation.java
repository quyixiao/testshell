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
            for (int i = 1; i < jjtGetNumChildren(); i++) {         //处理 a(1) 和 a(1)(2)...(3)类型的方法调用
                Object[] args = getArgsNode(i).getArguments(callstack, interpreter);
                if (i == 1) {
                    result = name.invokeMethod(interpreter, args, callstack, this);
                } else {
                    if (result instanceof TshMethod) {
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
