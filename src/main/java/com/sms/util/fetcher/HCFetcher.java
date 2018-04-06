package com.sms.util.fetcher;

import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

public class HCFetcher {

    private final Logger LOG = Logger.getLogger(HCFetcher.class);
    
    private PoolingHttpClientConnectionManager connManager = null;
    private CloseableHttpClient httpclient = null;
    private final Object mutex = new Object();
    
    private long getLastFetchTime = 0;// 删除GET请求时间
    private long postLastFetchTime = 0;// 上次POST请求时间
    private long politenessDelay = 120L;// 两次请求默认最小间隔
    
    private final static HCFetcher C = new HCFetcher();// 单体类
    private ClientConnectionMonitor connectionMonitor = null;

    public static HCFetcher getInstance() {
        return C;
    }

    private HCFetcher() {
        try {
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new TrustStrategy(){
                @Override
                public boolean isTrusted(final X509Certificate[] chain, final String authType)
                        throws CertificateException {
                    return true;
                }
            }).build();
            
//            SSLContext sslcontext = SSLContext.getInstance("TLS");
//            sslcontext.init(null, new TrustManager[] { trustAllManager },
//                    new java.security.SecureRandom());

            // Allow TLSv1 protocol only
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext, new String[] { "TLSv1" }, null,
//                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            
            // Create a registry of custom connection socket factories for
            // supported
            // protocol schemes.
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslsf).build();

