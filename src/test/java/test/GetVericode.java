package test;

import tsh.util.HttpUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class GetVericode {


    public static void main(String[] args) {
        String userName = "16657136053";

        String url_pre = "http://localhost:8502/api";
        //String url_pre = "https://tltapi.ldxinyong.com/lt-web";
        String get_verify_code_url = url_pre + "/app/user/getVerifyCode";

        Map<String, Object> headers = new LinkedHashMap<>();

        headers.put("Content-Type", "application/json;charset=UTF-8");

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("mobile", userName);
        data.put("type", "L");
        data.put("geetest", "0");
        String result = HttpUtil.post(get_verify_code_url, headers, data);
        System.out.println(result);

    }
}
