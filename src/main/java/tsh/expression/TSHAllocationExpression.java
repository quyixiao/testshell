package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.ReflectError;
import tsh.exception.TargetError;
import tsh.util.ClassIdentifier;
import tsh.util.Reflect;

import java.lang.reflect.InvocationTargetException;

public class TSHAllocationExpression extends SimpleNode {
    public TSHAllocationExpression(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode type = (SimpleNode) jjtGetChild(0);
        SimpleNode args = (SimpleNode) jjtGetChild(1);
        TSHAmbiguousName name = (TSHAmbiguousName) type;
        return objectAllocation(name, (TSHArguments) args, callstack, interpreter);
    }

    private Object objectAllocation(TSHAmbiguousName nameNode, TSHArguments argumentsNode, CallStack callstack, Interpreter interpreter)
            throws EvalError {
        Object[] args = argumentsNode.getArguments(callstack, interpreter);
        if (args == null) {
            throw new EvalError("Null args in new.", this, callstack);
        }
        Object obj = nameNode.toObject(callstack, interpreter, true/*force class*/);
        Class type = null;
        if (obj instanceof ClassIdentifier) {
            type = ((ClassIdentifier) obj).getTargetClass();
        } else {
            throw new EvalError("Unknown class: " + nameNode.text, this, callstack);
        }
        return constructObject(type, args, callstack);
    }

    private Object constructObject(Class type, Object[] args, CallStack callstack) throws EvalError {
        Object obj;
        try {
            return Reflect.constructObject(type, args);
        } catch (ReflectError e) {
            throw new EvalError("Constructor error: " + e.getMessage(), this, callstack);
        } catch (InvocationTargetException e) {

            throw new TargetError("Object constructor", e.getTargetException(), this, callstack, true);
        }

    }


}
