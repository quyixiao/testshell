package tsh.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import tsh.methods.CommonMethod;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 陈金虎 2017年1月16日 下午11:41:47
 * @类描述：http请求工具
 * @注意：本内容仅限于杭州霖梓网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings("deprecation")
public class HttpUtil {

    protected static final CommonMethod logger = new CommonMethod();

    private static final int socketTimeout = 15000;

    private static final int connectTimeout = 15000;

    /**
     * 执行GET请求
     *
     * @param url
     * @param timeout
     * @return
     */
    public static String doGet(String url, int timeout) {
        logger.print("doGet url ", url);
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            return "";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        logger.print("doGet result = {}", result);
        return result;
    }

    /**
     * 执行GET请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, String param) {
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setConnectTimeout(20 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            out = new OutputStreamWriter(conn.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            return "";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String post(String url, Map<String, Object> headers, Map<String, Object> data) {
        String param = JSON.toJSONString(data);
        logger.print("doHttpPost begin url = {} param = {}", url, param);
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(20 * 1000);
            if(headers !=null){
                for (Map.Entry<String, Object> header : headers.entrySet()) {
                    if(header.getValue() instanceof String){
                        conn.setRequestProperty(header.getKey(), header.getValue().toString());
                    }else if (header.getValue() instanceof Map){
                        conn.setRequestProperty(header.getKey(),JSON.toJSONString(header.getValue()));
                    }
                }
            }
            out = new OutputStreamWriter(conn.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        logger.print("doHttpPost result = {}", result);
        return result;
    }


    /**
     * 发送POST请求，将参数放置到BODY里边
     *
     * @param url
     * @param param
     * @return
     */
    public static String doHttpPostToBody(String url, String param) {
        logger.print("doHttpPost begin url = {} param = {}", url, param);
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            out = new OutputStreamWriter(conn.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        logger.print("doHttpPost result = {}", result);
        return result;
    }

    /**
     * 发送POST请求，将参数放置到BODY里边
     *
     * @param url
     * @param param
     * @return
     */
    public static String doHttpPostJsonParam(String url, String param) {
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setConnectTimeout(2000);

            out = new OutputStreamWriter(conn.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送POST请求，将参数放置到BODY里边
     *
     * @param url
     * @param param
     * @param cookie
     * @return
     */
    public static String httpPost(String url, String param, String cookie) {
        BufferedReader in = null;
        OutputStreamWriter out = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestProperty("Cookie", cookie);
            out = new OutputStreamWriter(conn.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.print("发送失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HTTPS的POST请求，并且忽略证书验证,将参数放置到BODY里边
     *
     * @param urlString
     * @param query
     * @return
     */
    public static String doHttpsPostIgnoreCert(String urlString, String query) {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
        try {
            URL url = new URL(urlString);
            /*
             * use ignore host name verifier
             */
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            connection.setDoOutput(true);
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            connection.setSSLSocketFactory(ssf);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            // 把数据写入请求的Body
            out.write(query);
            out.flush();
            out.close();

            InputStream reader = connection.getInputStream();
            byte[] bytes = new byte[512];
            int length = reader.read(bytes);

            do {
                buffer.write(bytes, 0, length);
                length = reader.read(bytes);
            } while (length > 0);

            reader.close();
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        String repString = new String(buffer.toByteArray());
        return repString;
    }

    /**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {

        public boolean verify(String s, SSLSession sslsession) {
            logger.print("WARNING: Hostname is not matched for cert.");
            return true;
        }
    };

    /**
     * Ignore Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

        private X509Certificate[] certificates;

        public void checkClientTrusted(X509Certificate certificates[], String authType) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = certificates;
                logger.print("init at checkClientTrusted");
            }
        }

        public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
                logger.print("init at checkServerTrusted");
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };


    public static String postForm10s(String url, Map<String, String> params) {

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();


        logger.print("create httppost:" + url + ",params=" + params);

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            logger.print("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpost.setConfig(RequestConfig.custom()
                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(10000)
                    .setSocketTimeout(10000).build());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String body = invoke(httpclient, httpost);

        httpclient.getConnectionManager().shutdown();

        return body;

    }


    public static String post30s(String url, Map<String, String> params) {
        String body = null;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        logger.print("create httppost:" + url + ",params=" + params);
        HttpPost post = postForm30s(url, params);
        body = invoke(httpclient, post);
        httpclient.getConnectionManager().shutdown();
        return body;
    }


    public static String post(String url, Map<String, String> params) {
        String body = null;


        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();


        logger.print("create httppost:" + url + ",params=" + params);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }


    public static String post15s(String url, Map<String, String> params) {
        String body = null;


        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();


        logger.print("create httppost 15s:" + url + ",params=" + params);
        HttpPost post = postForm15(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();

        logger.print("response status: " + response.getStatusLine());

        String body = null;
        try {
            body = EntityUtils.toString(entity);
            logger.print("http请求 paseResponse，responseBody={}", body);
        } catch (Exception e) {
            logger.print("http请求 paseResponse出现异常");
        }

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (Exception e) {
            logger.print("http sendRequest出现异常", e);
        }
        return response;
    }


    private static HttpPost postForm30s(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            logger.print("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpost.setConfig(RequestConfig.custom()
                    .setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }


    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            logger.print("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpost.setConfig(RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000).build());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }


    private static HttpPost postForm15(String url, Map<String, String> params) {
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            logger.print("postForm15 set utf-8 form entity to httppost 15s ");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpost.setConfig(RequestConfig.custom()
                    .setConnectTimeout(15000)
                    .setConnectionRequestTimeout(15000)
                    .setSocketTimeout(15000).build());
        } catch (UnsupportedEncodingException e) {
            logger.print("url=" + url + ",params=" + JSON.toJSONString(params), e);
        }

        return httpost;
    }


}
