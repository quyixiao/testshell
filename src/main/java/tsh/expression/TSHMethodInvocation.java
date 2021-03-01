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


    TSHArguments getArgsNode() {
        return (TSHArguments) jjtGetChild(1);
    }


    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        NameSpace namespace = callstack.top();
        TSHAmbiguousName nameNode = getNameNode();

        // Do not evaluate methods this() or super() in class instance space
        // (i.e. inside a constructor)
        if (namespace.getParent() != null && namespace.getParent().isClass
                && (nameNode.text.equals("super") || nameNode.text.equals("this"))
        )
            return Primitive.VOID;

        Name name = nameNode.getName(namespace);
        Object[] args = getArgsNode().getArguments(callstack, interpreter);


        try {
            return name.invokeMethod(interpreter, args, callstack, this);
        } catch (ReflectError e) {
            throw new EvalError(
                    "Error in method invocation: " + e.getMessage(),
                    this, callstack);
        } catch (InvocationTargetException e) {
            String msg = "Method Invocation " + name;
            Throwable te = e.getTargetException();

			/*
				Try to squeltch the native code stack trace if the exception
				was caused by a reflective call back into the bsh interpreter
				(e.g. eval() or source()
			*/
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
