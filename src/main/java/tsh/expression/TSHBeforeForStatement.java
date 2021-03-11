package tsh.expression;

import tsh.*;
import tsh.constant.ParserConstants;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;
import tsh.interfac.BshIterator;
import tsh.util.CollectionManager;
import tsh.util.TStringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSHBeforeForStatement extends SimpleNode implements ParserConstants {

    public String kOrI;     //可能是 map 的 key ，也可能是 list 的索引

    public String varName;

    private String label;

    public TSHBeforeForStatement(String id) {
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
        SimpleNode firstNode = ((SimpleNode) jjtGetChild(1));
        int nodeCount = jjtGetNumChildren();
        expression = firstNode;
        if (nodeCount > 1) {
            statement = ((SimpleNode) jjtGetChild(0));
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
        List<Object> returnControl = new ArrayList<>();
        int i = 0;
        while (iterator.hasNext()) {
            try {
                Object value = iterator.next();
                if (value == null) {
                    value = Primitive.NULL;
                }
                if (TStringUtil.isNotBlank(kOrI)) {
                    eachNameSpace.setVariable(kOrI, i, false);
                }
                if (elementType != null) {
                    eachNameSpace.setTypedVariable(varName, elementType/*type*/, value/*value*/, null/*none*/);
                } else {
                    eachNameSpace.setVariable(varName, value, false);
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + varName, this, callstack);
            }
            if (statement != null) {
                Object ret = statement.eval(callstack, interpreter);
                returnControl.add(ret);
            }
            i++;
        }
        callstack.swap(enclosingNameSpace);
        return returnControl;
    }



}
