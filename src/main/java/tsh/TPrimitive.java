/*****************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                *
 * or more contributor license agreements.  See the NOTICE file              *
 * distributed with this work for additional information                     *
 * regarding copyright ownership.  The ASF licenses this file                *
 * to you under the Apache License, Version 2.0 (the                         *
 * "License"); you may not use this file except in compliance                *
 * with the License.  You may obtain a copy of the License at                *
 *                                                                           *
 *     http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing,                *
 * software distributed under the License is distributed on an               *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                    *
 * KIND, either express or implied.  See the License for the                 *
 * specific language governing permissions and limitations                   *
 * under the License.                                                        *
 *                                                                           *
 *                                                                           *
 * This file is part of the BeanShell Java Scripting distribution.           *
 * Documentation and updates may be found at http://www.beanshell.org/       *
 * Patrick Niemeyer (pat@pat.net)                                            *
 * Author of Learning Java, O'Reilly & Associates                            *
 *                                                                           *
 *****************************************************************************/


package tsh;

import bsh.*;

import java.util.Hashtable;

/**
 * <p>Wrapper for primitive types in Bsh.  This is package public because it
 * is used in the implementation of some bsh commands.</p>
 *
 * <p>See the note in LHS.java about wrapping objects.</p>
 *
 * @author Pat Niemeyer
 * @author Daniel Leuck
 */
public final class TPrimitive implements ParserConstants, java.io.Serializable {
	/*
	Note: this class is final because we may test == Primitive.class in places.
	If we need to change that search for those tests.
	*/

    /*
    static Hashtable primitiveToWrapper = new Hashtable();
    static Hashtable wrapperToPrimitive = new Hashtable();
    static {
        primitiveToWrapper.put( Boolean.TYPE, Boolean.class );
        primitiveToWrapper.put( Byte.TYPE, Byte.class );
        primitiveToWrapper.put( Short.TYPE, Short.class );
        primitiveToWrapper.put( Character.TYPE, Character.class );
        primitiveToWrapper.put( Integer.TYPE, Integer.class );
        primitiveToWrapper.put( Long.TYPE, Long.class );
        primitiveToWrapper.put( Float.TYPE, Float.class );
        primitiveToWrapper.put( Double.TYPE, Double.class );
        wrapperToPrimitive.put( Boolean.class, Boolean.TYPE );
        wrapperToPrimitive.put( Byte.class, Byte.TYPE );
        wrapperToPrimitive.put( Short.class, Short.TYPE );
        wrapperToPrimitive.put( Character.class, Character.TYPE );
        wrapperToPrimitive.put( Integer.class, Integer.TYPE );
        wrapperToPrimitive.put( Long.class, Long.TYPE );
        wrapperToPrimitive.put( Float.class, Float.TYPE );
        wrapperToPrimitive.put( Double.class, Double.TYPE );
    }
    */
    static Hashtable wrapperMap = new Hashtable();

    static {
        wrapperMap.put(Boolean.TYPE, Boolean.class);
        wrapperMap.put(Byte.TYPE, Byte.class);
        wrapperMap.put(Short.TYPE, Short.class);
        wrapperMap.put(Character.TYPE, Character.class);
        wrapperMap.put(Integer.TYPE, Integer.class);
        wrapperMap.put(Long.TYPE, Long.class);
        wrapperMap.put(Float.TYPE, Float.class);
        wrapperMap.put(Double.TYPE, Double.class);
        wrapperMap.put(Boolean.class, Boolean.TYPE);
        wrapperMap.put(Byte.class, Byte.TYPE);
        wrapperMap.put(Short.class, Short.TYPE);
        wrapperMap.put(Character.class, Character.TYPE);
        wrapperMap.put(Integer.class, Integer.TYPE);
        wrapperMap.put(Long.class, Long.TYPE);
        wrapperMap.put(Float.class, Float.TYPE);
        wrapperMap.put(Double.class, Double.TYPE);
    }

    /**
     * The primitive value stored in its java.lang wrapper class
     */
    private Object value;

