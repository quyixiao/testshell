package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.HashMap;
import java.util.Map;

public class TSHMapInitializer extends SimpleNode {

    public TSHMapInitializer(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int numInitializers = jjtGetNumChildren();
        Map<Object, Object> initializers = new HashMap<>();
        for (int i = 0; i < numInitializers; i = i + 2) {
            SimpleNode lNode = (SimpleNode) jjtGetChild(i);
            SimpleNode rNode = (SimpleNode) jjtGetChild(i + 1);

            Object key = lNode.eval(callstack, interpreter);
            Object value = rNode.eval(callstack, interpreter);
            if (key == Primitive.VOID || value == Primitive.VOID)
                throw new EvalError(
                        "Void in map initializer, position" + i + ", or " + (i + 1), this, callstack);
            initializers.put(key, value);
        }
        return initializers;
    }

}
