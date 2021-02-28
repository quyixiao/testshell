package tsh.expression;

import tsh.SimpleNode;

public class TSHWhileStatement extends SimpleNode {

    /**
     * Set by Parser, default {@code false}
     */
    public boolean isDoStatement;


    public TSHWhileStatement(String id) {
        super(id);
    }
}
