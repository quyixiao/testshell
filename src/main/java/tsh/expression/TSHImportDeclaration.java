

package tsh.expression;


import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.service.ImportHelpService;

import java.util.Map;

public class TSHImportDeclaration extends SimpleNode {

    public TSHImportDeclaration(String id) {
        super(id);
    }

    public Object eval(Map<String, Object> imports, ImportHelpService helpService,CallStack callstack, Interpreter interpreter) throws EvalError {

        return null;
    }


}

