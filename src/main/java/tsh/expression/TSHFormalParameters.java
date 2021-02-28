package tsh.expression;

import tsh.SimpleNode;

public class TSHFormalParameters  extends SimpleNode {
    public String[] paramNames;

    int numArgs;
    public TSHFormalParameters(String id) {
        super(id);
    }
}
