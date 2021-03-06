package tsh.entity;

import tsh.expression.TSHMethodInvocation;

public class TMethodData {
    public TSHMethodInvocation invocation;


    public TMethodData(){
    }

    public TMethodData(TSHMethodInvocation invocation){
        this.invocation = invocation;
    }
}
