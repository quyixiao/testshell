package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHReturnStatement extends SimpleNode {

    public String kind;

    public String label;

    public TSHReturnStatement(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object value;
        if (jjtGetNumChildren() > 0) {
            value = ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);
        } else {
            value = Primitive.VOID;
        }
        return new ReturnControl(kind, value, this,label);
    }
}