    private static class Special implements java.io.Serializable {
        private Special() {
        }

        public static final Special NULL_VALUE = new Special();
        public static final Special VOID_TYPE = new Special();
    }

    /*
        NULL means "no value".
        This ia a placeholder for primitive null value.
    */
    public static final TPrimitive NULL = new TPrimitive(Special.NULL_VALUE);

    public static TPrimitive TRUE = new TPrimitive(true);
    public static TPrimitive FALSE = new TPrimitive(false);

    /**
     * VOID means "no type".
     * Strictly speaking, this makes no sense here.  But for practical
     * reasons we'll consider the lack of a type to be a special value.
     */
    public static final TPrimitive VOID = new TPrimitive(Special.VOID_TYPE);

    // private to prevent invocation with param that isn't a primitive-wrapper
    public TPrimitive(Object value) {
        if (value == null)
            throw new InterpreterError(
                    "Use Primitve.NULL instead of Primitive(null)");

        if (value != Special.NULL_VALUE
                && value != Special.VOID_TYPE &&
                !isWrapperType(value.getClass())
        )
            throw new InterpreterError("Not a wrapper type: " + value.getClass());

        this.value = value;
    }

    public TPrimitive(boolean value) {
        this(value ? Boolean.TRUE : Boolean.FALSE);
    }



    public TPrimitive(byte value) {
        this(new Byte(value));
    }

    public TPrimitive(short value) {
        this(new Short(value));
    }

    public TPrimitive(char value) {
        this(new Character(value));
    }

    public TPrimitive(int value) {
        this(new Integer(value));
    }

    public TPrimitive(long value) {
        this(new Long(value));
    }

    public TPrimitive(float value) {
        this(new Float(value));
    }

    public TPrimitive(double value) {
        this(new Double(value));
    }

    /**
     * Return the primitive value stored in its java.lang wrapper class
     */
    public Object getValue() {
        if (value == Special.NULL_VALUE)
            return null;
        else if (value == Special.VOID_TYPE)
            throw new InterpreterError("attempt to unwrap void type");
        else
            return value;
    }

    public String toString() {
        if (value == Special.NULL_VALUE)
            return "null";
        else if (value == Special.VOID_TYPE)
            return "void";
        else
            return value.toString();
    }

    /**
     * Get the corresponding Java primitive TYPE class for this Primitive.
     *
     * @return the primitive TYPE class type of the value or Void.TYPE for
     * Primitive.VOID or null value for type of Primitive.NULL
     */
    public Class getType() {
        if (this == TPrimitive.VOID)
            return Void.TYPE;

        // NULL return null as type... we currently use null type to indicate
        // loose typing throughout bsh.
        if (this == TPrimitive.NULL)
            return null;

        return unboxType(value.getClass());
    }

    /**
     * Perform a binary operation on two Primitives or wrapper types.
     * If both original args were Primitives return a Primitive result
     * else it was mixed (wrapper/primitive) return the wrapper type.
     * The exception is for boolean operations where we will return the
     * primitive type either way.
     */
    public static Object binaryOperation(
            Object obj1, Object obj2, int kind)
            throws UtilEvalError {
        // special primitive types
        if (obj1 == NULL || obj2 == NULL)
            throw new UtilEvalError(
                    "Null value or 'null' literal in binary operation");
        if (obj1 == VOID || obj2 == VOID)
            throw new UtilEvalError(
                    "Undefined variable, class, or 'void' literal in binary operation");

        // keep track of the original types
        Class lhsOrgType = obj1.getClass();
        Class rhsOrgType = obj2.getClass();

        // Unwrap primitives
        if (obj1 instanceof TPrimitive)
            obj1 = ((TPrimitive) obj1).getValue();
        if (obj2 instanceof TPrimitive)
            obj2 = ((TPrimitive) obj2).getValue();

        Object[] operands = promotePrimitives(obj1, obj2);
        Object lhs = operands[0];
        Object rhs = operands[1];

        if (lhs.getClass() != rhs.getClass())
            throw new UtilEvalError("Type mismatch in operator.  "
                    + lhs.getClass() + " cannot be used with " + rhs.getClass());

        Object result;
        try {
            result = binaryOperationImpl(lhs, rhs, kind);
        } catch (ArithmeticException e) {
            throw new UtilTargetError("Arithemetic Exception in binary op", e);
        }


        if (result instanceof Boolean)
            return ((Boolean) result).booleanValue() ? TPrimitive.TRUE :
                    TPrimitive.FALSE;
            // If both original args were Primitives return a Primitive result
            // else it was mixed (wrapper/primitive) return the wrapper type
            // Exception is for boolean result, return the primitive
        else if ((lhsOrgType == TPrimitive.class && rhsOrgType == TPrimitive.class))
            return new TPrimitive(result);
        else
            return result;
    }

