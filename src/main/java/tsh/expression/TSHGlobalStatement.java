package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHGlobalStatement extends SimpleNode {

    public TSHGlobalStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode simpleNode = (SimpleNode) jjtGetChild(0);
        if (simpleNode instanceof TSHPrimaryExpression) {
            Object obj = simpleNode.jjtGetChild(0);
            if (obj instanceof TSHAmbiguousName) {
                Object value = simpleNode.eval(callstack, interpreter);
                return new GlobalControl(((TSHAmbiguousName) obj).text.trim(), value);
            }
        }
        return null;
    }



}
