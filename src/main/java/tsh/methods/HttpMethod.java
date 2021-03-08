package tsh.methods;

import tsh.util.HttpUtil;

import java.util.Map;

public class HttpMethod {

    public Object http1(Object... t) {
        String url = t[0].toString();
        String method = t[1].toString();
        Map<String, Object> headers = (Map<String, Object>) t[2];
        Map<String, Object> data = (Map<String, Object>) t[3];
        return HttpUtil.post(url, headers, data);
    }

    public Object http2(Object... t) {
        String url = t[0].toString();
        String method = t[1].toString();
        Map<String, Object> data = (Map<String, Object>) t[2];
        return HttpUtil.post(url, null, data);
    }


}