    static Object binaryOperationImpl(Object lhs, Object rhs, int kind)
            throws UtilEvalError {
        if (lhs instanceof Boolean)
            return booleanBinaryOperation((Boolean) lhs, (Boolean) rhs, kind);
        else if (lhs instanceof Integer)
            return intBinaryOperation((Integer) lhs, (Integer) rhs, kind);
        else if (lhs instanceof Long)
            return longBinaryOperation((Long) lhs, (Long) rhs, kind);
        else if (lhs instanceof Float)
            return floatBinaryOperation((Float) lhs, (Float) rhs, kind);
        else if (lhs instanceof Double)
            return doubleBinaryOperation((Double) lhs, (Double) rhs, kind);
        else
            throw new UtilEvalError("Invalid types in binary operator");
    }

    static Boolean booleanBinaryOperation(Boolean B1, Boolean B2, int kind) {
        boolean lhs = B1.booleanValue();
        boolean rhs = B2.booleanValue();

        switch (kind) {
            case EQ:
                return lhs == rhs ? Boolean.TRUE : Boolean.FALSE;

            case NE:
                return lhs != rhs ? Boolean.TRUE : Boolean.FALSE;

            case BOOL_OR:
            case BOOL_ORX:
                return lhs || rhs ? Boolean.TRUE : Boolean.FALSE;

            case BOOL_AND:
            case BOOL_ANDX:
                return lhs && rhs ? Boolean.TRUE : Boolean.FALSE;

            default:
                throw new InterpreterError("unimplemented binary operator");
        }
    }

    // returns Object covering both Long and Boolean return types
    static Object longBinaryOperation(Long L1, Long L2, int kind) {
        long lhs = L1.longValue();
        long rhs = L2.longValue();

        switch (kind) {
            // boolean
            case LT:
            case LTX:
                return lhs < rhs ? Boolean.TRUE : Boolean.FALSE;

            case GT:
            case GTX:
                return lhs > rhs ? Boolean.TRUE : Boolean.FALSE;

            case EQ:
                return lhs == rhs ? Boolean.TRUE : Boolean.FALSE;

            case LE:
            case LEX:
                return lhs <= rhs ? Boolean.TRUE : Boolean.FALSE;

            case GE:
            case GEX:
                return lhs >= rhs ? Boolean.TRUE : Boolean.FALSE;

            case NE:
                return lhs != rhs ? Boolean.TRUE : Boolean.FALSE;

            // arithmetic
            case PLUS:
                return new Long(lhs + rhs);

            case MINUS:
                return new Long(lhs - rhs);

            case STAR:
                return new Long(lhs * rhs);

            case SLASH:
                return new Long(lhs / rhs);

            case MOD:
                return new Long(lhs % rhs);

            // bitwise
            case LSHIFT:
            case LSHIFTX:
                return new Long(lhs << rhs);

            case RSIGNEDSHIFT:
            case RSIGNEDSHIFTX:
                return new Long(lhs >> rhs);

            case RUNSIGNEDSHIFT:
            case RUNSIGNEDSHIFTX:
                return new Long(lhs >>> rhs);

            case BIT_AND:
            case BIT_ANDX:
                return new Long(lhs & rhs);

            case BIT_OR:
            case BIT_ORX:
                return new Long(lhs | rhs);

            case XOR:
                return new Long(lhs ^ rhs);

            default:
                throw new InterpreterError(
                        "Unimplemented binary long operator");
        }
    }

