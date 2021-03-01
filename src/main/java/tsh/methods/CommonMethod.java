package tsh.methods;

public class CommonMethod {


    public void print(Object... t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            sb.append(t[i]);
            if (i < t.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

}
