package tsh.expression;

import tsh.*;
import tsh.constant.ParserConstants;
import tsh.exception.EvalError;
import tsh.exception.UtilEvalError;
import tsh.interfac.BshIterator;
import tsh.util.CollectionManager;
import tsh.util.TStringUtil;

import java.util.Map;

public class TSHForStatement extends SimpleNode implements ParserConstants {

    public String kOrI;     //可能是 map 的 key ，也可能是 list 的索引

    public String varName;

    private String label;

    public TSHForStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter, String label) throws EvalError {
        this.label = label;
        return eval(callstack, interpreter);
    }

    // 下面针对  for( i, item  in [1,2,3,4,5]){print(i,item)} 和 for (i in range(5)){print(i)} 两种情况来分析
    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Class elementType = null;
        SimpleNode expression;
        SimpleNode statement = null;
        NameSpace enclosingNameSpace = callstack.top();

        SimpleNode firstNode = ((SimpleNode) jjtGetChild(0));
        int nodeCount = jjtGetNumChildren();
        expression = firstNode; // for 循环的第一个节点是表达式，range(5)
        if (nodeCount > 1) {
            statement = ((SimpleNode) jjtGetChild(1));
        }
        BlockNameSpace eachNameSpace = new BlockNameSpace(enclosingNameSpace);
        callstack.swap(eachNameSpace);
        final Object iteratee = expression.eval(callstack, interpreter);        // 得到一个 iterator
        if (iteratee == Primitive.NULL) {
            throw new EvalError("The collection, array, map, iterator, or " + "enumeration portion of a for statement cannot be null.", this, callstack);
        }
        CollectionManager cm = CollectionManager.getCollectionManager();
        if (!cm.isBshIterable(iteratee)) {
            if (iteratee instanceof Map) {              //如果是 Map 类型，则调用 map遍历的方法
                //对于 map 的处理
                return forMap(enclosingNameSpace, eachNameSpace, (Map) iteratee, statement, callstack, interpreter);
            }
            throw new EvalError("Can't iterate over type: " + iteratee.getClass(), this, callstack);
        }
        BshIterator iterator = cm.getBshIterator(iteratee);
        Object returnControl = Primitive.VOID;
        int i = 0;
        while (iterator.hasNext()) {        // iterator遍历
            try {
                Object value = iterator.next();
                if (value == null) {
                    value = Primitive.NULL;
                }
                if (TStringUtil.isNotBlank(kOrI)) {     // 遍历 iterator 的索引  ，从0开始
                    eachNameSpace.setVariable(kOrI, i, false);
                }
                if (elementType != null) {  // 设置局部变量
                    eachNameSpace.setTypedVariable(varName/*name*/, elementType/*type*/, value/*value*/, null/*none*/);
                } else {
                    eachNameSpace.setVariable(varName, value, false);
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + varName, this, callstack);
            }
            boolean breakout = false; // switch eats a multi-level break here?
            if (statement != null) {
                Object ret = statement.eval(callstack, interpreter);        // 执行 {} 内的内容
                if (ret instanceof ReturnControl) {     //如果返回结果是return 类型的
                    String retLabel = ((ReturnControl) ret).label;
                    if (TStringUtil.isNotBlank(retLabel)) {                //处理 break label 的情况
                        if (!retLabel.equals(label)) {
                            returnControl = ret;
                        }
                        breakout = true;
                    } else {
                        switch (((ReturnControl) ret).kind) {       //普通返回的处理
                            case RETURN:                            //如果是return ,则终止所有迭代
                                returnControl = ret;
                                breakout = true;
                                break;
                            case CONTINUE:                          //如果是continue继续迭代
                                break;
                            case BREAK:                             //如果是break，终止当前迭代
                                breakout = true;
                                break;
                        }
                    }
                }
            }
            if (breakout) {
                break;
            }
            i++;
        }
        callstack.swap(enclosingNameSpace);
        return returnControl;
    }


    public Object forMap(NameSpace enclosingNameSpace, BlockNameSpace eachNameSpace, Map<Object, Object> map, SimpleNode statement,
                         CallStack callstack, Interpreter interpreter) throws EvalError {
        Object returnControl = Primitive.VOID;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            try {
                eachNameSpace.setVariable(kOrI, entry.getKey(), false);
                eachNameSpace.setVariable(varName, entry.getValue(), false);
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + varName, this, callstack);
            }
            boolean breakout = false; // switch eats a multi-level break here?
            if (statement != null) {
                Object ret = statement.eval(callstack, interpreter); // {} 执行
                if (ret instanceof ReturnControl) {
                    String retLabel = ((ReturnControl) ret).label;
                    if (TStringUtil.isNotBlank(retLabel)) {                //处理 break label 的情况
                        if (!retLabel.equals(label)) {
                            returnControl = ret;
                        }
                        breakout = true;
                    } else {
                        switch (((ReturnControl) ret).kind) {
                            case RETURN:                    // return 返回多层循环
                                returnControl = ret;
                                breakout = true;
                                break;
                            case CONTINUE:                  // continue
                                break;
                            case BREAK:                     // break
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
