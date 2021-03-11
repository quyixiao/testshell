package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.ArrayList;
import java.util.List;

public class TSHMapInvocation extends SimpleNode {


    public TSHMapInvocation(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        List<Object> list = new ArrayList<>();
        SimpleNode simpleNode = (SimpleNode) jjtGetChild(0);
        Object object = simpleNode.eval(callstack, interpreter);
        if (object instanceof TshMethod) {
            List<List<Object>> argsList = new ArrayList<>();
            for (int i = 1; i < jjtGetNumChildren(); i++) {
                List<Object> l = (List) ((SimpleNode) jjtGetChild(i)).eval(callstack, interpreter);
                argsList.add(l);
            }
            for (int i = 0; i < argsList.get(0).size(); i++) {
                Object[] args = new Object[argsList.size()];
                for (int j = 0; j < argsList.size(); j++) {
                    args[j] = argsList.get(j).get(i);
                }
                Object result = ((TshMethod) object).invokeNew(args, interpreter, callstack, simpleNode);
                list.add(result);
            }
        }
        return list;
    }


}
