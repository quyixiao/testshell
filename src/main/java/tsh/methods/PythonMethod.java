package tsh.methods;

import tsh.entity.TBigDecimal;
import tsh.util.TNumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PythonMethod {

    public List range(Object... t) {
        List list = new ArrayList();
        if (t != null && t.length == 2) {
            Object t0 = t[0];
            Object t1 = t[1];
            if (t0 instanceof TBigDecimal && t1 instanceof TBigDecimal) {
                for (int i = ((TBigDecimal) t0).getValue().intValue(); i < ((TBigDecimal) t1).getValue().intValue(); i++) {
                    list.add(new TBigDecimal(new BigDecimal(i), 0));
                }
            } else if (t0 instanceof String && t1 instanceof String) {
                for (int i = TNumberUtil.objToInt(t0); i < TNumberUtil.objToInt(t1); i++) {
                    list.add(new TBigDecimal(new BigDecimal(i), 0));
                }
            }
        } else if (t != null && t.length == 1) {
            Object t0 = t[0];
            if (t0 instanceof TBigDecimal) {
                for (int i = 0; i < ((TBigDecimal) t0).getValue().intValue(); i++) {
                    list.add(new TBigDecimal(new BigDecimal(i), 0));
                }
            } else if (t0 instanceof String) {
                for (int i = 0; i < TNumberUtil.objToInt(t0); i++) {
                    list.add(new TBigDecimal(new BigDecimal(i), 0));
                }
            }
        }
        return list;
    }


    public Object len(Object... t) {
        if (t[0] instanceof List) {
            return new TBigDecimal(new BigDecimal(((List) t[0]).size()), 0);
        } else if (t[0] instanceof Map) {
            return new TBigDecimal(new BigDecimal(((Map) t[0]).size()), 0);
        } else if (t[0] instanceof String) {
            return new TBigDecimal(new BigDecimal(t[0].toString().length()), 0);
        }
        return new TBigDecimal(BigDecimal.ZERO, 0);

    }
}
