package tsh.methods;

public class CommonMethod {
    public void println(Object ... t) {
        StringBuilder sb = new StringBuilder();
        for (Object o : t) {
            sb.append(o).append(" ");
        }
        System.out.println(sb.toString());
    }

}
