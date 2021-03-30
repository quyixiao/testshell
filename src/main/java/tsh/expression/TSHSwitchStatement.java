package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.constant.ParserConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;
import tsh.util.TStringUtil;

public class TSHSwitchStatement extends SimpleNode implements ParserConstants {

    public TSHSwitchStatement(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int numchild = jjtGetNumChildren();
        int child = 0;
        SimpleNode switchExp = ((SimpleNode) jjtGetChild(child++));
        Object switchVal = switchExp.eval(callstack, interpreter);

        TSHSwitchLabel label;
        Object node;
        ReturnControl returnControl = null;

        // get the first label
        if (child >= numchild) {
            throw new EvalError("Empty switch statement.", this, callstack);
        }
        label = ((TSHSwitchLabel) jjtGetChild(child++));

        // while more labels or blocks and haven't hit return control
        while (child < numchild && returnControl == null) {
            // if label is default or equals switchVal
            if (label.isDefault || primitiveEquals(switchVal, label.eval(callstack, interpreter), callstack, switchExp)) {
                // execute nodes, skipping labels, until a break or return
                while (child < numchild) {
                    node = jjtGetChild(child++);
                    if (node instanceof TSHSwitchLabel) {
                        continue;
                    }
                    Object value = ((SimpleNode) node).eval(callstack, interpreter);
                    if (value instanceof ReturnControl) {
                        returnControl = (ReturnControl) value;
                        break;
                    }
                }
            } else {
                // skip nodes until next label
                while (child < numchild) {
                    node = jjtGetChild(child++);
                    if (node instanceof TSHSwitchLabel) {
                        label = (TSHSwitchLabel) node;
                        break;
                    }
                }
            }
        }
        if (returnControl != null && (returnControl.kind == RETURN || TStringUtil.isNotBlank(returnControl.label))) {
            return returnControl;
        } else {
            return Primitive.VOID;
        }
    }

    /**
     * Helper method for testing equals on two primitive or boxable objects.
     * yuck: factor this out into Primitive.java
     */
    private boolean primitiveEquals(Object switchVal, Object targetVal, CallStack callstack, SimpleNode switchExp) throws EvalError {
        if (switchVal instanceof Primitive || targetVal instanceof Primitive || switchVal instanceof TBigDecimal || targetVal instanceof TBigDecimal) {
            try {
                Object result = Primitive.binaryOperation(switchVal, targetVal, ParserConstants.EQ);
                result = Primitive.unwrap(result);
                return result.equals(Boolean.TRUE);
            } catch (UtilEvalError e) {
                return Boolean.FALSE;
            }
        } else {
            return switchVal.equals(targetVal);
        }
    }


}
