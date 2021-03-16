package tsh.methods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import tsh.entity.TBigDecimal;
import tsh.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonMethod {

    public void print(Object... t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            sb.append(TStringUtil.getString(t[i]));
            if (i < t.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
        List<String> list = Console.log.get();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(sb.toString());
        Console.log.set(list);
    }


    public Object time(Object... t) {
        return new TBigDecimal(new BigDecimal(System.currentTimeMillis()), 0);
    }

    public Object date(Object... t) {
        return new Date();
    }

    public Object sdf(Object... t) {
        String v1 = (String) t[0];
        if (t[1] instanceof TBigDecimal) {
            TBigDecimal v2 = (TBigDecimal) t[1];
            return new SimpleDateFormat(v1).format(new Date(TNumberUtil.strToLong(v2.getValue() + "")));
        } else if (t[1] instanceof Date) {
            return new SimpleDateFormat(v1).format((Date) t[1]);
        }
        return null;
    }


    public Object type(Object... t) {
        Object v = t[0];
        if (v instanceof Boolean) {
            return "boolean";
        } else if (v instanceof TBigDecimal) {
            return "number";
        } else if (v instanceof List) {
            return "list";
        } else if (v instanceof Map) {
            return "map";
        } else if (v instanceof String) {
            return "str";
        } else if (v instanceof Date) {
            return "date";
        }
        return "noKnow";
    }


    public Object sleep(Object... t) {
        try {
            if (t[0] instanceof TBigDecimal) {
                Thread.sleep(TNumberUtil.objToInt(((TBigDecimal) t[0]).getValue()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object json(Object... t) {
        if (t[0] instanceof String) {
            return JSONObject.parseObject(t[0].toString(), LinkedHashMap.class);
        }
        return new LinkedHashMap<>();
    }


    public static Object uploadFile(Object... t) throws Exception {
        List<File> fileList = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            String url = t[0].toString();
            Map<String, Object> headers = null;
            Map<String, String> files = null;
            if (t.length > 1) {
                headers = (Map<String, Object>) t[1];
            }
            Map<String, String> data = null;
            if (t.length > 2) {
                data = (Map<String, String>) t[2];
            }
            if (t.length > 3) {
                files = (Map<String, String>) t[3];
            }
            httpClient = HttpClientBuilder.create().build();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
            if (data != null) {
                int i = 0;
                StringBuilder sb = new StringBuilder(url);
                for (Map.Entry<String, String> d : data.entrySet()) {
                    if (i == 0) {
                        sb.append("?");
                    } else {
                        sb.append("&");
                    }
                    sb.append(d.getKey()).append("=").append(d.getValue());
                }
                url = sb.toString();
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            if (headers != null) {
                for (Map.Entry<String,Object> map : headers.entrySet()) {
                    if(map.getValue() instanceof Map){
                        httpPost.setHeader(map.getKey(), JSON.toJSONString(map.getValue()));
                    }else{
                        httpPost.setHeader(map.getKey(), map.getValue() + "");
                    }
                }
            }
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            //multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
            //File file = new File("F:\\Ken\\1.PNG");
            //FileBody bin = new FileBody(file);
            if (files != null) {
                ClassLoader classLoader = TClassUtils.getDefaultClassLoader();
                URL pathUrl = classLoader.getResource("");
                String path = pathUrl.getPath();
                for (Map.Entry<String, String> fileUrl : files.entrySet()) {
                    String value = fileUrl.getValue();
                    int i = value.lastIndexOf("/");
                    String fileName = value.substring(i + 1);
                    String filePath = path + fileName;
                    THttpUtil.downloadFile(value, filePath);
                    File file = new File(filePath);
                    fileList.add(file);
                    //multipartEntityBuilder.addBinaryBody("file", file,ContentType.create("image/png"),"abc.pdf");
                    //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
                    //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
                    multipartEntityBuilder.addBinaryBody(fileUrl.getKey(), file);
                }
            }
            //multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                String str = "";
                while (!TStringUtil.isEmpty(str = reader.readLine())) {
                    result.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
            if (httpResponse != null) {
                httpResponse.close();
            }
            //如果文件存在，则删除文件
            for (File file : fileList) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        return result.toString();
    }


}
