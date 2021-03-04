package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

public class TSHFormalParameters  extends SimpleNode {
    public String[] paramNames;

    int numArgs;

    public TSHFormalParameters(String id) {
        super(id);
    }


    void insureParsed() {
        if (paramNames != null)
            return;

        this.numArgs = jjtGetNumChildren();
        String[] paramNames = new String[numArgs];

        for (int i = 0; i < numArgs; i++) {
            TSHFormalParameter param = (TSHFormalParameter) jjtGetChild(i);
            paramNames[i] = param.name;
        }

        this.paramNames = paramNames;
    }

    public String[] getParamNames() {
        insureParsed();
        return paramNames;
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        insureParsed();
        Class[] paramTypes = new Class[numArgs];

        for (int i = 0; i < numArgs; i++) {
            TSHFormalParameter param = (TSHFormalParameter) jjtGetChild(i);
            paramTypes[i] = (Class) param.eval(callstack, interpreter);
        }

        return paramTypes;
    }






}
