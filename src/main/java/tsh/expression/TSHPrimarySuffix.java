package tsh.expression;

import tsh.*;
import tsh.constant.TParserConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.*;
import tsh.t.TSHTuple;
import tsh.t.Tuple3;
import tsh.util.CollectionManager;
import tsh.util.NumberUtil;
import tsh.util.Reflect;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TSHPrimarySuffix extends SimpleNode implements TParserConstants {
    public static final int
            INDEX = 1,
            NAME = 2,
            PROPERTY = 3;

    public int operation;
    Object index;
    public String field;

    public TSHPrimarySuffix(String id) {
        super(id);
    }


    public Object doSuffix(Object obj, boolean toLHS, CallStack callstack, Interpreter interpreter) throws EvalError {
        if (obj instanceof SimpleNode) {
            if (obj instanceof TSHAmbiguousName) {
                obj = ((TSHAmbiguousName) obj).toObject(callstack, interpreter);
            } else {
                obj = ((SimpleNode) obj).eval(callstack, interpreter);
            }
        } else if (obj instanceof LHS) {
            try {
                obj = ((LHS) obj).getValue();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }

        try {
            switch (operation) {
                case INDEX:
                    return doIndex(obj, toLHS, callstack, interpreter);

                case NAME:
                    return doName(obj, toLHS, callstack, interpreter);

                case PROPERTY:
                    return doProperty(toLHS, obj, callstack, interpreter);

                default:
                    throw new InterpreterError("Unknown suffix type");
            }
        } catch (ReflectError e) {
            throw new EvalError("reflection error: " + e, this, callstack);
        } catch (InvocationTargetException e) {
            throw new TargetError("target exception", e.getTargetException(),
                    this, callstack, true);
        }
    }


    /*
        Field access, .length on array, or a method invocation
        Must handle toLHS case for each.
    */
    private Object doName(
            Object obj, boolean toLHS,
            CallStack callstack, Interpreter interpreter)
            throws EvalError, ReflectError, InvocationTargetException {
        try {
            // .length on array
            if (field.equals("length") && obj.getClass().isArray())
                if (toLHS)
                    throw new EvalError("Can't assign array length", this, callstack);
                else
                    return new Primitive(Array.getLength(obj));

            // field access
            if (jjtGetNumChildren() == 0)
                if (toLHS)
                    return Reflect.getLHSObjectField(obj, field);
                else
                    return Reflect.getObjectFieldValue(obj, field);

            // Method invocation
            // (LHS or non LHS evaluation can both encounter method calls)
            Object[] oa = ((TSHArguments) jjtGetChild(0)).getArguments(callstack, interpreter);

            // TODO:
            // Note: this try/catch block is copied from BSHMethodInvocation
            // we need to factor out this common functionality and make sure
            // we handle all cases ... (e.g. property style access, etc.)
            // maybe move this to Reflect ?
            try {
                return Reflect.invokeObjectMethod(obj, field, oa, interpreter, callstack, this);
            } catch (ReflectError e) {
                throw new EvalError("Error in method invocation: " + e.getMessage(), this, callstack);
            } catch (InvocationTargetException e) {
                String msg = "Method Invocation " + field;
                Throwable te = e.getTargetException();

				/*
					Try to squeltch the native code stack trace if the exception
					was caused by a reflective call back into the bsh interpreter
					(e.g. eval() or source()
				*/
                boolean isNative = true;
                if (te instanceof EvalError)
                    if (te instanceof TargetError)
                        isNative = ((TargetError) te).inNativeCode();
                    else
                        isNative = false;

                throw new TargetError(msg, te, this, callstack, isNative);
            }

        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    /**
     *
     */
    static TSHTuple getIndexAux(Object obj, CallStack callstack, Interpreter interpreter, SimpleNode callerInfo) throws EvalError {
        try {
            Object indexVal = ((SimpleNode) callerInfo.jjtGetChild(0)).eval(callstack, interpreter);
            int index1 = getIndex(indexVal);
            int num = callerInfo.jjtGetNumChildren();
            if (num > 1) {
                Object indexVal1 = ((SimpleNode) callerInfo.jjtGetChild(1)).eval(callstack, interpreter);
                int index2 = getIndex(indexVal1);
                return new TSHTuple(true, index1, index2);
            }
            return new TSHTuple(false, index1);
        } catch (UtilEvalError e) {
            Interpreter.debug("doIndex: " + e);
            throw e.toEvalError("Arrays may only be indexed by integer types.", callerInfo, callstack);
        }
    }

    public static int getIndex(Object indexVal) throws UtilEvalError {
        if (!(indexVal instanceof Primitive)) {
            if (indexVal instanceof TBigDecimal) {
                indexVal = ((TBigDecimal) indexVal).getValue();
            } else {
                indexVal = Types.castObject(indexVal, Integer.TYPE, Types.ASSIGNMENT);
            }
        }
        return NumberUtil.objToInt(indexVal);
    }

    /**
     * array index.
     * Must handle toLHS case.
     */
    private Object doIndex(Object obj, boolean toLHS, CallStack callstack, Interpreter interpreter) throws EvalError, ReflectError {
        Tuple3<Boolean, Integer, Integer> data = getIndexAux(obj, callstack, interpreter, this).getData();
        if (toLHS) {
            return new LHS(obj, data.getSecond());
        } else {
            try {
                if (data.getFirst()) {
                    int beginIndex;
                    int endIndex;
                    if (obj instanceof List) {
                        beginIndex = getRealIndex(((List) obj).size(), data.getSecond());
                        endIndex = getRealIndex(((List) obj).size(), data.getThird());
                        return ((List) obj).subList(beginIndex, endIndex);
                    } else if (obj instanceof String) {
                        beginIndex = getRealIndex(((String) obj).length(), data.getSecond());
                        endIndex = getRealIndex(((String) obj).length(), data.getThird());
                        return obj.toString().substring(beginIndex, endIndex);
                    }
                } else {
                    if (obj instanceof List) {
                        return ((List) obj).get(getRealIndex(((List) obj).size(), data.getSecond()));
                    } else if (obj instanceof String) {
                        return ((String) obj).toCharArray()[getRealIndex(((String) obj).length(), data.getSecond())] + "";
                    } else {
                        return Reflect.getIndex(obj, data.getSecond());
                    }
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }
        return null;
    }

    public int getRealIndex(int size, int position) {
        if (position < 0) {
            return size + position;
        }
        if (position == DEFAULT_INT_MAX) {
            return size;
        }
        return position;
    }

    /**
     * Property access.
     * Must handle toLHS case.
     */
    private Object doProperty(boolean toLHS, Object obj, CallStack callstack, Interpreter interpreter) throws EvalError {
        if (obj == Primitive.VOID)
            throw new EvalError("Attempt to access property on undefined variable or class name", this, callstack);

        if (obj instanceof Primitive)
            throw new EvalError("Attempt to access property on a primitive", this, callstack);

        Object value = ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);

        if (!(value instanceof String))
            throw new EvalError("Property expression must be a String or identifier.", this, callstack);

        if (toLHS)
            return new LHS(obj, (String) value);

        // Property style access to Hashtable or Map
        CollectionManager cm = CollectionManager.getCollectionManager();
        if (cm.isMap(obj)) {
            Object val = cm.getFromMap(obj, value/*key*/);
            return (val == null ? val = Primitive.NULL : val);
        }
        try {
            return Reflect.getObjectProperty(obj, (String) value);
        } catch (UtilEvalError e) {
            throw e.toEvalError("Property: " + value, this, callstack);
        } catch (ReflectError e) {
            throw new EvalError("No such property: " + value, this, callstack);
        }
    }


    public static void main(String[] args) {
        String a = "abcdefg";
        String b = a.toString().substring(1, 7);
        System.out.println(b);
    }
}