    // returns Object covering both Integer and Boolean return types
    static Object intBinaryOperation(Integer I1, Integer I2, int kind) {
        int lhs = I1.intValue();
        int rhs = I2.intValue();

        switch (kind) {
            // boolean
            case LT:
            case LTX:
                return lhs < rhs ? Boolean.TRUE : Boolean.FALSE;

            case GT:
            case GTX:
                return lhs > rhs ? Boolean.TRUE : Boolean.FALSE;

            case EQ:
                return lhs == rhs ? Boolean.TRUE : Boolean.FALSE;

            case LE:
            case LEX:
                return lhs <= rhs ? Boolean.TRUE : Boolean.FALSE;

            case GE:
            case GEX:
                return lhs >= rhs ? Boolean.TRUE : Boolean.FALSE;

            case NE:
                return lhs != rhs ? Boolean.TRUE : Boolean.FALSE;

            // arithmetic
            case PLUS:
                return new Integer(lhs + rhs);

            case MINUS:
                return new Integer(lhs - rhs);

            case STAR:
                return new Integer(lhs * rhs);

            case SLASH:
                return new Integer(lhs / rhs);

            case MOD:
                return new Integer(lhs % rhs);

            // bitwise
            case LSHIFT:
            case LSHIFTX:
                return new Integer(lhs << rhs);

            case RSIGNEDSHIFT:
            case RSIGNEDSHIFTX:
                return new Integer(lhs >> rhs);

            case RUNSIGNEDSHIFT:
            case RUNSIGNEDSHIFTX:
                return new Integer(lhs >>> rhs);

            case BIT_AND:
            case BIT_ANDX:
                return new Integer(lhs & rhs);

            case BIT_OR:
            case BIT_ORX:
                return new Integer(lhs | rhs);

            case XOR:
                return new Integer(lhs ^ rhs);

            default:
                throw new InterpreterError(
                        "Unimplemented binary integer operator");
        }
    }

    // returns Object covering both Double and Boolean return types
    static Object doubleBinaryOperation(Double D1, Double D2, int kind)
            throws UtilEvalError {
        double lhs = D1.doubleValue();
        double rhs = D2.doubleValue();

        switch (kind) {
            // boolean
            case LT:
            case LTX:
                return lhs < rhs ? Boolean.TRUE : Boolean.FALSE;

            case GT:
            case GTX:
                return lhs > rhs ? Boolean.TRUE : Boolean.FALSE;

            case EQ:
                return lhs == rhs ? Boolean.TRUE : Boolean.FALSE;

            case LE:
            case LEX:
                return lhs <= rhs ? Boolean.TRUE : Boolean.FALSE;

            case GE:
            case GEX:
                return lhs >= rhs ? Boolean.TRUE : Boolean.FALSE;

            case NE:
                return lhs != rhs ? Boolean.TRUE : Boolean.FALSE;

            // arithmetic
            case PLUS:
                return new Double(lhs + rhs);

            case MINUS:
                return new Double(lhs - rhs);

            case STAR:
                return new Double(lhs * rhs);

            case SLASH:
                return new Double(lhs / rhs);

            case MOD:
                return new Double(lhs % rhs);

            // can't shift floating-point values
            case LSHIFT:
            case LSHIFTX:
            case RSIGNEDSHIFT:
            case RSIGNEDSHIFTX:
            case RUNSIGNEDSHIFT:
            case RUNSIGNEDSHIFTX:
                throw new UtilEvalError("Can't shift doubles");

            default:
                throw new InterpreterError(
                        "Unimplemented binary double operator");
        }
    }

