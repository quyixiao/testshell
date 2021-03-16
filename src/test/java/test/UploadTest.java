package test;

import tsh.methods.CommonMethod;

import java.util.HashMap;
import java.util.Map;

public class UploadTest {

    public static void main(String[] args) throws Exception {
        String a = "{'appVersion': '100', 'time': '1612324428713', 'id': 'i_abab733db3554270bef62734753de27b_1612324428713_appStore', 'netType': 'WIFI', 'userName': '16657136053', 'uuid': 'abab733db3554270bef62734753de27b', 'channel': 'appStore', 'token': '1a2a8f9387d747528c347bb8715e6536', 'sign': 'bf4d78b779eb9d34a9bfe01beb498f7974e60855eabf9e8edc4101d933abb245', 'idfa': '3C80E5EF-1AB5-4100-AD72-0BC55C125D2F'}";
        Map<String, String> headers = new HashMap<>();
        headers.put("appInfo", a);
        Map<String, String> data = new HashMap<>();
        data.put("userPhone", "16657136053");
        data.put("type", "front");
        String url = "https://tltfile.ldxinyong.com/file/upload/uploadOCRPhoto";
        Map<String, String> files = new HashMap<>();
        files.put("file", "http://my-wallet.oss-cn-hangzhou.aliyuncs.com/ppl/test/a05af06d460e27b8.jpeg");
        String result = (String) CommonMethod.uploadFile(url, headers, data, files);
        System.out.println(result);
    }
}
