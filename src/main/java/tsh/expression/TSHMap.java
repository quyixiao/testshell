package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.InterpreterError;

import java.util.Map;

public class TSHMap extends SimpleNode {

    public Map<Object,Object> value;


    public TSHMap(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (value == null)
            throw new InterpreterError("Null in bsh literal: " + value);

        return value;
    }




}