    // returns Object covering both Long and Boolean return types
    static Object floatBinaryOperation(Float F1, Float F2, int kind)
            throws UtilEvalError {
        float lhs = F1.floatValue();
        float rhs = F2.floatValue();

        switch (kind) {
            // boolean
            case LT:
            case LTX:
                return lhs < rhs ? Boolean.TRUE : Boolean.FALSE;

            case GT:
            case GTX:
                return lhs > rhs ? Boolean.TRUE : Boolean.FALSE;

            case EQ:
                return lhs == rhs ? Boolean.TRUE : Boolean.FALSE;

            case LE:
            case LEX:
                return lhs <= rhs ? Boolean.TRUE : Boolean.FALSE;

            case GE:
            case GEX:
                return lhs >= rhs ? Boolean.TRUE : Boolean.FALSE;

            case NE:
                return lhs != rhs ? Boolean.TRUE : Boolean.FALSE;

            // arithmetic
            case PLUS:
                return new Float(lhs + rhs);

            case MINUS:
                return new Float(lhs - rhs);

            case STAR:
                return new Float(lhs * rhs);

            case SLASH:
                return new Float(lhs / rhs);

            case MOD:
                return new Float(lhs % rhs);

            // can't shift floats
            case LSHIFT:
            case LSHIFTX:
            case RSIGNEDSHIFT:
            case RSIGNEDSHIFTX:
            case RUNSIGNEDSHIFT:
            case RUNSIGNEDSHIFTX:
                throw new UtilEvalError("Can't shift floats ");

            default:
                throw new InterpreterError(
                        "Unimplemented binary float operator");
        }
    }

    /**
     * Promote primitive wrapper type to to Integer wrapper type
     */
    static Object promoteToInteger(Object wrapper) {
        if (wrapper instanceof Character)
            return new Integer(((Character) wrapper).charValue());
        else if ((wrapper instanceof Byte) || (wrapper instanceof Short))
            return new Integer(((Number) wrapper).intValue());

        return wrapper;
    }

    /**
     * Promote the pair of primitives to the maximum type of the two.
     * e.g. [int,long]->[long,long]
     */
    static Object[] promotePrimitives(Object lhs, Object rhs) {
        lhs = promoteToInteger(lhs);
        rhs = promoteToInteger(rhs);

        if ((lhs instanceof Number) && (rhs instanceof Number)) {
            Number lnum = (Number) lhs;
            Number rnum = (Number) rhs;

            boolean b;

            if ((b = (lnum instanceof Double)) || (rnum instanceof Double)) {
                if (b)
                    rhs = new Double(rnum.doubleValue());
                else
                    lhs = new Double(lnum.doubleValue());
            } else if ((b = (lnum instanceof Float)) || (rnum instanceof Float)) {
                if (b)
                    rhs = new Float(rnum.floatValue());
                else
                    lhs = new Float(lnum.floatValue());
            } else if ((b = (lnum instanceof Long)) || (rnum instanceof Long)) {
                if (b)
                    rhs = new Long(rnum.longValue());
                else
                    lhs = new Long(lnum.longValue());
            }
        }

        return new Object[]{lhs, rhs};
    }

    public static TPrimitive unaryOperation(TPrimitive val, int kind)
            throws UtilEvalError {
        if (val == NULL)
            throw new UtilEvalError(
                    "illegal use of null object or 'null' literal");
        if (val == VOID)
            throw new UtilEvalError(
                    "illegal use of undefined object or 'void' literal");

        Class operandType = val.getType();
        Object operand = promoteToInteger(val.getValue());

        if (operand instanceof Boolean)
            return booleanUnaryOperation((Boolean) operand, kind)
                    ? TPrimitive.TRUE : TPrimitive.FALSE;
        else if (operand instanceof Integer) {
            int result = intUnaryOperation((Integer) operand, kind);

            // ++ and -- must be cast back the original type
            if (kind == INCR || kind == DECR) {
                if (operandType == Byte.TYPE)
                    return new TPrimitive((byte) result);
                if (operandType == Short.TYPE)
                    return new TPrimitive((short) result);
                if (operandType == Character.TYPE)
                    return new TPrimitive((char) result);
            }

            return new TPrimitive(result);
        } else if (operand instanceof Long)
            return new TPrimitive(longUnaryOperation((Long) operand, kind));
        else if (operand instanceof Float)
            return new TPrimitive(floatUnaryOperation((Float) operand, kind));
        else if (operand instanceof Double)
            return new TPrimitive(doubleUnaryOperation((Double) operand, kind));
        else
            throw new InterpreterError(
                    "An error occurred.  Please call technical support.");
    }

