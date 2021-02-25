
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tsh.util;

import bsh.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {
    private static int DEF_DIV_SCALE = 10;

    public BigDecimalUtil() {
    }

    /**
     * 返回比较值
     *
     * @param v1
     * @param v2
     * @return -1 v1<v2， 0 v1==v2, 1 v1>v2
     */
    public static int compareTo(final BigDecimal v1, final BigDecimal v2) {
        return v1.compareTo(v2);
    }

    /**
     * 加法,保留小数点两位
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.add(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }

    public static double add(double... params) {
        BigDecimal b1 = new BigDecimal(0);
        double[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            double param = arr$[i$];
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.add(b2);
        }

        return b1.doubleValue();
    }





    public static BigDecimal add(BigDecimal... params) {
        BigDecimal b1 = new BigDecimal(0);
        BigDecimal[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            BigDecimal param = arr$[i$];
            b1 = b1.add(param);
        }
        return BigDecimalUtil.set2Scale(b1);
    }

    public static BigDecimal set2Scale(BigDecimal bigDecimal){
        if(bigDecimal == null){
            bigDecimal = BigDecimal.ZERO;
        }
        bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }


    public static BigDecimal setScale(BigDecimal bigDecimal,int num){
        if(bigDecimal == null){
            bigDecimal = BigDecimal.ZERO;
        }
        bigDecimal = bigDecimal.setScale(num,BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double sub(double... params) {
        int len = params.length;
        if (len < 1) {
            return 0.0D;
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(params[0]));

            for(int i = 1; i < len; ++i) {
                BigDecimal b2 = new BigDecimal(Double.toString(params[i]));
                b1 = b1.subtract(b2);
            }

            return b1.doubleValue();
        }

    }


    public static BigDecimal sub(BigDecimal... params) {
        int len = params.length;
        if (len < 1) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal b1 = params[0];

            for(int i = 1; i < len; ++i) {
                BigDecimal b2 =  params[i];
                b1 = b1.subtract(b2);
            }

            return b1;
        }
    }

    /**
     * 减法v1-v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.subtract(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 乘法 v1*v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.multiply(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 多个Bigdecimal相乘
     *
     * @param array
     * @return
     */
    public static BigDecimal multiply(BigDecimal... array) {
        BigDecimal result = BigDecimal.ONE;
        if (array == null || array.length == 0) {
            return BigDecimal.ZERO;
        }
        for (int i = 0; i < array.length; i++) {
            result = multiply(result, array[i]);
        }
        return result;
    }

    public static double mul(double... params) {
        BigDecimal b1 = new BigDecimal(1);
        double[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            double param = arr$[i$];
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.multiply(b2);
        }

        return b1.doubleValue();
    }

    /**
     * 除法 v1/v2(v2为0时未抛异常，注意不传空)
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.divide(v2, 2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 除法 v1/v2(v2为0时未抛异常，注意不传空)
     * 保留四位有效小数
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divideScaleFour(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;

        v1 = v1.divide(v2, 4, RoundingMode.HALF_UP);
        return v1;
    }


    /**
     * 除法 v1/v2(v2为0时未抛异常，注意不传空)
     * 保留四位有效小数
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divideScaleFourNew(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;

        if (v2.compareTo(BigDecimal.ZERO) == 0 && v1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        v1 = v1.divide(v2, 4, RoundingMode.HALF_UP);
        return v1;
    }


    public static BigDecimal mul(BigDecimal... params) {
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            BigDecimal param = arr$[i$];
            BigDecimal b2 = param;
            b1 = b1.multiply(b2);
        }

        return b1;
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("在做除法运算时，精度必须是正整数或零");
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("在做四舍五入处理时，精度必须是正整数或零");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static double round(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 2, 4).doubleValue();
    }

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("在做四舍五入处理时，精度必须是正整数或零");
        } else {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static double round(String v) {
        if (StringUtil.isBlank(v)) {
            return 0.0D;
        } else {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, 2, 4).doubleValue();
        }
    }

    public static double decimal(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度必须是正整数或零");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 1).doubleValue();
        }
    }

    public static BigDecimal getBigDecimal(String str) {
        if (!StringUtil.isNumber(str)) {
            str = "0";
        }

        BigDecimal decimal = new BigDecimal(str);
        return decimal.setScale(2, 6);
    }
}
