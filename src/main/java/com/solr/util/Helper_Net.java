package com.solr.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Helper_Net {

	public static void main(String[] args) {
		//makeImg("http://imgcache.mysodao.com/img2/M01/92/38/CgAPDk9pO9i1Pc9EAAGqwIKPmVk577_700x0x1.JPG", "C:/Users/lechen/Desktop/test.jpg");
	}
	
    /**下载图片
     * @param imgUrl
     * @param fileURL
     * @author	lec
     * @date 	创建时间:2015年11月5日 下午8:04:27 
     */
    public static void makeImg(String imgUrl,String fileURL) {
    	
    	//代理ip
//    	String ip = "122.96.59.105";
//		String port = "82";
//    	System.getProperties().setProperty("proxySet", "true");
//    	System.getProperties().setProperty("http.proxyHost", ip);
//    	System.getProperties().setProperty("http.proxyPort", port);
    	
        try {
        	URL url = new URL(imgUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			//输入流
			InputStream is = conn.getInputStream();
			//输出流  
			FileOutputStream out = new FileOutputStream(new File(fileURL));
			
			//写
            byte[] buf = new byte[2048];
            int length = is.read(buf);
            while (length != -1) {
                out.write(buf, 0, length);
                length = is.read(buf);
            }
           
            is.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }  

	/**向指定 URL 发送get方法的请求
	 * @param url0
	 * @param fileSavePath
	 * @author	lec
	 * @date 	创建时间:2015年11月5日 下午2:09:19 
	 */
	public static void downHtmlCodeOrImg(String url0, String fileSavePath) {

		//编码
		String code="utf-8";
		
		//代理ip
		String ip = "120.27.114.30";
		String port = "80";
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);
		
		
		try {
			URL url = new URL(url0);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			InputStream is = conn.getInputStream();
			InputStreamReader readerObj = new InputStreamReader(is,code);
			BufferedReader br = new BufferedReader(readerObj);
			
			FileOutputStream fos = new FileOutputStream(new File(fileSavePath));
			Writer writer = new OutputStreamWriter(fos, code);
			BufferedWriter bw = new BufferedWriter(writer);
			
			int ch = 0;
			
			// 以字符方式显示文件内容 
			while((ch = br.read()) != -1) {
				//System.out.print((char)ch); 
				bw.write(ch); 
			}
			if(br!=null)
				br.close();
			if(bw!=null)
				bw.close();
			
			is.close();
			//fos.close();
			writer.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**向指定 URL 发送get方法的请求
	 * @param url
	 * @param code
	 * @return
	 * @author	lec
	 * @date 	创建时间:2015年11月5日 下午2:09:40 
	 */
	public static String readHTML(String url,String code) {
		
		
		/*String ip = "120.27.114.30";
		String port = "80";

		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);*/
		
		String string="";
		try {
			String strLine;
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			
			
			InputStream streamObj = conn.getInputStream();
			InputStreamReader readerObj = new InputStreamReader(streamObj,code);
			BufferedReader buffObj = new BufferedReader(readerObj);
			//System.out.println(buffObj.readLine());
			while ((strLine = buffObj.readLine()) != null) {
				string+=strLine;
			}
			buffObj.close();
		} catch (Exception e) {
			System.err.println("url   error");
		}
		return string;
	}
	
	
	 /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static void savePost(String url, String param,String fileSavePath) {

		//编码
		String code="utf-8";
		
		//代理ip
		String ip = "122.10.83.222";
		String port = "80";
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);
    	
        PrintWriter out = null;
        BufferedReader br = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStream is = conn.getInputStream();
			InputStreamReader readerObj = new InputStreamReader(is,code);
			br = new BufferedReader(readerObj);
			
            
			FileOutputStream fos = new FileOutputStream(new File(fileSavePath));
			Writer writer = new OutputStreamWriter(fos, code);
			BufferedWriter bw = new BufferedWriter(writer);
			
			int ch = 0;
			
			// 以字符方式显示文件内容 
			while((ch = br.read()) != -1) {
				//System.out.print((char)ch); 
				bw.write(ch); 
			}
			if(br!=null)
				br.close();
			if(bw!=null)
				bw.close();
			
			is.close();
			//fos.close();
			writer.close();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
       
    }    

    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {

		//编码
		String code="utf-8";
		
		//代理ip
		String ip = "120.198.236.10";
		String port = "80";
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);
    	
        PrintWriter out = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStream is = conn.getInputStream();
			InputStreamReader readerObj = new InputStreamReader(is,code);
			br = new BufferedReader(readerObj);
			
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(br!=null){
                    br.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String getHtml(String urlString) { 
    	//代理ip
		String ip = "122.96.59.105";
		String port = "82";
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);
		
    	try {
    		StringBuffer html = new StringBuffer(); 
    		java.net.URL url = new java.net.URL(urlString); //根据 String 表示形式创建 URL 对象。
    		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
    		java.io.InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream());//返回从此打开的连接读取的输入流。
    		java.io.BufferedReader br = new java.io.BufferedReader(isr);//创建一个使用默认大小输入缓冲区的缓冲字符输入流。
    		
    		String temp;
    		while ((temp = br.readLine()) != null) { //按行读取输出流
    			if(!temp.trim().equals("")){
    				html.append(temp).append("\n"); //读完每行后换行
    			}
    		}
    		br.close(); //关闭
    		isr.close(); //关闭
    		return html.toString(); //返回此序列中数据的字符串表示形式。
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    } 
    


}