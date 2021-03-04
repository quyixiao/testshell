package tsh.methods;

import java.util.List;
import java.util.Map;

public class CommonMethod {


    public void print(Object... t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            sb.append(getString(t[i]));
            if (i < t.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public String getString(Object obj) {
        StringBuilder sb = new StringBuilder();
        if (obj instanceof List) {
            sb.append("[");
            int i = 0;
            for (Object o : (List<Object>) obj) {
                appendObject(sb, o);
                if (i < ((List<Object>) obj).size() - 1) {
                    sb.append(" ,");
                }
                i++;
            }
            sb.append("]");
        } else if (obj instanceof Map) {
            sb.append("{");
            int i = 0;
            for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) obj).entrySet()) {
                if (i > 0) {
                    sb.append(",");
                }
                Object key = entry.getKey();
                Object value = entry.getValue();
                appendObject(sb, key);
                sb.append(": ");
                appendObject(sb, value);
                i++;
            }
            sb.append("}");
        } else {
            sb.append(obj);
        }
        return sb.toString();
    }

    public void appendObject(StringBuilder sb, Object o) {
        if (o instanceof List || o instanceof Map) {
            sb.append(getString(o));
        } else {
            if (o instanceof String) {
                sb.append("'").append(o).append("'");
            } else {
                sb.append(o);
            }
        }
    }
}
