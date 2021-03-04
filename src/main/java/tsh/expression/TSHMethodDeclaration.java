package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;

public class TSHMethodDeclaration extends SimpleNode {

    public String methodName;//方法名称

    TSHFormalParameters paramsNode;

    int firstThrowsClause;

    TSHBlock blockNode;
    int numThrows = 0;

    public TSHMethodDeclaration(String id) {
        super(id);
    }

    synchronized void insureNodesParsed() {
        if (paramsNode != null) {
            return;
        }
        paramsNode = (TSHFormalParameters) jjtGetChild(0);
        blockNode = (TSHBlock) jjtGetChild(1 + numThrows); // skip throws
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        evalNodes(callstack, interpreter);
        NameSpace namespace = callstack.top();
        TshMethod bshMethod = new TshMethod(this, namespace, null);
        try {
            namespace.setMethod(methodName, bshMethod);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
        return Primitive.VOID;
    }

    private void evalNodes(CallStack callstack, Interpreter interpreter) throws EvalError {
        insureNodesParsed();

        for (int i = firstThrowsClause; i < numThrows + firstThrowsClause; i++) {
            ((TSHAmbiguousName) jjtGetChild(i)).toClass(callstack, interpreter);
        }
        paramsNode.eval(callstack, interpreter);

    }

    public String toString() {
        return "MethodDeclaration: " + id;
    }


}
