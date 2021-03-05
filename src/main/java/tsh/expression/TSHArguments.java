package tsh.expression;

import tsh.CallStack;
import tsh.entity.TVar;
import tsh.exception.EvalError;
import tsh.Interpreter;
import tsh.SimpleNode;

import java.util.ArrayList;
import java.util.List;

public class TSHArguments extends SimpleNode {

    public TSHArguments(String id) {
        super(id);
    }

    public Object[] getArguments(CallStack callstack, Interpreter interpreter) throws EvalError {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode argsI = ((SimpleNode) jjtGetChild(i));
            if(argsI instanceof TSHAssignment){
                SimpleNode lhsNode =(SimpleNode) argsI.jjtGetChild(0);
                String paramName = null;
                if(lhsNode instanceof TSHPrimaryExpression){
                    SimpleNode llhsNode = (SimpleNode) lhsNode.jjtGetChild(0);
                    if(llhsNode instanceof TSHAmbiguousName){
                        paramName = llhsNode.getText().trim();
                    }
                }
                SimpleNode rhsNode = (SimpleNode) argsI.jjtGetChild(1);
                Object value = rhsNode.eval(callstack,interpreter);
                TVar tVar = new TVar(paramName,value);
                list.add(tVar);
            }else{
                Object value = argsI.eval(callstack, interpreter);
                list.add(value);
            }
            if (list.get(i) == Primitive.VOID)
                throw new EvalError("Undefined argument: " +   ((SimpleNode) jjtGetChild(i)).getText(), this, callstack);
        }
        return list.toArray();
    }
}
