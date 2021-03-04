package tsh.entity;

import java.math.BigDecimal;

public class TBigDecimal {

    private BigDecimal value;

    private int precision;  //精度


    public TBigDecimal() {

    }

    public int intValue() {
        return value.intValue();
    }

    public long longValue() {
        return value.longValue();
    }

    public float floatValue() {
        return value.floatValue();
    }

    public double doubleValue() {
        return value.doubleValue();
    }


    public TBigDecimal(BigDecimal value, int precision) {
        this.value = value;
        this.precision = precision;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }


    @Override
    public String toString() {
        return value.setScale(precision, BigDecimal.ROUND_HALF_UP) + "";
    }


}
