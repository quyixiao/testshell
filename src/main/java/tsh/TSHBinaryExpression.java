package tsh;

import bsh.ParserConstants;
import bsh.SimpleNode;

public class TSHBinaryExpression extends SimpleNode implements ParserConstants {
    public String kind;

    TSHBinaryExpression(String id) {
        super(id );
    }


}
