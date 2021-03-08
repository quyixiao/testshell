package test;

import com.alibaba.fastjson.JSON;
import tsh.methods.EncryptionMethod;
import tsh.util.HttpUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Login {


    public static void main(String[] args) {
        String userName = "16657136053";
        String url_pre = "https://tltapi.ldxinyong.com/lt-web";
        String login_register_url = url_pre + "/app/user/loginOrRegister";

        String token = "";
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("appVersion", 100);
        map.put("time", "1612324428713");
        map.put("id", "i_abab733db3554270bef62734753de27b_1612324428713_appStore");
        map.put("netType", "WIFI");
        map.put("userName", userName);
        map.put("uuid", "abab733db3554270bef62734753de27b");
        map.put("channel", "appStore");
        map.put("token", token);
        map.put("sign", "3402511929dab8845095d26581286955dc351394b4619e8218e234b2d1c43034");
        map.put("idfa", "3C80E5EF-1AB5-4100-AD72-0BC55C125D2F");

        String signStrBefore = "netType=" + map.get("netType") + "&userName=" + userName + "&time=" + map.get(
                "time") + "&appVersion=" + map.get("appVersion") + map.get("token");
        System.out.println(signStrBefore);

        map.put("sign",EncryptionMethod.sha256(signStrBefore));

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("appInfo", JSON.toJSON(map));

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("mobile", userName);
        data.put("verifyCode","888888");
        data.put("userName",userName);
        data.put("envData", "eyJwaG9uZVR5cGUiOiJvcyIsImRldk9TVmVyc2lvbiI6IjEwLjIifQ==");
        String result = HttpUtil.post(login_register_url, headers, data);
        System.out.println(result);

    }
}