    static boolean booleanUnaryOperation(Boolean B, int kind)
            throws UtilEvalError {
        boolean operand = B.booleanValue();
        switch (kind) {
            case BANG:
                return !operand;
            default:
                throw new UtilEvalError("Operator inappropriate for boolean");
        }
    }

    static int intUnaryOperation(Integer I, int kind) {
        int operand = I.intValue();

        switch (kind) {
            case PLUS:
                return operand;
            case MINUS:
                return -operand;
            case TILDE:
                return ~operand;
            case INCR:
                return operand + 1;
            case DECR:
                return operand - 1;
            default:
                throw new InterpreterError("bad integer unaryOperation");
        }
    }

    static long longUnaryOperation(Long L, int kind) {
        long operand = L.longValue();

        switch (kind) {
            case PLUS:
                return operand;
            case MINUS:
                return -operand;
            case TILDE:
                return ~operand;
            case INCR:
                return operand + 1;
            case DECR:
                return operand - 1;
            default:
                throw new InterpreterError("bad long unaryOperation");
        }
    }

    static float floatUnaryOperation(Float F, int kind) {
        float operand = F.floatValue();

        switch (kind) {
            case PLUS:
                return operand;
            case MINUS:
                return -operand;
            default:
                throw new InterpreterError("bad float unaryOperation");
        }
    }

    static double doubleUnaryOperation(Double D, int kind) {
        double operand = D.doubleValue();

        switch (kind) {
            case PLUS:
                return operand;
            case MINUS:
                return -operand;
            default:
                throw new InterpreterError("bad double unaryOperation");
        }
    }

    public int intValue() throws UtilEvalError {
        if (value instanceof Number)
            return ((Number) value).intValue();
        else
            throw new UtilEvalError("Primitive not a number");
    }

    public boolean booleanValue() throws UtilEvalError {
        if (value instanceof Boolean)
            return ((Boolean) value).booleanValue();
        else
            throw new UtilEvalError("Primitive not a boolean");
    }

    /**
     * Determine if this primitive is a numeric type.
     * i.e. not boolean, null, or void (but including char)
     */
    public boolean isNumber() {
        return (!(value instanceof Boolean)
                && !(this == NULL) && !(this == VOID));
    }

    public Number numberValue() throws UtilEvalError {
        Object value = this.value;

        // Promote character to Number type for these purposes
        if (value instanceof Character)
            value = new Integer(((Character) value).charValue());

        if (value instanceof Number)
            return (Number) value;
        else
            throw new UtilEvalError("Primitive not a number");
    }

    /**
     * Primitives compare equal with other Primitives containing an equal
     * wrapped value.
     */
    public boolean equals(Object obj) {
        if (obj instanceof TPrimitive)
            return ((TPrimitive) obj).value.equals(this.value);
        else
            return false;
    }

    /**
     * The hash of the Primitive is tied to the hash of the wrapped value but
     * shifted so that they are not the same.
     */
    public int hashCode() {
        return this.value.hashCode() * 21; // arbitrary
    }

    /**
     * Unwrap primitive values and map voids to nulls.
     * Non Primitive types remain unchanged.
     *
     * @param obj object type which may be bsh.Primitive
     * @return corresponding "normal" Java type, "unwrapping"
     * any bsh.Primitive types to their wrapper types.
     */
    public static Object unwrap(Object obj) {
        // map voids to nulls for the outside world
        if (obj == TPrimitive.VOID)
            return null;

        // unwrap primitives
        if (obj instanceof TPrimitive)
            return ((TPrimitive) obj).getValue();
        else
            return obj;
    }

