package tsh.expression;


import tsh.SimpleNode;

public class TSHSwitchLabel extends SimpleNode {

    public boolean isDefault;
    public TSHSwitchLabel(String id) {
        super(id);
    }
}
