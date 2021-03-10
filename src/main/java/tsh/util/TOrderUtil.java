package tsh.util;

import java.text.SimpleDateFormat;

public class TOrderUtil {


    public static String getUserPoolOrder(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(pre);
        return sb.append(dateformat.format(System.currentTimeMillis()))
                .append((int) (Math.random() * 100000)).toString();
    }

    public static String getMethodName() {
        return "lambda" + TOrderUtil.getUserPoolOrder("0");
    }
}
