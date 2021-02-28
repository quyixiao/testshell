package tsh.expression;

import tsh.SimpleNode;
import tsh.constant.TParserConstants;

public class TSHUnaryExpression extends SimpleNode implements TParserConstants {
    public String kind;
    public boolean postfix = false;

    public TSHUnaryExpression(String id) {
        super(id);
    }
}
