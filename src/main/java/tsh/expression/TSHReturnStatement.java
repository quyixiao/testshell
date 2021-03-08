package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.ArrayList;
import java.util.List;

public class TSHReturnStatement extends SimpleNode {

    public String kind;

    public String label;

    public TSHReturnStatement(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object value;
        if (jjtGetNumChildren() > 0) {
            if (jjtGetNumChildren() == 1) {
                value = ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);
            } else {
                List<Object> list = new ArrayList<>();
                for (int i = 0; i < jjtGetNumChildren(); i++) {
                    value = ((SimpleNode) jjtGetChild(i)).eval(callstack, interpreter);
                    list.add(value);
                }
                value = list;
            }
        } else {
            value = Primitive.VOID;
        }
        return new ReturnControl(kind, value, this, label);
    }
}
