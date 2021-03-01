package tsh.expression;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;
import tsh.exception.EvalError;

import java.util.ArrayList;
import java.util.List;

public class TSHListInitializer extends SimpleNode {

    public TSHListInitializer(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int numInitializers = jjtGetNumChildren();
        List<Object> initializers = new ArrayList<>();

        for (int i = 0; i < numInitializers; i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
            Object currentInitializer = node.eval(callstack, interpreter);
            if (currentInitializer == Primitive.VOID)
                throw new EvalError(
                        "Void in array initializer, position" + i, this, callstack);
            initializers.add(currentInitializer);
        }
        return initializers;
    }


}
