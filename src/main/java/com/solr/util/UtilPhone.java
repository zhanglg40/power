package com.solr.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/** 
 * @author	lec
			E-mail:314121993@qq.com
 * @date 	创建时间:2015年9月23日 下午7:00:45 
 */
public class UtilPhone {
	public static void main(String[] args) {
		System.out.println(getMobileAddress("13173056346"));
	}
	
	/**根据手机号查询归属地
	 * @param mobile
	 * @return
	 * @author	lec
	 * @date 	创建时间:2015年9月23日 下午7:00:52 
	 */
	public static String getMobileAddress(String mobile) {
		String address = "";
		try {
			mobile = mobile.trim();
			if (mobile.matches("^(1)\\d{10}$")|| mobile.matches("^(01)\\d{10}$")) // 以13，15，18开头，后面九位全为数字
			{
				String url = "http://haoma.baidu.com/search?m="
						+ mobile;
				URLConnection connection = (URLConnection) new URL(url)
						.openConnection();
				connection.setDoOutput(true);
				InputStream os = connection.getInputStream();
				Thread.sleep(100);
				int length = os.available();
				byte[] buff = new byte[length];
				os.read(buff);
				String s = new String(buff, "utf8");
				
				address=Helper_Regex.matcher_return_string("<div class=\"category\">([\\S\\s]+?)</div>", s);
				address=Helper_Regex.matcher_return_string("<h2>([\\S\\s]+?)</h2>", address);
				s = null;
				buff = null;
				os.close();
				connection = null;
			}
		} catch (Exception e) {
			address = "未知";
			System.out.println("手机所属地查询失败====================");
		}
		return address;
	}
	
	public static String[] getPhoneAdderss(String phone) {
		String[] kong={"","",""};
		if (!(phone.matches("^(1)\\d{10}$")|| phone.matches("^(01)\\d{10}$"))) return kong;// 不是以13，15，18开头，后面九位全为数字 返回
		
		String prove="";
		String city="";
		String type="";
		String url="https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+phone+  "&co=&resource_id=6004&t=1449900702884&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110206169729979740571_1449900637799&_=1449900637817";
		String html= Helper_Net.readHTML(url,"gbk");
		System.out.println(html);
		prove=Helper_Regex.matcher_return_string("\"prov\":\"([\\S\\s]+?)\"", html);
		city=Helper_Regex.matcher_return_string("\"city\":\"([\\S\\s]+?)\"", html);
		type=Helper_Regex.matcher_return_string("\"type\":\"([\\S\\s]+?)\"", html);
		
		if ("北京".equals(city)) {
			prove="北京";
		}
		if ("上海".equals(city)) {
			prove="上海";
		}
		if ("天津".equals(city)) {
			prove="天津";
		}
		if ("重庆".equals(city)) {
			prove="重庆";
		}
		
		String[] strings={prove,city,type};
		return strings; 
	}
}
