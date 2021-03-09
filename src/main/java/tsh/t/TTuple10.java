package tsh.t;

import java.io.Serializable;

public class TTuple10<A, B, C, D, E, F, G, H, I, J> extends TTuple9<A, B, C, D, E, F, G, H, I> implements Serializable {

    private J ten;

    public TTuple10(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
        super(a, b, c, d, e, f, g, h, i);
        ten = j;
    }

    public J getTen() {
        return ten;
    }

    public void setTen(J ten) {
        this.ten = ten;
    }


}
