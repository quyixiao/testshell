package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.entity.TVar;
import tsh.exception.EvalError;
import tsh.util.TStringUtil;

import java.util.ArrayList;
import java.util.List;

public class TSHFormalParameters extends SimpleNode {

    public String[] paramNames;
    public TVar[] defaultValues;

    Class[] paramTypes;

    int numArgs;

    public TSHFormalParameters(String id) {
        super(id);
    }


    void insureParsed(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (paramNames != null)
            return;

        this.numArgs = jjtGetNumChildren();
        List<String> names = new ArrayList<>();
        List<TVar> values = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < numArgs; i++) {
            SimpleNode parameter = (SimpleNode) jjtGetChild(i);
            if (parameter instanceof TSHFormalParameter) {
                TSHFormalParameter param = (TSHFormalParameter) parameter;
                names.add(param.name);
                flag++;
                TVar var = new TVar(param.name);
                if (TStringUtil.isNotBlank(param.kind)) {
                    var.setKind(param.kind);
                }
                values.add(var);
            } else {
                Object result = parameter.eval(callstack, interpreter);
                TVar var = values.get(flag - 1);
                var.setValue(result);
            }
        }
        this.numArgs = names.size();
        this.paramNames = names.toArray(new String[names.size()]);
        this.defaultValues = values.toArray(new TVar[values.size()]);
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public TVar[] getParamDefaultValues() {
        return defaultValues;
    }


    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        insureParsed(callstack, interpreter);
        Class[] paramTypes = new Class[numArgs];
        for (int i = 0; i < numArgs; i++) {
            Object paramTypeNode = jjtGetChild(i);
            if (paramTypeNode instanceof TSHFormalParameter) {
                TSHFormalParameter param = (TSHFormalParameter) paramTypeNode;
                paramTypes[i] = (Class) param.eval(callstack, interpreter);
            }
        }
        this.paramTypes = paramTypes;
        return paramTypes;
    }


}
