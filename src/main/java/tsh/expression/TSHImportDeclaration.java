

package tsh.expression;


import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.service.ImportHelpService;
import tsh.t.Tuple1;
import tsh.util.Utils;

import java.util.Map;

public class TSHImportDeclaration extends SimpleNode {

    public String text;


    public TSHImportDeclaration(String id) {
        super(id);
    }

    public Object eval(Map<String, Object> imports, ImportHelpService helpService, CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            NameSpace nameSpace = callstack.top();
            String name = text.trim();
            if (name.endsWith(".tsh")) {
                String script = helpService.getCode(name);
                Tuple1<Map<String, Object>> data = Utils.run(script, null, imports, helpService).getData();
                Utils.batchInitVariable(nameSpace,data.getFirst());
            } else {
                if (imports != null) {
                    Object object = imports.get(name);
                    Utils.setVariable(nameSpace, name, object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Primitive.VOID;
    }


}

