package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.LHS;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;

public class TSHPrimaryExpression extends SimpleNode {

    public TSHPrimaryExpression(String id) {
        super(id);
    }


    public LHS toLHS(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = eval(true, callstack, interpreter);
        if (!(obj instanceof LHS)) {
            throw new EvalError("Can't assign to:", this, callstack);
        } else {
            return (LHS) obj;
        }
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        return eval(false, callstack, interpreter);
    }


    private Object eval(boolean toLHS, CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = jjtGetChild(0);
        int numChildren = jjtGetNumChildren();

        for (int i = 1; i < numChildren; i++) {
            obj = ((TSHPrimarySuffix) jjtGetChild(i)).doSuffix(obj, toLHS, callstack, interpreter);
        }

        if (obj instanceof SimpleNode) {
            if (obj instanceof TSHAmbiguousName) {
                if (toLHS) {
                    obj = ((TSHAmbiguousName) obj).toLHS(callstack, interpreter);
                } else {
                    obj = ((TSHAmbiguousName) obj).toObject(callstack, interpreter);
                }
            } else if (toLHS) {
                throw new EvalError("Can't assign to prefix.", this, callstack);
            } else {
                obj = ((SimpleNode) obj).eval(callstack, interpreter);
            }
        }


        if (obj instanceof LHS) {
            if (toLHS) {
                return obj;
            } else {
                try {
                    return ((LHS) obj).getValue();
                } catch (UtilEvalError e) {
                    throw e.toEvalError(this, callstack);
                }
            }
        } else {
            return obj;
        }
    }
}