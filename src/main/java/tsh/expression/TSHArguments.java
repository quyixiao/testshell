package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.constant.ParserConstants;
import tsh.entity.TVar;
import tsh.exception.EvalError;
import tsh.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSHArguments extends SimpleNode implements ParserConstants {

    public TSHArguments(String id) {
        super(id);
    }

    public Object[] getArguments(CallStack callstack, Interpreter interpreter) throws EvalError {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode argsI = ((SimpleNode) jjtGetChild(i));
            if (argsI instanceof TSHAssignment) {
                SimpleNode lhsNode = (SimpleNode) argsI.jjtGetChild(0);
                String paramName = null;
                if (lhsNode instanceof TSHPrimaryExpression) {
                    SimpleNode llhsNode = (SimpleNode) lhsNode.jjtGetChild(0);
                    if (llhsNode instanceof TSHAmbiguousName) {
                        paramName = llhsNode.getText().trim();
                    }
                }
                SimpleNode rhsNode = (SimpleNode) argsI.jjtGetChild(1);
                Object value = null;
                SimpleNode parent = ((SimpleNode) rhsNode.jjtGetParent());
                // 如果 parent 的【operator】操作符号是 += ，-= ，*= ， /= ，%=
                if (parent instanceof TSHAssignment && !"=".equals(((TSHAssignment) parent).operator)) {
                    value = parent.eval(callstack, interpreter);
                    list.add(value);
                    continue;
                }
                value = rhsNode.eval(callstack, interpreter);
                TVar tVar = new TVar(paramName, value);
                list.add(tVar);
            } else if (argsI instanceof TSHStarArgument) {
                TSHStarArgument starNode = (TSHStarArgument) argsI;
                Object result = starNode.eval(callstack, interpreter);
                if (Utils.eq(starNode.kind, STAR)) {
                    list.addAll((List) result);
                } else if (Utils.eq(starNode.kind, SSTAR)) {
                    for (Map.Entry<String, Object> entry : ((Map<String, Object>) result).entrySet()) {
                        TVar tVar = new TVar(entry.getKey(), entry.getValue());
                        list.add(tVar);
                    }
                }
            } else {
                Object value = argsI.eval(callstack, interpreter);
                list.add(value);
            }
        }
        return list.toArray();
    }
}
