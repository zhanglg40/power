package com.sms.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpHandler {

    public static String code = "utf-8";

    public static String get(String url, Map<String, String> header,String sourceCoder,String destCoder) throws Exception {
        HttpGet httpget = new HttpGet(url);

        Set<Map.Entry<String, String>> set = header.entrySet();
        for (Map.Entry<String, String> entry : set) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }

        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(httpget);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            InputStream is = response.getEntity().getContent();
            String result = inStream2String(is,sourceCoder,destCoder);
            return result;
        }
        return null;
    }

    public static String get(String url) throws Exception {
        // url = "http://www.baidu.com";
//        System.out.println("请求：" + url);
        // System.out.println("开始=" + new Date());
        HttpGet httpget = new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpget);
        // System.out.println("结束=" + new Date());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            InputStream is = response.getEntity().getContent();
            String result = inStream2String(is,"GBK",code);
            // new String(Base64.decodeBase64(result.getBytes()))
//            System.out.println("返回：" + result);
//      System.out.println("请求：1" + result);
            return result;
        }
        return null;
    }

    private static String inStream2String(InputStream is,String sourceCoder,String destCoder) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(new String(baos.toByteArray(), sourceCoder).getBytes(), destCoder);
    }

    public static void main(String... args) throws Exception {
        // get("http://121.40.192.102:8080/erp/1/system/info/showStoreInfo?storeId=1");
        get("http://www.baidu.com");
        // get("http://118.126.5.31:8084/remote/open/2/auth/getToken/mingxi?t=1453364873048&s=bbff34cc65f85178975f052cf37024e47b6c39c3&v=l5nLbfongCzwHgAgWJlnsGkSLJgagDJD");
//    get("http://118.126.5.31:8084/remote/open/2/auth/getToken/mingxi?t=1453365340581&s=81f41c8ddec65b943b7db2b39da9c51bf0647bd3&v=OHoy7UlMvV7Ye1uYIeH4lGniO9tH6J2y");
    }
}
