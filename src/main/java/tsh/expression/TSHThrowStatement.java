package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.TargetError;

public class TSHThrowStatement extends SimpleNode {
    public TSHThrowStatement(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);
        if (!(obj instanceof Exception)) {
            throw new EvalError("Expression in 'throw' must be Exception type", this, callstack);
        }
        throw new TargetError((Exception) obj, this, callstack);
    }
}
