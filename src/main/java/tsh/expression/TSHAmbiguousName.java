package tsh.expression;

import tsh.*;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;

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
            return getName(callstack.top()).toObjectNew(callstack, interpreter, forceClass, text);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EvalError(e.getMessage(), this, callstack);
        }
    }


    public LHS toLHS(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            return getName(callstack.top()).toLHS(callstack, interpreter);
        } catch (Exception e) {
            throw new EvalError(e.getMessage(), this, callstack);
        }
    }


    public Class toClass(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            return getName(callstack.top()).toClass();
        } catch (ClassNotFoundException e) {
            throw new EvalError(e.getMessage(), this, callstack);
        } catch (UtilEvalError e2) {
            // ClassPathException is a type of UtilEvalError
            throw e2.toEvalError(this, callstack);
        }
    }

}
