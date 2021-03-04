package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;

import java.util.Arrays;
import java.util.List;

public class TSHVariableDeclarator extends SimpleNode {
    public String[] names = null;

    public TSHVariableDeclarator(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        Object value = null;
        if (jjtGetNumChildren() > 0) {
            SimpleNode initializer = (SimpleNode) jjtGetChild(0);
            value = initializer.eval(callstack, interpreter);
            if (value == Primitive.VOID) {
                throw new EvalError("Void initializer.", this, callstack);
            }
            try {
                if (initializer instanceof TSHListInitializer || value instanceof  List) {//如果是集合类型
                    List<Object> objectList = (List<Object>) value;
                    for (int i = 0; i < names.length; i++) {
                        if (objectList.size() > i) {
                            if (i < names.length - 1) {
                                namespace.setVariable(names[i], objectList.get(i), false);
                            } else {
                                namespace.setVariable(names[i], objectList.subList(i, objectList.size()), false);
                            }
                        } else {
                            namespace.setVariable(names[i], null, false);
                        }
                    }
                } else if (initializer instanceof TSHMapInitializer) {
                    namespace.setVariable(names[0], value, false);
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + Arrays.toString(names), this, callstack);
            }
        }

        return value;
    }

}
