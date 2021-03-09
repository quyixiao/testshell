//: net/mindview/util/FiveTuple.java
package tsh.t;


/*****
 *
 * 增加类型参数是一件简单的事情
 *
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 */
public class TTuple5<A, B, C, D, E> extends TTuple4<A, B, C, D> {
    private E fifth;

    public TTuple5(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }

    public E getFifth() {
        return fifth;
    }

    public void setFifth(E fifth) {
        this.fifth = fifth;
    }
}


///:~
