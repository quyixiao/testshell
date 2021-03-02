package tsh.expression;

import tsh.*;
import tsh.constant.TParserConstants;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;
import tsh.interfac.BshIterator;
import tsh.util.CollectionManager;
import tsh.util.StringUtil;

public class TSHForStatement extends SimpleNode implements TParserConstants {

    public String varName;

    private String label;

    public TSHForStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter, String label) throws EvalError {
        this.label = label;
        return eval(callstack, interpreter);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Class elementType = null;
        SimpleNode expression;
        SimpleNode statement = null;
        NameSpace enclosingNameSpace = callstack.top();
        SimpleNode firstNode = ((SimpleNode) jjtGetChild(0));
        int nodeCount = jjtGetNumChildren();
        expression = firstNode;
        if (nodeCount > 1) {
            statement = ((SimpleNode) jjtGetChild(1));
        }

        BlockNameSpace eachNameSpace = new BlockNameSpace(enclosingNameSpace);
        callstack.swap(eachNameSpace);
        final Object iteratee = expression.eval(callstack, interpreter);
        if (iteratee == Primitive.NULL) {
            throw new EvalError("The collection, array, map, iterator, or " + "enumeration portion of a for statement cannot be null.", this, callstack);
        }
        CollectionManager cm = CollectionManager.getCollectionManager();
        if (!cm.isBshIterable(iteratee)) {
            throw new EvalError("Can't iterate over type: " + iteratee.getClass(), this, callstack);
        }
        BshIterator iterator = cm.getBshIterator(iteratee);
        Object returnControl = Primitive.VOID;
        while (iterator.hasNext()) {
            try {
                Object value = iterator.next();
                if (value == null) {
                    value = Primitive.NULL;
                }
                if (elementType != null) {
                    eachNameSpace.setTypedVariable(varName/*name*/, elementType/*type*/, value/*value*/, null/*none*/);
                } else {
                    eachNameSpace.setVariable(varName, value, false);
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + varName, this, callstack);
            }
            boolean breakout = false; // switch eats a multi-level break here?
            if (statement != null) {
                // not empty statement
                Object ret = statement.eval(callstack, interpreter);
                if (ret instanceof ReturnControl) {
                    String retLabel = ((ReturnControl) ret).label;
                    if (StringUtil.isNotBlank(retLabel)) {                //处理 break label 的情况
                        if (!retLabel.equals(label)) {
                            returnControl = ret;
                        }
                        breakout = true;
                    } else {
                        switch (((ReturnControl) ret).kind) {
                            case RETURN:
                                returnControl = ret;
                                breakout = true;
                                break;
                            case CONTINUE:
                                break;
                            case BREAK:
                                breakout = true;
                                break;
                        }
                    }
                }
            }
            if (breakout) {
                break;
            }
        }
        callstack.swap(enclosingNameSpace);
        return returnControl;
    }

}
