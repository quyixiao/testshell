package tsh.methods;

import tsh.util.StringUtil;

public class StringMethod {

    public Object isBlank(Object... t) {
        if (t == null || t.length == 0) {
            return true;
        }
        if (t[0] == null) {
            return true;
        }
        if (StringUtil.isBlank(t[0].toString())) {
            return true;
        }
        return false;
    }


    public Object isNotBlank(Object... t) {
        return !((boolean) isBlank(t));
    }
}
