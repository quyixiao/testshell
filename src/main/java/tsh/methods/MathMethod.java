package tsh.methods;

import tsh.entity.TBigDecimal;
import tsh.util.TNumberUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * https://www.cnblogs.com/hpzyang/p/11440831.html
 */
public class MathMethod {

    //取大于等于x的最小的整数值，如果x是一个整数，则返回x
    public Object ceil(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.ceil(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 0);
    }

    //把y的正负号加到x前面，可以使用0
    public Object copysign(Object... t) {
        TBigDecimal v1 = (TBigDecimal) t[0];
        TBigDecimal v2 = (TBigDecimal) t[1];
        double a = Math.copySign(TNumberUtil.objToDoubleWithDefault(v1.getValue(), 0d),
                TNumberUtil.objToDoubleWithDefault(v2.getValue(), 0d)
        );
        return new TBigDecimal(new BigDecimal(a), v1.getPrecision());
    }

    //求x的余弦，x必须是弧度
    public Object cos(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.cos(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }

    //把x从弧度转换成角度
    public Object degrees(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.toDegrees(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 1);
    }

    //exp()返回math.e(其值为2.71828)的x次方
    public Object exp(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.exp(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }

    //expm1()返回math.e的x(其值为2.71828)次方的值减１
    public Object expm1(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.expm1(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }

    //fabs()返回x的绝对值
    public Object fabs(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.abs(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), v.getPrecision());
    }


    //factorial()取x的阶乘的值
    public Object factorial(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        int a = 1;
        for (int i = 1; i <= TNumberUtil.objToInt(v.getValue()); i++) {
            a *= i;
        }
        return new TBigDecimal(new BigDecimal(a), v.getPrecision());
    }

    //floor()取小于等于x的最大的整数值，如果x是一个整数，则返回自身
    public Object floor(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.floor(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 0);
    }


    //12. fmod()得到x/y的余数，其值是一个浮点数
    public Object fmod(Object... t) {
        TBigDecimal v1 = (TBigDecimal) t[0];
        TBigDecimal v2 = (TBigDecimal) t[1];
        double a = Math.floorMod(TNumberUtil.objToLong(v1.getValue()),
                TNumberUtil.objToLong(v2.getValue())
        );
        return new TBigDecimal(new BigDecimal(a), 1);
    }

    //14. 对迭代器里的每个元素进行求和操作
    public Object fsum(Object... t) {
        Object obj = t[0];
        int maxPrecision = 0;
        BigDecimal bigDecimal = new BigDecimal(0);
        if (obj instanceof List) {
            List<TBigDecimal> list = (List<TBigDecimal>) t[0];
            for (TBigDecimal l : list) {
                bigDecimal = bigDecimal.add(l.getValue());
                if (l.getPrecision() > maxPrecision) {
                    maxPrecision = l.getPrecision();
                }
            }
        } else {
            for (Object l : t) {
                TBigDecimal big = (TBigDecimal) l;
                bigDecimal = bigDecimal.add(big.getValue());
                if (big.getPrecision() > maxPrecision) {
                    maxPrecision = big.getPrecision();
                }
            }
        }
        return new TBigDecimal(bigDecimal, maxPrecision);
    }

    //22. log10()返回x的以10为底的对数
    public Object log10(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.log10(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }


    //26. pow()返回x的y次方，即x**y
    public Object pow(Object... t) {
        TBigDecimal v1 = (TBigDecimal) t[0];
        TBigDecimal v2 = (TBigDecimal) t[1];
        int p = v1.getPrecision() > v2.getPrecision() ? v1.getPrecision() : v2.getPrecision();
        double a = Math.pow(TNumberUtil.objToDoubleWithDefault(v1.getValue(), 0d),
                TNumberUtil.objToDoubleWithDefault(v2.getValue(), 0d)
        );
        return new TBigDecimal(new BigDecimal(a), p);
    }

    // 27. radians()把角度x转换成弧度
    public Object radians(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.toRadians(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }


    //28. sin()求x(x为弧度)的正弦值
    public Object sin(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.sin(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }


    //29. sqrt()求x的平方根
    public Object sqrt(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.sqrt(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }


    //30. tan()返回x(x为弧度)的正切值
    public Object tan(Object... t) {
        TBigDecimal v = (TBigDecimal) t[0];
        double a = Math.tan(TNumberUtil.objToDoubleWithDefault(v.getValue(), 0d));
        return new TBigDecimal(new BigDecimal(a), 16);
    }


}

