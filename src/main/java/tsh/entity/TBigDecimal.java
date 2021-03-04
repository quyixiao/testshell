package tsh.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

public class TBigDecimal {

    private BigDecimal value;

    private int precision;  //精度


    public TBigDecimal() {
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

    public static void main(String[] args) {
        TBigDecimal t = new TBigDecimal(new BigDecimal(10),2);
        System.out.println(JSON.toJSONString(t.toString(), SerializerFeature.UseSingleQuotes,SerializerFeature.WriteEnumUsingToString));
        System.out.println(t);
    }
}
