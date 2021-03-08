package tsh.expression;


import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.constant.ParserConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;
import tsh.util.Utils;

public class TSHBinaryExpression extends SimpleNode implements ParserConstants {
    public String kind;

    public TSHBinaryExpression(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        SimpleNode simpleNode = ((SimpleNode) jjtGetChild(0));
        Object lhs = simpleNode.eval(callstack, interpreter);

        if (Utils.eq(kind, BOOL_AND)) {
            Object obj = lhs;
            if (isPrimitiveValue(lhs))
                obj = ((Primitive) lhs).getValue();
            if (obj instanceof Boolean && (((Boolean) obj).booleanValue() == false))
                return Primitive.FALSE;
        }
        if (Utils.eq(kind, BOOL_OR)) {
            Object obj = lhs;
            if (isPrimitiveValue(lhs))
                obj = ((Primitive) lhs).getValue();
            if (obj instanceof Boolean && (((Boolean) obj).booleanValue() == true))
                return Primitive.TRUE;
        }


        boolean isLhsWrapper = isWrapper(lhs);
        Object rhs = ((SimpleNode) jjtGetChild(1)).eval(callstack, interpreter);
        boolean isRhsWrapper = isWrapper(rhs);
        if ((isLhsWrapper || isPrimitiveValue(lhs)) && (isRhsWrapper || isPrimitiveValue(rhs))) {
            try {
                return Primitive.binaryOperation(lhs, rhs, kind);
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }

        switch (kind) {
            case EQ:
                return (lhs == rhs) ? Primitive.TRUE : Primitive.FALSE;
            case NE:
                return (lhs != rhs) ? Primitive.TRUE : Primitive.FALSE;
            case PLUS:
                if (lhs instanceof String || rhs instanceof String)
                    return lhs.toString() + rhs.toString();
            case STAR:
                StringBuilder sb = new StringBuilder();
                if(lhs instanceof TBigDecimal){
                    for(int i =0;i < ((TBigDecimal) lhs).getValue().intValue(); i ++){
                        sb.append(rhs.toString());
                    }
                }
                if(rhs instanceof  TBigDecimal){
                    for(int i = 0 ;i < ((TBigDecimal) rhs).getValue().intValue();i ++){
                        sb.append(lhs.toString());
                    }
                }
                return sb.toString();
            default:
                if (lhs instanceof Primitive || rhs instanceof Primitive)
                    if (lhs == Primitive.VOID || rhs == Primitive.VOID)
                        throw new EvalError(
                                "illegal use of undefined variable, class, or 'void' literal",
                                this, callstack);
                    else if (lhs == Primitive.NULL || rhs == Primitive.NULL)
                        throw new EvalError(
                                "illegal use of null value or 'null' literal", this, callstack);

                throw new EvalError("Operator: '" + kind +
                        "' inappropriate for objects", this, callstack);
        }
    }

    /*
        object is a non-null and non-void Primitive type
    */
    private boolean isPrimitiveValue(Object obj) {
        return ((obj instanceof Primitive)
                && (obj != Primitive.VOID) && (obj != Primitive.NULL));
    }

    /*
        object is a java.lang wrapper for boolean, char, or number type
    */
    private boolean isWrapper(Object obj) {
        return (obj instanceof Boolean ||
                obj instanceof Character || obj instanceof Number || obj instanceof TBigDecimal);
    }




}
