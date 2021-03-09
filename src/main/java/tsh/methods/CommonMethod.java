package tsh.methods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tsh.entity.TBigDecimal;
import tsh.util.Console;
import tsh.util.TNumberUtil;
import tsh.util.TStringUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonMethod {

    public void print(Object... t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            sb.append(TStringUtil.getString(t[i]));
            if (i < t.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
        List<String> list = Console.log.get();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(sb.toString());
        Console.log.set(list);
    }


    public Object time(Object... t) {
        return new TBigDecimal(new BigDecimal(System.currentTimeMillis()), 0);
    }

    public Object date(Object... t) {
        return new Date();
    }

    public Object sdf(Object... t) {
        String v1 = (String) t[0];
        if (t[1] instanceof TBigDecimal) {
            TBigDecimal v2 = (TBigDecimal) t[1];
            return new SimpleDateFormat(v1).format(new Date(TNumberUtil.strToLong(v2.getValue() + "")));
        } else if (t[1] instanceof Date) {
            return new SimpleDateFormat(v1).format((Date) t[1]);
        }
        return null;
    }


    public Object type(Object... t) {
        Object v = t[0];
        if (v instanceof Boolean) {
            return "boolean";
        } else if (v instanceof TBigDecimal) {
            return "number";
        } else if (v instanceof List) {
            return "list";
        } else if (v instanceof Map) {
            return "map";
        } else if (v instanceof String) {
            return "str";
        } else if (v instanceof Date) {
            return "date";
        }
        return "noKnow";
    }


    public Object sleep(Object... t) {
        try {
            if (t[0] instanceof TBigDecimal) {
                Thread.sleep(TNumberUtil.objToInt(((TBigDecimal) t[0]).getValue()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object json(Object ... t){
        if(t[0] instanceof  String){
            return JSONObject.parseObject(t[0].toString(),LinkedHashMap.class);
        }
        return new LinkedHashMap<>();
    }

}
