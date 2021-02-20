package tsh;

public class TParserBase {


    public static boolean eq(String value, String... str) {
        for (String s : str) {
            if (!s.equals(value)) {
                return false;
            }
        }
        return true;
    }


    public static boolean eqOR(String value, String... str) {
        for (String s : str) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
