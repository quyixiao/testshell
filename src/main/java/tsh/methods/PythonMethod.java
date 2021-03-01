package tsh.methods;

import tsh.entity.TBigDecimal;
import tsh.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

public class PythonMethod {

    public List range(Object ... t ){
        List list = new ArrayList();
        if(t !=null && t.length >= 2 ){
            Object t0 = t[0];
            Object t1 = t[1];
            if(t0 instanceof TBigDecimal && t1 instanceof TBigDecimal){
                for(int i = ((TBigDecimal) t0).getValue().intValue(); i < ((TBigDecimal) t1).getValue().intValue();i ++){
                    list.add(i);
                }
            }else if (t0 instanceof String && t1 instanceof String){
                for(int i = NumberUtil.objToInt(t0) ;i < NumberUtil.objToInt(t1);i ++){
                    list.add(i);
                }
            }
        }
        return list;
    }
}
