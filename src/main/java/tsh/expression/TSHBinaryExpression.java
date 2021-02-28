package tsh.expression;


import tsh.SimpleNode;
import tsh.constant.TParserConstants;

public class TSHBinaryExpression extends SimpleNode implements TParserConstants {
    public String kind;

    public TSHBinaryExpression(String id) {
        super(id );
    }


}
