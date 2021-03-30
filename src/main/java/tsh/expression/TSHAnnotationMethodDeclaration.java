package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHAnnotationMethodDeclaration extends SimpleNode {

    public TSHAnnotationMethodDeclaration(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        TSHMethodInvocation invocation = ((TSHMethodInvocation) jjtGetChild(0));
        return ((TSHMethodDeclaration) jjtGetChild(1)).eval(callstack, interpreter, invocation);
    }


}
