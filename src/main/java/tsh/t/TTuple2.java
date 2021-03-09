package tsh.t;

public class TTuple2<A, B> extends TTuple1<A> {
    private B second;

    public TTuple2(A a, B b) {
        super(a);
        second = b;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

}

