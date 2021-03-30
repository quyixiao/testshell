package tsh.util;

import tsh.exception.EvalError;
import tsh.exception.TargetError;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TExceptionUtils {


    public static String dealException(Exception e) {
        return getExceptionInfo(e);
    }

    public static String dealException(Throwable e) {
        return getExceptionInfo(e);
    }

    private static String getExceptionInfo(Throwable e) {
        StringWriter sw = null;
        try {
            sw = new StringWriter();
            //将出错的栈信息输出到printWriter中
            if (e instanceof TargetError) {
                e.getCause().printStackTrace(new PrintWriter(sw, true));
            } else if (e instanceof EvalError) {
                e.printStackTrace(new PrintWriter(sw, true));
            } else {
                e.printStackTrace(new PrintWriter(sw, true));
            }
            String str = sw.toString();
            sw.flush();
            return str;
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            int a = 0;
            int b = 1;
            int c = b / a;
        } catch (Exception e) {
            String a = dealException(e);
            System.out.println(a);
        }
    }


}
