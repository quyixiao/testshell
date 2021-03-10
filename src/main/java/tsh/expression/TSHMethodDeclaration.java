package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.entity.TMethodData;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;

public class TSHMethodDeclaration extends SimpleNode {

    public String methodName;//方法名称

    public TSHFormalParameters paramsNode;

    public int firstThrowsClause;

    public TSHBlock blockNode;

    public int numThrows = 0;

    public TSHMethodDeclaration(String id) {
        super(id);
    }

    public synchronized void insureNodesParsed() {
        if (paramsNode != null) {
            return;
        }
        paramsNode = (TSHFormalParameters) jjtGetChild(0);
        blockNode = (TSHBlock) jjtGetChild(1 + numThrows); // skip throws
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        return eval(callstack, interpreter, null);
    }


    public Object eval(CallStack callstack, Interpreter interpreter, TSHMethodInvocation invocation) throws EvalError {
        evalNodes(callstack, interpreter);
        NameSpace namespace = callstack.top();
        TMethodData methodData = new TMethodData(invocation);
        TshMethod bshMethod = new TshMethod(this, namespace, methodData);
        try {
            namespace.setMethod(methodName, bshMethod);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
        return Primitive.VOID;
    }

    public void evalNodes(CallStack callstack, Interpreter interpreter) throws EvalError {
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