            connManager = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            connManager.setMaxTotal(100);

            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom()
                    .setMaxHeaderCount(200)
                    .setMaxLineLength(2000)
                    .build();
            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints)
                    .build();
            // Configure the connection manager to use connection configuration
            // either
            // by default or for a specific host.
            connManager.setDefaultConnectionConfig(connectionConfig);

            // Configure total max or per route limits for persistent
            // connections
            // that can be kept in the pool or leased by the connection manager.
            connManager.setMaxTotal(100);
            connManager.setDefaultMaxPerRoute(30);

            // Create global request configuration
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setExpectContinueEnabled(true)
                    .setSocketTimeout(60000)
                    .setConnectTimeout(60000)
                    .setConnectionRequestTimeout(60000)
                    .build();

            connectionMonitor = new ClientConnectionMonitor(
                    connManager);
            connectionMonitor.start();

            httpclient = HttpClients.custom()
                    .setConnectionManager(connManager)
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .addInterceptorFirst(new HttpRequestInterceptor() {// 请求
                        @Override
                        public void process(final HttpRequest request, final HttpContext context) 
                                throws HttpException, IOException {
                            if (request.containsHeader("Accept-Encoding") == false) {
                                request.addHeader("Accept-Encoding", "gzip");
                            }
                            if (request.containsHeader("Content-Type") == false) {
                                request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                            }
                            if (request.containsHeader("User-Agent") == false) {
                                request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; rv:45.0) Gecko/20100101 Firefox/45.0");
                            }
                            request.addHeader("Connection", "keep-alive");
                            request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
                            request.addHeader("Accept-Charset", "utf-8");
                            request.addHeader("Accept-Encoding", "gzip");
                        }
                    }).addInterceptorFirst(new HttpResponseInterceptor() {// 响应
                        @Override
                        public void process(final HttpResponse response, final HttpContext context)
                                throws HttpException, IOException {
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                Header ceheader = entity.getContentEncoding();
                                if (ceheader != null) {
                                    HeaderElement[] codecs = ceheader.getElements();
                                    for (int i = 0; i < codecs.length; i++) {
                                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                            response.setEntity( new GzipDecompressingEntity(response.getEntity()) );
                                            return ;
                                        }
                                    }
                                }
                            }
                        }
                    })
                    .build();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 发送给请求
     * @param url
     * @return
     */
    public FetchEntity get(final String url) {
        synchronized (mutex) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - getLastFetchTime < politenessDelay) {
                try {
                    Thread.sleep(politenessDelay - (currentTimeMillis - getLastFetchTime));
                } catch (Exception e) {}
            }
            getLastFetchTime = System.currentTimeMillis();
            
            FetchEntity entity = null;
            HttpGet httpget = null;
            CloseableHttpResponse response = null;
            
            entity = new FetchEntity();
            entity.setSuccess(false);
            entity.setLocation(url);
            
            try {
                if (httpclient == null || url == null)
                    return entity;
                
                LOG.debug("----------------------------------------");
                LOG.debug("请求网址: "+url);
                LOG.debug("请求方法: GET");
                
                httpget  = new HttpGet(url);
                response = httpclient.execute(httpget);
                
                StatusLine statusLine = response.getStatusLine();
                LOG.debug("版本状态: "+statusLine);
                entity.setStatus(statusLine.getStatusCode());
                
                if(response.getLastHeader("Content-Encoding") == null){
                    LOG.debug("Content-Encoding: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Encoding"));
                }
                if(response.getLastHeader("Content-Length") == null){
                    LOG.debug("Content-Length: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Length"));
                }
                LOG.debug("----------------------------------------");
                
                if (entity.getStatus() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    
//                    byte[] buf = EntityUtils.toByteArray(httpEntity);
                    entity.setData(EntityUtils.toByteArray(httpEntity));
                    
                    Header contentTypeHeader = httpEntity.getContentType();
                    if(contentTypeHeader != null){
                        entity.setContentType(contentTypeHeader.getValue());
                    }
                    Header contentEncodingHeader = httpEntity.getContentEncoding();
                    if(contentEncodingHeader != null){
                        entity.setContentEncoding(contentEncodingHeader.getValue());
                    }
                    entity.setSuccess(true);
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    EntityUtils.consume(httpEntity);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // The underlying HTTP connection is still held by the response object
                // to allow the response content to be streamed directly from the network socket.
                // In order to ensure correct deallocation of system resources
                // the user MUST call CloseableHttpResponse#close() from a finally clause.
                // Please note that if response content is not fully consumed the underlying
                // connection cannot be safely re-used and will be shut down and discarded
                // by the connection manager.
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (Exception e) {}
            }
            return entity;
        }
    }
    public FetchEntity post(final String url, List<BasicNameValuePair> params, final byte[] data) {
        synchronized (mutex) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - postLastFetchTime < politenessDelay) {
                try {
                    Thread.sleep(politenessDelay - (currentTimeMillis - postLastFetchTime));
                } catch (Exception e) {}
            }
            postLastFetchTime = System.currentTimeMillis();

            FetchEntity entity = null;
            HttpPost httppost = null;
            CloseableHttpResponse response = null;

            entity = new FetchEntity();
            entity.setSuccess(false);
            entity.setLocation(url);

            try {
                if (httpclient == null || url == null)
                    return entity;

                LOG.debug("----------------------------------------");
                LOG.debug("请求网址: "+url);
                LOG.debug("请求方法: POST");

                httppost  = new HttpPost(url);
                if(params != null){
                    httppost.setEntity(new UrlEncodedFormEntity(params,"GBK"));
                    //  httppost.setEntity(new ByteArrayEntity(data));
                }

                response = httpclient.execute(httppost);

                StatusLine statusLine = response.getStatusLine();
                LOG.debug("版本状态: "+statusLine);
                entity.setStatus(statusLine.getStatusCode());

                if(response.getLastHeader("Content-Encoding") == null){
                    LOG.debug("Content-Encoding: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Encoding"));
                }
                if(response.getLastHeader("Content-Length") == null){
                    LOG.debug("Content-Length: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Length"));
                }
                LOG.debug("----------------------------------------");

                if (entity.getStatus() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();

//                    byte[] buf = EntityUtils.toByteArray(httpEntity);
                    entity.setData(EntityUtils.toByteArray(httpEntity));

                    Header contentTypeHeader = httpEntity.getContentType();
                    if(contentTypeHeader != null){
                        entity.setContentType(contentTypeHeader.getValue());
                    }
                    Header contentEncodingHeader = httpEntity.getContentEncoding();
                    if(contentEncodingHeader != null){
                        entity.setContentEncoding(contentEncodingHeader.getValue());
                    }
                    entity.setSuccess(true);
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    EntityUtils.consume(httpEntity);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // The underlying HTTP connection is still held by the response object
                // to allow the response content to be streamed directly from the network socket.
                // In order to ensure correct deallocation of system resources
                // the user MUST call CloseableHttpResponse#close() from a finally clause.
                // Please note that if response content is not fully consumed the underlying
                // connection cannot be safely re-used and will be shut down and discarded
                // by the connection manager.
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (Exception e) {}
            }
            return entity;
        }
    }
    public FetchEntity post(final String url, final byte[] data) {
        synchronized (mutex) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - postLastFetchTime < politenessDelay) {
                try {
                    Thread.sleep(politenessDelay - (currentTimeMillis - postLastFetchTime));
                } catch (Exception e) {}
            }
            postLastFetchTime = System.currentTimeMillis();
            
            FetchEntity entity = null;
            HttpPost httppost = null;
            CloseableHttpResponse response = null;
            
            entity = new FetchEntity();
            entity.setSuccess(false);
            entity.setLocation(url);
            
            try {
                if (httpclient == null || url == null)
                    return entity;
                
                LOG.debug("----------------------------------------");
                LOG.debug("请求网址: "+url);
                LOG.debug("请求方法: POST");
                
                httppost  = new HttpPost(url);
                if(data != null){
                    httppost.setEntity(new ByteArrayEntity(data));
                }
                
                response = httpclient.execute(httppost);
                
                StatusLine statusLine = response.getStatusLine();
                LOG.debug("版本状态: "+statusLine);
                entity.setStatus(statusLine.getStatusCode());
                
                if(response.getLastHeader("Content-Encoding") == null){
                    LOG.debug("Content-Encoding: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Encoding"));
                }
                if(response.getLastHeader("Content-Length") == null){
                    LOG.debug("Content-Length: NULL");
                } else {
                    LOG.debug(response.getLastHeader("Content-Length"));
                }
                LOG.debug("----------------------------------------");
                
                if (entity.getStatus() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    
//                    byte[] buf = EntityUtils.toByteArray(httpEntity);
                    entity.setData(EntityUtils.toByteArray(httpEntity));
                    
                    Header contentTypeHeader = httpEntity.getContentType();
                    if(contentTypeHeader != null){
                        entity.setContentType(contentTypeHeader.getValue());
                    }
                    Header contentEncodingHeader = httpEntity.getContentEncoding();
                    if(contentEncodingHeader != null){
                        entity.setContentEncoding(contentEncodingHeader.getValue());
                    }
                    entity.setSuccess(true);
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    EntityUtils.consume(httpEntity);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // The underlying HTTP connection is still held by the response object
                // to allow the response content to be streamed directly from the network socket.
                // In order to ensure correct deallocation of system resources
                // the user MUST call CloseableHttpResponse#close() from a finally clause.
                // Please note that if response content is not fully consumed the underlying
                // connection cannot be safely re-used and will be shut down and discarded
                // by the connection manager.
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (Exception e) {}
            }
            return entity;
        }
    }
    /**
     * 关闭资源
     */
    public synchronized void shutdown() {
        if (connectionMonitor != null) {
            try {
                connManager.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connectionMonitor.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public long getPolitenessDelay() {
        return politenessDelay;
    }

    public void setPolitenessDelay(long politenessDelay) {
        this.politenessDelay = politenessDelay;
    }
    /*
    private class AllTrustManager implements X509TrustManager{
        @Override
        public void checkClientTrusted(X509Certificate[] arg0,
                String arg1) throws CertificateException {

        }
        @Override
        public void checkServerTrusted(X509Certificate[] arg0,
                String arg1) throws CertificateException {

        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }
    */
}
