

package tsh.expression;


import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.service.ImportHelpService;
import tsh.t.TTuple1;
import tsh.util.TStringUtil;
import tsh.util.Utils;

import java.util.Map;

public class TSHImportDeclaration extends SimpleNode {

    public String text;

    public TSHImportDeclaration(String id) {
        super(id);
    }

    public Object eval(Map<String, Object> globals, Map<String, Object> imports, ImportHelpService helpService, CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            String names[] = text.trim().split(",");
            for (String name : names) {
                if (TStringUtil.isBlank(name)) {
                    continue;
                }
                importVaribaleOrMethod(name, globals, imports, helpService, callstack, interpreter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Primitive.VOID;
    }

    public void importVaribaleOrMethod(String name, Map<String, Object> globals, Map<String, Object> imports,
                                       ImportHelpService helpService, CallStack callstack, Interpreter interpreter) throws Exception {
        NameSpace nameSpace = callstack.top();
        if (name.endsWith(".tsh")) {
            String script = helpService.getCode(name);
            TTuple1<Map<String, Object>> data = Utils.run(script, null, null, null, helpService).getData();
            Utils.batchInitVariable(nameSpace, data.getFirst());
        } else {
            Object object = Utils.getFromMap(imports, name);                    //先从imports中获取，如果没有获取到，再从全局 global中获取
            if (object == null) {
                object = Utils.getFromMap(globals, name);
                if (object != null) {
                    Utils.setVariable(nameSpace, name, object);
                }
            } else {
                Utils.setVariable(nameSpace, name, object);
            }
        }
    }


}

