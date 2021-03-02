package tsh.methods;

import tsh.entity.TBigDecimal;
import tsh.util.NumberUtil;

import java.math.BigDecimal;

public class MathMethod {

    public Object ceil(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.ceil(NumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 0);
    }
}
