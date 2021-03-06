package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHStarArgument extends SimpleNode {
    public String kind;

    public TSHStarArgument(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode node = ((SimpleNode) jjtGetChild(0));
        return node.eval(callstack, interpreter);
    }

}
