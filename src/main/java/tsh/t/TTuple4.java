//: net/mindview/util/FourTuple.java
package tsh.t;


/***
 *
 *
 * 355页
 *
 *
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public class TTuple4<A, B, C, D> extends TTuple3<A, B, C> {
    private D fourth;

    public TTuple4(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }


    public D getFourth() {
        return fourth;
    }

    public void setFourth(D fourth) {
        this.fourth = fourth;
    }
}
