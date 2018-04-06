package com.sms.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;



import org.apache.http.message.BasicNameValuePair;

import com.sms.util.fetcher.FetchEntity;
import com.sms.util.fetcher.HCFetcher;


public class SmsUtil {

  public static String sendSms(String tel, String text){

    try{
//      String url_send = Constansts.url_sms_send + "?un=" + PropertyUtil.get("sms_account_username") + "&pw="
//              + PropertyUtil.get("sms_account_pwd") + "&da=" + tel + "&sm=" + EncryptUtils.toHEX(text.getBytes("GBK"))
//              + "&dc=15&rd=1";

      String url_send = Constansts.url_sms_send + "?un=" + Constansts.SMS_USER + "&pw="
              + Constansts.SMS_PWD + "&da=" + tel + "&sm=" + EncryptUtils.toHEX(text.getBytes("GBK"))
              + "&dc=15&rd=1";
      System.out.println(url_send);
//      return "ss";
      return HttpHandler.get(url_send);
    }catch (Exception e){

    }
    return "发送失败";
  }

  public static int money() throws Exception {
//    String url_send = Constansts.url_sms_money + "?un=" + PropertyUtil.get("sms_account_username") + "&pw="
//        + PropertyUtil.get("sms_account_pwd");

    String url_send = Constansts.url_sms_money + "?un=" +Constansts.SMS_USER + "&pw="
        + Constansts.SMS_PWD;
    String result = HttpHandler.get(url_send);
    if(result.indexOf("op=bi&bl=")!=-1)
    {
      String rest = result.replace("op=bi&bl=","");

      return Integer.parseInt(rest);
    }

    throw new Exception("http error "+result);
  }


  /**
   * 将字符串中逗号前后的空格去掉
   * @param sstring
   * @return
   */
  public static  String deleteSpace(String sstring)
  {
    StringBuffer sbf=new StringBuffer();
    String[] sArray=sstring.split(",");
    for (String sarray:  sArray) {
      sarray=sarray.trim();
      sbf.append(sarray).append(",");
    }
    return sbf.substring(0,sbf.length()-1).toString();
  }


  public static void main(String... args) throws Exception {
//     sendSms();
    System.out.println(money());
    HCFetcher fetcher = HCFetcher.getInstance();
    String url="http://61.147.98.117:9015/servlet/UserServiceAPI?method=sendSMS&username=jiekouceshi&password="
            +BASE64Encoder("123")+"&smstype=1&mobile=15922285983";
    System.out.print(url);

    List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
  //  String   name=new   String("地方name".getBytes("GBK")); 
    String s1 = "hello中国人"; 
    String gbk = new String(s1.getBytes("iso-8859-1"), "gbk"); 
    params.add(new BasicNameValuePair("content",s1));
  
    FetchEntity entity = fetcher.post(url,params ,null);
    if(entity.isSuccess()){
        System.out.println("sucess!!!!!!!!!!");
    }else{
        System.out.println("!!!!!!!!!");
    }
    // System.out.println(EncryptUtils.toHEX("ABC".getBytes()));

//    System.out.println(sendSms("18662548200", "又是收不到 退订回N"));

//    String url = "http://222.73.44.38:8080/mo?un="+Constansts.SMS_USER+"&pw="+Constansts.SMS_PWD;

//    HttpHandler.get(url);
  }
  public synchronized static String BASE64Encoder(String str) throws
  Exception {
return new sun.misc.BASE64Encoder().encode(str.getBytes());

}
}
