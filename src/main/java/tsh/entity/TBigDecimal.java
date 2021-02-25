package tsh.entity;

import java.math.BigDecimal;

public class TBigDecimal {

    private BigDecimal value;

    private int precision;  //精度

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
}
