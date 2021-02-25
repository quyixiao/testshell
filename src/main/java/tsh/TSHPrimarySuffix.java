package tsh;

import bsh.SimpleNode;

public class TSHPrimarySuffix extends SimpleNode {
    public static final int
            CLASS = 0,
            INDEX = 1,
            NAME = 2,
            PROPERTY = 3;

    public int operation;
    Object index;
    public String field;

    public TSHPrimarySuffix(String id) {
        super(id);
    }
}
