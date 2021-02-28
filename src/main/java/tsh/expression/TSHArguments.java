package tsh.expression;

import tsh.CallStack;
import tsh.exception.EvalError;
import tsh.Interpreter;
import tsh.SimpleNode;

public class TSHArguments extends SimpleNode {

    public TSHArguments(String id) {
        super(id);
    }

    public Object[] getArguments(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        // evaluate each child
        Object[] args = new Object[jjtGetNumChildren()];
        for (int i = 0; i < args.length; i++) {
            args[i] = ((SimpleNode) jjtGetChild(i)).eval(callstack, interpreter);
            if (args[i] == Primitive.VOID)
                throw new EvalError("Undefined argument: " +
                        ((SimpleNode) jjtGetChild(i)).getText(), this, callstack);
        }

        return args;
    }
}
