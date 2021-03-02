package tsh.expression;


import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHSwitchLabel extends SimpleNode {

    public boolean isDefault;

    public TSHSwitchLabel(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (isDefault) {
            return null; // should probably error
        }
        SimpleNode label = ((SimpleNode) jjtGetChild(0));
        return label.eval(callstack, interpreter);
    }
}
