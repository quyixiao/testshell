package tsh.expression;

import tsh.SimpleNode;

public class TSHVariableDeclarator extends SimpleNode {
    public String name ;
    public TSHVariableDeclarator(String id) {
        super(id);
    }
}
