package tsh;

import bsh.SimpleNode;

public class TSHWhileStatement extends SimpleNode {

    /**
     * Set by Parser, default {@code false}
     */
    boolean isDoStatement;


    public TSHWhileStatement(String id) {
        super(id);
    }
}
