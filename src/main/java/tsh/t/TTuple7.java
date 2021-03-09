package tsh.t;

public class TTuple7<A, B, C, D, E, F, G> extends TTuple6<A, B, C, D, E, F> {

    private G seven;

    public TTuple7(A a, B b, C c, D d, E e, F f, G g) {
        super(a, b, c, d, e, f);
        seven = g;
    }

    public G getSeven() {
        return seven;
    }

    public void setSeven(G seven) {
        this.seven = seven;
    }
}
