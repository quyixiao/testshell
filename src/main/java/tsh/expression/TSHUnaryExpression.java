package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.LHS;
import tsh.SimpleNode;
import tsh.constant.TParserConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.EvalError;
import tsh.exception.InterpreterError;
import tsh.exception.UtilEvalError;
import tsh.util.NumberUtil;
import tsh.util.Utils;

import java.math.BigDecimal;

public class TSHUnaryExpression extends SimpleNode implements TParserConstants {
    public String kind;
    public boolean postfix = false;

    public TSHUnaryExpression(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        SimpleNode node = (SimpleNode) jjtGetChild(0);
        try {
            if (Utils.eqOR(kind, INCR, DECR)) {
                LHS lhs = ((TSHPrimaryExpression) node).toLHS(callstack, interpreter);
                return lhsUnaryOperation(lhs, interpreter.getStrictJava());
            } else {
                return unaryOperation(node.eval(callstack, interpreter), kind);
            }
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    private Object lhsUnaryOperation(LHS lhs, boolean strictJava) throws UtilEvalError {
        Object prevalue, postvalue;
        prevalue = lhs.getValue();
        postvalue = unaryOperation(prevalue, kind);

        Object retVal;
        if (postfix) {
            retVal = prevalue;
        } else {
            retVal = postvalue;
        }

        lhs.assign(postvalue, strictJava);
        return retVal;
    }

    private Object unaryOperation(Object op, String kind) throws UtilEvalError {
        if (op instanceof Boolean || op instanceof Character || op instanceof Number ) {
            return primitiveWrapperUnaryOperation(op, kind);
        }
        if(op instanceof TBigDecimal){
            Object obj  = ((TBigDecimal) op).getValue();
            int precision = ((TBigDecimal) op).getPrecision();
            if(((TBigDecimal) op).getPrecision() > 0){
                op = NumberUtil.objToDoubleWithDefault(obj,0.0d);
            }else{
                op = NumberUtil.objToInt(obj);
            }
            Object result =  primitiveWrapperUnaryOperation(op, kind);
            return new TBigDecimal(NumberUtil.objToBigDecimalDefault(result,BigDecimal.ZERO),precision);
        }

        if (!(op instanceof Primitive)) {
            throw new UtilEvalError("Unary operation " + id + " inappropriate for object");
        }

        return Primitive.unaryOperation((Primitive) op, kind);
    }

    private Object primitiveWrapperUnaryOperation(Object val, String kind) throws UtilEvalError {
        Class operandType = val.getClass();
        Object operand = Primitive.promoteToInteger(val);

        if (operand instanceof Boolean) {
            return Primitive.booleanUnaryOperation((Boolean) operand, kind) ? Boolean.TRUE : Boolean.FALSE;
        } else if (operand instanceof Integer) {
            int result = Primitive.intUnaryOperation((Integer) operand, kind);
            // ++ and -- must be cast back the original type
            if (Utils.eqOR(kind, INCR, DECR)) {
                if (operandType == Byte.TYPE) {
                    return new Byte((byte) result);
                }
                if (operandType == Short.TYPE) {
                    return new Short((short) result);
                }
                if (operandType == Character.TYPE) {
                    return new Character((char) result);
                }
            }
            return new Integer(result);
        } else if (operand instanceof Long) {
            return new Long(Primitive.longUnaryOperation((Long) operand, kind));
        } else if (operand instanceof Float) {
            return new Float(Primitive.floatUnaryOperation((Float) operand, kind));
        } else if (operand instanceof Double) {
            return new Double(Primitive.doubleUnaryOperation((Double) operand, kind));
        } else {
            throw new InterpreterError("An error occurred.  Please call technical support.");
        }
    }
}
