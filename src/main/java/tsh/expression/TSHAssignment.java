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
import tsh.util.Utils;

public class TSHAssignment extends SimpleNode implements TParserConstants {


    public String operator;

    public TSHAssignment(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        TSHPrimaryExpression lhsNode = (TSHPrimaryExpression) jjtGetChild(0);
        if (lhsNode == null)
            throw new InterpreterError("Error, null LHSnode");

        boolean strictJava = interpreter.getStrictJava();
        LHS lhs = lhsNode.toLHS(callstack, interpreter);
        if (lhs == null)
            throw new InterpreterError("Error, null LHS");

        // For operator-assign operations save the lhs value before evaluating
        // the rhs.  This is correct Java behavior for postfix operations
        // e.g. i=1; i+=i++; // should be 2 not 3
        Object lhsValue = null;
        if (Utils.notEq(operator, ASSIGN)) // assign doesn't need the pre-value
            try {
                lhsValue = lhs.getValue();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }

        SimpleNode rhsNode = (SimpleNode) jjtGetChild(1);

        Object rhs;

        // implement "blocks" foo = { };
        // if ( rhsNode instanceof BSHBlock )
        //    rsh =
        // else
        rhs = rhsNode.eval(callstack, interpreter);

        if (rhs == Primitive.VOID)
            throw new EvalError("Void assignment.", this, callstack);

        try {
            switch (operator) {
                case ASSIGN:
                    return lhs.assign(rhs, strictJava);

                case PLUSASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, PLUS), strictJava);

                case MINUSASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, MINUS), strictJava);

                case STARASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, STAR), strictJava);

                case SLASHASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, SLASH), strictJava);

                case ANDASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, AND), strictJava);

                case ORASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, OR), strictJava);

                case XORASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, XOR), strictJava);

                case MODASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, MOD), strictJava);

                case LSHIFTASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, LSHIFT), strictJava);

                case RSIGNEDSHIFTASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, RSIGNEDSHIFT), strictJava);

                case RUNSIGNEDSHIFTASSIGN:
                    return lhs.assign(operation(lhsValue, rhs, RUNSIGNEDSHIFT), strictJava);

                default:
                    throw new InterpreterError(
                            "unimplemented operator in assignment TSH");
            }
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    private Object operation(Object lhs, Object rhs, String kind) throws UtilEvalError {
        if (lhs instanceof String && rhs != Primitive.VOID) {
            if (Utils.notEq(kind, PLUS)) {
                throw new UtilEvalError("Use of non + operator with String LHS");
            }
            return (String) lhs + rhs;
        }

        if (lhs instanceof Primitive || rhs instanceof Primitive)
            if (lhs == Primitive.VOID || rhs == Primitive.VOID)
                throw new UtilEvalError(
                        "Illegal use of undefined object or 'void' literal");
            else if (lhs == Primitive.NULL || rhs == Primitive.NULL)
                throw new UtilEvalError(
                        "Illegal use of null object or 'null' literal");


        if ((lhs instanceof Boolean || lhs instanceof Character ||
                lhs instanceof Number || lhs instanceof Primitive) &&
                (rhs instanceof Boolean || rhs instanceof Character ||
                        rhs instanceof Number || rhs instanceof Primitive)) {
            return Primitive.binaryOperation(lhs, rhs, kind);
        }

        if(lhs instanceof TBigDecimal && rhs instanceof  TBigDecimal){
            return Primitive.binaryOperation(lhs, rhs, kind);
        }

        throw new UtilEvalError("Non primitive value in operator: " +
                lhs.getClass() + " " +" " + rhs.getClass());
    }
}
