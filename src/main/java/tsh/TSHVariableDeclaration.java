package tsh;

import bsh.*;

public class TSHVariableDeclaration extends SimpleNode {
    public Modifiers modifiers;

    public TSHVariableDeclaration(String id) {
        super(id);
    }


    /**
     * evaluate the type and one or more variable declarators, e.g.:
     * int a, b=5, c;
     */
    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        try {

        } catch (Exception e) {

        }
        return Primitive.VOID;
    }


}
