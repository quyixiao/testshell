package tsh;

import bsh.SimpleNode;

public class TSHMethodDeclaration extends SimpleNode {

    public String methodName;//方法名称

    TSHFormalParameters paramsNode;

    public TSHMethodDeclaration(String id) {
        super(id);
    }
}
