package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.ArrayList;

public class TSHLambdaDeclaration extends TSHMethodDeclaration {

    public TSHLambdaDeclaration(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object result = super.eval(callstack, interpreter);
        if (jjtGetNumChildren() > 2 + numThrows) {
            for (int i = 2 + numThrows; i < jjtGetNumChildren(); i++) {
                result = ((SimpleNode) jjtGetChild(i)).eval(callstack, interpreter);
            }
        }
        return result;
    }

    public String toString() {
        return "TSHLambdaDeclaration: " + id;
    }

}
