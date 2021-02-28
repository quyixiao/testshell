package tsh.expression;

import tsh.*;
import tsh.exception.EvalError;

public class TSHAmbiguousName extends SimpleNode {
    public String text;

    public TSHAmbiguousName(String id) {
        super(id);
    }


    public Name getName(NameSpace namespace) {
        return namespace.getNameResolver(text);
    }

    public Object toObject(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        return toObject(callstack, interpreter, false);
    }

    Object toObject(CallStack callstack, Interpreter interpreter, boolean forceClass) throws EvalError {
        try {
            return getName(callstack.top()).toObject(callstack, interpreter, forceClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public LHS toLHS(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            return getName(callstack.top()).toLHS(callstack, interpreter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
