package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.constant.TParserConstants;
import tsh.exception.EvalError;
import tsh.util.StringUtil;

public class TSHWhileStatement extends SimpleNode implements TParserConstants {

    /**
     * Set by Parser, default {@code false}
     */
    public boolean isDoStatement;
    private String label;

    public TSHWhileStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter, String label) throws EvalError {
        this.label = label;
        return eval(callstack, interpreter);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int numChild = jjtGetNumChildren();
        // Order of body and condition is swapped for do / while
        final SimpleNode condExp;
        final SimpleNode body;
        if (isDoStatement) {
            condExp = (SimpleNode) jjtGetChild(1);
            body = (SimpleNode) jjtGetChild(0);
        } else {
            condExp = (SimpleNode) jjtGetChild(0);
            if (numChild > 1) {
                body = (SimpleNode) jjtGetChild(1);
            } else {
                body = null;
            }
        }
        boolean doOnceFlag = isDoStatement;
        while (doOnceFlag || TSHIfStatement.evaluateCondition(condExp, callstack, interpreter)) {
            doOnceFlag = false;
            if (body == null) {
                continue;
            }
            Object ret = body.eval(callstack, interpreter);
            if (ret instanceof ReturnControl) {
                String retLabel = ((ReturnControl) ret).label;
                if (StringUtil.isNotBlank(retLabel)) {                //处理 break label 的情况
                    if (!retLabel.equals(label)) {
                        return ret;
                    }
                    break;
                } else {
                    switch (((ReturnControl) ret).kind) {
                        case RETURN:
                            return ret;
                        case CONTINUE:
                            break;
                        case BREAK:
                            return Primitive.VOID;
                    }
                }
            }
        }
        return Primitive.VOID;
    }


}
