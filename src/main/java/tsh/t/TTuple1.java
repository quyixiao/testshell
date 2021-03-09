package tsh.t;

import java.io.Serializable;

public class TTuple1<A> implements Serializable {
    private A first;

    public TTuple1(A a) {
        first = a;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }


}

