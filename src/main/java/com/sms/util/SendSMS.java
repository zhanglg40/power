package com.sms.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * SMS APIFOR HTTP
 * @author clsoftware
 *
 */
public class SendSMS {
	/**
	 * url encode
	 *
	 * @param str
	 *            String
	 * @param charset
	 *            String
	 * @return String
	 */
	public static String urlEncode(String str, String charset) {
		try {
			return java.net.URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return "";
		}
	}
	/**
	 * encode a specified string useing BASE64Encoder
	 *
	 * @param str
	 *            the string to be encoded
	 * @return String
	 * @throws Exception
	 */
	public static String BASE64Encoder(String str) throws
			Exception {
		return new sun.misc.BASE64Encoder().encode(str.getBytes());

	}
	/**
	 * Validate the string is empty or not
	 *
	 * @param str
	 *            the String to be validated
	 * @return boolean not math return false else return true
	 */
	public synchronized static boolean isEmpty(String str) {

		return ((str == null) || (str.trim().equals(""))) ? true : false;
	}

	public static String strReplace(String sBody, String sFrom, String sTo) {
		int i, j, k, l;
		if (sBody == null || sBody.equals(""))
			return "";
		if (sFrom == null || sFrom.equals(""))
			return sBody;
		i = 0;
		j = sFrom.length();
		k = sTo.length();
		StringBuffer sss = new StringBuffer(sBody.length());
		boolean bFirst = true;
		l = i;
		while (sBody.indexOf(sFrom, i) != -1) {
			i = sBody.indexOf(sFrom, i);
			sss.append(sBody.substring(l, i));
			sss.append(sTo);
			i += j;
			l = i;
		}
		sss.append(sBody.substring(l));
		return sss.toString();
	}

	/**
	 * request a url
	 *
	 * @param urlAddr
	 * @param map
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public static String sendUrlRequest(String urlAddr, Map map, String method,String character)
			throws Exception {
		boolean isSuccess = false;
		StringBuffer params = new StringBuffer();
		HttpURLConnection conn = null;
		if(map!=null){
			Iterator it = map.entrySet().iterator();


			while (it.hasNext()) {
				Entry element = (Entry) it.next();

				params.append(element.getKey());
				params.append("=");
				params.append(strReplace(element.getValue().toString(),"{islongsms}","" ));
				params.append("&");

			}
		}
		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}

		try {
			character=isEmpty(character)?"GBK":character;
			String u = urlAddr +(method.equalsIgnoreCase("GET")?params.toString():"");
			//System.out.println(u+" request method:"+method+" character=:"+character+ params.toString());



			URL url = new URL(u);

			conn = (HttpURLConnection) url.openConnection();
			if(!method.equalsIgnoreCase("GET")){
				conn.setDoOutput(true);
				conn.setRequestMethod(method);
				conn.setUseCaches(false);
				conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");



				conn.setRequestProperty("Content-type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Language",""+character );
				conn.setConnectTimeout(10000);
				conn.setRequestProperty("Accept-Charset", ""+character);
				//conn.setRequestProperty("Connection", "Close");
				conn.setRequestProperty("Content-length", String.valueOf(params
						.length()));
				conn.setDoInput(true);
				conn.connect();
			}else{
				conn.setRequestProperty("Content-length", "" + params.toString().getBytes("GBK").length);
				conn.setRequestProperty("Content-Type", "application/octet-stream");
				conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
				conn.setRequestProperty("Charset", "GBK");
			}
			if(!method.equalsIgnoreCase("GET")){
				OutputStreamWriter out = new OutputStreamWriter(conn
						.getOutputStream(), character);
				//System.out.println("$$$$$="+out.getEncoding());
				out.write(new String(params.toString().getBytes("GBK"),character));
				out.flush();
				out.close();
			}
			InputStream in = conn.getInputStream();
			InputStreamReader r = new InputStreamReader(in,character);
			LineNumberReader din = new LineNumberReader(r);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = din.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return "";
	}
    public static void sendSMS(String content , String mobile){
		String username="jiekouceshi";
		String password=null;
		try {
			password = BASE64Encoder("song");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String host="http://61.147.98.117:9015";
		try {
			content=urlEncode(content,"GBK");
			String url=host+"/servlet/UserServiceAPI?method=sendSMS&extenno=22&isLongSms=0&username=15802266547&password="+password+"&smstype=1&mobile="+mobile+"&content="+content;
			System.out.println(url);
			String result=sendUrlRequest(url, new HashMap(), "GET", "GBK");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username="jiekouceshi";
		String password=null;
		try {
			password = BASE64Encoder("song");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String host="http://61.147.98.117:9015";
		String content="【智慧用电云平台】尊敬的用户：鑫鑫牧业表1温度A出现异常，异常原因：温度过高，请及时处理。";

		try {
			content=urlEncode(content,"GBK");
			String url=host+"/servlet/UserServiceAPI?method=sendSMS&extenno=22&isLongSms=0&username=15802266547&password="+password+"&smstype=1&mobile=15922285983,18622645793&content="+content;
			System.out.println(url);
			String result=sendUrlRequest(url, new HashMap(), "GET", "GBK");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//			//post 提交测试
//			  content="【奇致软件】mycontentPOST提交测试333444";
//		      try {
//		    	  content=urlEncode(content,"GBK");
//		    	  String url=host+"/servlet/UserServiceAPI?method=sendSMS&extenno=22&isLongSms=0&username="+username+"&password="+password+"&smstype=1&mobile=17616249440&content="+content;
//		    	  System.out.println(url);
//				String result=sendUrlRequest(url, new HashMap(), "POST", "GBK");
//				System.out.println(result);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

	}

}
