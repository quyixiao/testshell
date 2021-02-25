package tsh;

import bsh.ParserConstants;
import bsh.SimpleNode;

public class TSHUnaryExpression  extends SimpleNode implements ParserConstants {
    public String kind;
    public boolean postfix = false;

    TSHUnaryExpression(String id) {
        super(id);
    }
}
