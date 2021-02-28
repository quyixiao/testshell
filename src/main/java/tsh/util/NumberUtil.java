package tsh.util;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;


/**
 * 
 * 类NumberUtil.java的实现描述： 数字相关的工具类
 * @author 陈金虎 2015年12月20日 下午2:56:09
 */
public class NumberUtil {

    /**
     * <p>
     * String convert to int,if string is null or empty or string convert failed, return -1;else return
     * Integer.parseInt(string);
     * </p>
     * 
     * @code{Integer for example:
     * 
     * <pre>
     * strToInt(null) = -1;
     * strToInt('') = -1;
     * strToInt('  ') = -1;
     * strToInt('sb') = -1;
     * strToInt('100') = 100;
     * </pre>
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        return strToIntWithDefault(str, -1);
    }

    /**
     * <p>
     * String convert to int with defaultInt ,if string is null or empty or string convert failed, return default;else
     * return Integer.parseInt(string);
     * </p>
     * 
     * @code{Integer for example:
     * 
     * <pre>
     * strToInt(null) = defaultInt;
     * strToInt('') = defaultInt;
     * strToInt('  ') = defaultInt;
     * strToInt('sb') = defaultInt;
     * strToInt('100') = 100;
     * </pre>
     * @param str
     * @return
     */
    public static int strToIntWithDefault(String str, int def) {
        if (StringUtil.isBlank(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }
    
    public static Integer strToIntWithDefault(String str, Integer def) {
        if (StringUtil.isBlank(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * <p>
     * String convert to long,if obj is null or convert failed, return -1l;else return Long.parseLong(obj.toString());
     * </p>
     * <code> Long </code> for example:
     *
     * <pre>
     * objToLong(null) = -1l;
     * objToLong('sb') = -1l;
     * objToLong('100l') = 100l;
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static long objToLong(Object obj) {
        return objToLongWithDefault(obj, -1L);
    }

    /**
     * <p>
     * String convert to long,if obj is null or convert failed, return defLong;else return Long.parseLong(string);
     * </p>
     * <code> Long </code> for example:
     * 
     * <pre>
     * objToLongWithDefault(null, defLong) = defLong;
     * objToLongWithDefault('sb',defLong) = defLong;
     * objToLongWithDefault('100l',defLong) = 100l;
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static long objToLongWithDefault(Object obj, long def) {
        if (null == obj) {
            return def;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return def;
        }
    }
    
    public static Long objToLongWithDefault(Object obj, Long def) {
        if (null == obj) {
            return def;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return def;
        }
    }


    /**
     * <p>
     * String convert to int,if obj is null or convert failed, return -1;else return Integer.parseInt(obj.toString());
     * </p>
     * <code> Integer </code> for example:
     *
     * <pre>
     * objToInt(null) = -1;
     * objToInt('sb') = -1;
     * objToInt('100') = 100;
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static int objToInt(Object obj) {
        return objToIntWithDefault(obj, -1);
    }

    /**
     * <p>
     * String convert to int,if obj is null or convert failed, return defInt;else return Integer.parseInt(string);
     * </p>
     * 
     * <code> Integer </code>  for example:
     * 
     * <pre>
     * objToInt(null) = defInt;
     * objToInt('sb') = defInt;
     * objToInt('100') = 100;
     * </pre>
     * @param obj
     * @return
     */
    public static int objToIntWithDefault(Object obj, int def) {
        if (null == obj) {
            return def;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return def;
        }
    }

    public static long strToLong(String str) {
        return strToLongWithDefault(str, -1L);
    }

    public static long strToLongWithDefault(String str, long def) {
        if (StringUtil.isBlank(str)) {
            return def;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static double strToDouble(String str) {
        return strToDoubleWithDefault(str, -1d);
    }

    public static double strToDoubleWithDefault(String str, double def) {
        if (StringUtil.isBlank(str)) {
            return def;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return def;
        }
    }
    
    public static BigDecimal objToBigDecimalDefault(Object obj, BigDecimal defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Long objToLongDefault(Object obj, long defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Long objToLongDefault(Object obj, Long defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Boolean objToBooleanDefault(Object obj, Boolean defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Long objToPageLongDefault(Object obj, Long defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
        	Long pageNum = Long.parseLong(obj.toString());
            return pageNum == 0L ? 1L : pageNum;
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static int objToIntDefault(Object obj, int defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Integer objToIntDefault(Object obj, Integer defaultValue) {
        if (null == obj) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }



    public static Double objToDoubleWithDefault(Object obj, Double def) {
        if (null == obj) {
            return def;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return def;
        }
    }


    public NumberUtil() {
    }

    public static double format(double d, String format) {
        DecimalFormat df = new DecimalFormat(format);
        String ds = df.format(d);
        return Double.parseDouble(ds);
    }

    public static double format2(double d) {
        return BigDecimalUtil.decimal(d, 2);
    }

    public static String format2Str(double d) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        return df.format(BigDecimalUtil.decimal(d, 2));
    }

    public static String format3Str(double d) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(BigDecimalUtil.decimal(d, 2));
    }

    public static String format5Str(double d) {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(BigDecimalUtil.decimal(d, 5));
    }

    public static double format4(double d) {
        return BigDecimalUtil.decimal(d, 4);
    }

    public static double format6(double d) {
        return BigDecimalUtil.decimal(d, 6);
    }

    public static int compare(double x, double y) {
        BigDecimal val1 = BigDecimal.valueOf(x);
        BigDecimal val2 = BigDecimal.valueOf(y);
        return val1.compareTo(val2);
    }

    public static double ceil(double d, int len) {
        String str = Double.toString(d);
        int a = str.indexOf(".");
        if (a + 3 > str.length()) {
            a = str.length();
        } else {
            a += 3;
        }

        str = str.substring(0, a);
        return Double.parseDouble(str);
    }

    public static double ceil(double d) {
        return ceil(d, 2);
    }

    public static String format(double d) {
        if (d < 1.0E7D) {
            return d + "";
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(d);
        }
    }

    public static long getLong(String str) {
        if (StringUtil.isBlank(str)) {
            return 0L;
        } else {
            long ret = 0L;

            try {
                ret = Long.parseLong(str);
            } catch (NumberFormatException var4) {
                ret = 0L;
            }

            return ret;
        }
    }

    public static int getInt(String str) {
        if (StringUtil.isBlank(str)) {
            return 0;
        } else {
            boolean var1 = false;

            int ret;
            try {
                ret = Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                ret = 0;
            }

            return ret;
        }
    }

    public static double getDouble(String str) {
        if (StringUtil.isBlank(str)) {
            return 0.0D;
        } else {
            double ret = 0.0D;

            try {
                ret = Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                ret = 0.0D;
            }

            return ret;
        }
    }


    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static int getPrecision(String literal) {
        int a = literal.indexOf(".");
        if(a == -1 ){
            return 0;
        }
        return literal.length() - a -1 ;
    }

    public static void main(String[] args) {

        System.out.println(getPrecision("1"));
    }

}
