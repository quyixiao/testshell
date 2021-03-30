package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.LinkedHashMap;
import java.util.Map;

public class TSHExportStatement extends SimpleNode {

    public String kind;

    public String label;

    public TSHExportStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Map<String, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode simpleNode = (SimpleNode) jjtGetChild(i);
            if (simpleNode instanceof TSHPrimaryExpression) {
                Object obj = simpleNode.jjtGetChild(0);
                if (obj instanceof TSHAmbiguousName) {
                    Object value = simpleNode.eval(callstack, interpreter);
                    result.put(((TSHAmbiguousName) obj).text.trim(), value);
                }
            } else if (simpleNode instanceof TSHAssignment) {
                SimpleNode lhsNode = (SimpleNode) simpleNode.jjtGetChild(0);
                String paramName = null;
                if (lhsNode instanceof TSHPrimaryExpression) {
                    SimpleNode llhsNode = (SimpleNode) lhsNode.jjtGetChild(0);
                    if (llhsNode instanceof TSHAmbiguousName) {
                        paramName = llhsNode.getText().trim();
                    }
                }
                Object value = simpleNode.eval(callstack, interpreter);
                result.put(paramName, value);
            }
        }
        return new ExportControl(result);
    }
}
