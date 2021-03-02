package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHLabeledStatement extends SimpleNode {
    public String label;

    public TSHLabeledStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode node = ((SimpleNode) jjtGetChild(0));
        if (node instanceof TSHWhileStatement) {
            return ((TSHWhileStatement) node).eval(callstack, interpreter, label);
        } else if (node instanceof TSHForStatement) {
            return ((TSHForStatement) node).eval(callstack, interpreter, label);
        }
        return node.eval(callstack, interpreter);
    }
}
