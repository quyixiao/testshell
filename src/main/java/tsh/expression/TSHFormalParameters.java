package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.ArrayList;
import java.util.List;

public class TSHFormalParameters extends SimpleNode {
    public String[] paramNames;
    public Object[] defaultValues;


    int numArgs;

    public TSHFormalParameters(String id) {
        super(id);
    }


    void insureParsed(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (paramNames != null)
            return;

        this.numArgs = jjtGetNumChildren();

        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        int flag = 0;
        for (int i = 0; i < numArgs; i++) {
            SimpleNode parameter = (SimpleNode) jjtGetChild(i);
            if (parameter instanceof TSHFormalParameter) {
                TSHFormalParameter param = (TSHFormalParameter) parameter;
                names.add(param.name);
                flag++;
                values.add(null);
            } else {
                Object result = parameter.eval(callstack, interpreter);
                values.set(flag - 1, result);
            }
        }
        this.numArgs = names.size();
        this.paramNames = names.toArray(new String[names.size()]);
        this.defaultValues = values.toArray(new Object[values.size()]);
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public Object[] getParamDefaultValues() {
        return defaultValues;
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        insureParsed(callstack, interpreter);
        Class[] paramTypes = new Class[numArgs];
        for (int i = 0; i < numArgs; i++) {
            TSHFormalParameter param = (TSHFormalParameter) jjtGetChild(i);
            paramTypes[i] = (Class) param.eval(callstack, interpreter);
        }
        return paramTypes;
    }


}
