package com.sms.util;

public class Constansts {


  public final static String OAUTH_USER = "mingxi"; //下载账户
  public final static String OAUTH_PWD = "KN51f2h482mbrL1RAHYR"; //下载密码

  public final static String SMS_USER = "800449"; //短信账户

  public final static String SMS_PWD = "800449"; //短信密码

  public final static String url_base = "http://118.126.5.31:8084"; //下载url

  public final static String url_auth = url_base + "/remote/open/2/auth/getToken"; //下载权限url

  public final static String url_download = url_base + "/remote/open/2/somp/smsmt.download.concise"; //下载文件url

  public final static String url_test = url_base + "/remote/open/2/somp/test.getTime";

  public final static String url_sms_base = "http://222.73.44.38:8080"; //短信url

  public final static String url_sms_send = url_sms_base + "/mt"; //短信发送url

  public final static String url_sms_money = url_sms_base + "/bi"; //短信余额url


  public final static String url_region_baidu = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=";

  public final static String region_baidu_apikey = "1804286e37d3249cf2490378f4dea2e0";

  public final static String region_juhe_apikey = "2fe378abfbd6dc1bbc7f58582207cad3";

  public final static String url_region_juhe = "http://apis.juhe.cn/mobile/get?key="+region_juhe_apikey+"&phone=";


//  public static final String root = "/Users/zhouping/Documents/download/";
}