    /*
        Unwrap Primitive wrappers to their java.lang wrapper values.
		e.g. Primitive(42) becomes Integer(42)
		@see #unwrap( Object )
    */
    public static Object[] unwrap(Object[] args) {
        Object[] oa = new Object[args.length];
        for (int i = 0; i < args.length; i++)
            oa[i] = unwrap(args[i]);
        return oa;
    }

    /*
     */
    public static Object[] wrap(Object[] args, Class[] paramTypes) {
        if (args == null)
            return null;

        Object[] oa = new Object[args.length];
        for (int i = 0; i < args.length; i++)
            oa[i] = wrap(args[i], paramTypes[i]);
        return oa;
    }

    /**
     * Wrap primitive values (as indicated by type param) and nulls in the
     * Primitive class.  Values not primitive or null are left unchanged.
     * Primitive values are represented by their wrapped values in param value.
     * <p/>
     * The value null is mapped to Primitive.NULL.
     * Any value specified with type Void.TYPE is mapped to Primitive.VOID.
     */
    public static Object wrap(
            Object value, Class type) {
        if (type == Void.TYPE)
            return TPrimitive.VOID;

        if (value == null)
            return TPrimitive.NULL;

        if (value instanceof Boolean)
            return ((Boolean) value).booleanValue() ? TPrimitive.TRUE :
                    TPrimitive.FALSE;

        if (type.isPrimitive() && isWrapperType(value.getClass()))
            return new TPrimitive(value);

        return value;
    }



    /**
     * Get the corresponding java.lang wrapper class for the primitive TYPE
     * class.
     * e.g.  Integer.TYPE -> Integer.class
     */
    public static Class boxType(Class primitiveType) {
        Class c = (Class) wrapperMap.get(primitiveType);
        if (c != null)
            return c;
        throw new InterpreterError(
                "Not a primitive type: " + primitiveType);
    }

    /**
     * Get the corresponding primitive TYPE class for the java.lang wrapper
     * class type.
     * e.g.  Integer.class -> Integer.TYPE
     */
    public static Class unboxType(Class wrapperType) {
        Class c = (Class) wrapperMap.get(wrapperType);
        if (c != null)
            return c;
        throw new InterpreterError(
                "Not a primitive wrapper type: " + wrapperType);
    }



    public static boolean isWrapperType(Class type) {
        return wrapperMap.get(type) != null && !type.isPrimitive();
    }

    /**
     * Cast a primitive value represented by its java.lang wrapper type to the
     * specified java.lang wrapper type.  e.g.  Byte(5) to Integer(5) or
     * Integer(5) to Byte(5)
     *
     * @param toType is the java TYPE type
     * @param value  is the value in java.lang wrapper.
     *               value may not be null.
     */
    static Object castWrapper(
            Class toType, Object value) {
        if (!toType.isPrimitive())
            throw new InterpreterError("invalid type in castWrapper: " + toType);
        if (value == null)
            throw new InterpreterError("null value in castWrapper, guard");
        if (value instanceof Boolean) {
            if (toType != Boolean.TYPE)
                throw new InterpreterError("bad wrapper cast of boolean");
            else
                return value;
        }

        // first promote char to Number type to avoid duplicating code
        if (value instanceof Character)
            value = new Integer(((Character) value).charValue());

        if (!(value instanceof Number))
            throw new InterpreterError("bad type in cast");

        Number number = (Number) value;

        if (toType == Byte.TYPE)
            return new Byte(number.byteValue());
        if (toType == Short.TYPE)
            return new Short(number.shortValue());
        if (toType == Character.TYPE)
            return new Character((char) number.intValue());
        if (toType == Integer.TYPE)
            return new Integer(number.intValue());
        if (toType == Long.TYPE)
            return new Long(number.longValue());
        if (toType == Float.TYPE)
            return new Float(number.floatValue());
        if (toType == Double.TYPE)
            return new Double(number.doubleValue());

        throw new InterpreterError("error in wrapper cast");
    }

}