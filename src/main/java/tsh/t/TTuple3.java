package tsh.t;


/***
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class TTuple3<A, B, C> extends TTuple2<A, B> {
    private C third;

    public TTuple3(A a, B b, C c) {
        super(a, b);
        third = c;
    }


    public C getThird() {
        return third;
    }

    public void setThird(C third) {
        this.third = third;
    }
}
