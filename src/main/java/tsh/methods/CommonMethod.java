package tsh.methods;

import tsh.entity.TBigDecimal;
import tsh.util.NumberUtil;
import tsh.util.StringUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommonMethod {


    public void print(Object... t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            sb.append(StringUtil.getString(t[i]));
            if (i < t.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }


    public Object time(Object... t) {
        return new TBigDecimal(new BigDecimal(System.currentTimeMillis()), 0);
    }

    public Object date(Object ... t ) {
        return new Date();
    }

    public Object sdf(Object... t) {
        String v1 = (String) t[0];
        if (t[1] instanceof TBigDecimal) {
            TBigDecimal v2 = (TBigDecimal) t[1];
            return new SimpleDateFormat(v1).format(new Date(NumberUtil.strToLong(v2.getValue() + "")));
        } else if (t[1] instanceof Date) {
            return new SimpleDateFormat(v1).format((Date) t[1]);
        }
        return null;
    }


    public Object type(Object... t) {
        Object v = t[0];
        if (v instanceof Boolean) {
            return "bool";
        }else if(v instanceof TBigDecimal){
            return "number";
        }else if (v instanceof List) {
            return "list";
        } else if (v instanceof Map){
            return "map";
        }else if (v instanceof String){
            return "str";
        }else if (v instanceof Date){
            return "date";
        }
        return "noKnow";
    }



}
