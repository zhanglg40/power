package com.thinkgem.jeesite.common.config;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


/**
 * 全局常量表
 * @author Administrator
 *
 */
public class GConstants {
    
    private static Logger LOG = Logger.getLogger(GConstants.class);
    
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    
    // 常用系统属性
    public static final String   LS = System.getProperty("line.separator");// 文本换行符
    public static final String   FS = "/";//System.getProperty("file.separator");// 文件分隔符
    public static final int      BUFFER_SIZE = 8192;// 新建BYTE缓存大小，8*1024;
    public static final String   SEMICOLON   = ";";// 分号
    
    public static  final String[] resources = new String[]{"mybatis-refresh.properties", 
            "jdbc.properties", "ejweb.properties"};// 越靠后越配置的值权限越高，即后面的同名变量会覆盖前面的变量
    // 文件过滤，
    public final static  Map<String, Boolean> FILE_FILTERS = new HashMap<String, Boolean>(10);
    private static final Properties P = new Properties();// 属性变
    public static final  GConstants C = new GConstants();// 单实体类
    
    // 是|否
    public static final String YES    = "1";
    public static final String NO     = "0";
    public static final String OK     = "OK";
    public static final String EMPTY  = "EMPTY";
    
//    public static final String USER_TYPE_INNER  = "INNER";
//    public static final String USER_TYPE_OUTER  = "OUTER";
    
    public static final String IM_TYPE_INNER   = "INNER";// 内部联系人
    public static final String IM_TYPE_OUTER   = "OUTER";// 外部联系人
    public static final String IM_TYPE_SEAT    = "SEAT";// 席位联系人
    public static final String IM_TYPE_STTION  = "STATION";// 场站联系人
    
    public static final String  SIGN_PRIVATE_KEY        = GConstants.getValue("content.sign.private.key", "FvNMhdkN5eTsgAfU2YHGJ2RfpKVi3omn");// 签名私钥
    public static final boolean IS_VERIFY_CONTENT_SIGN  = GConstants.getBoolean("is.verify.content.sign", false);// 是否验证签名
    
    // 将基本配置导入为固定常量
//    public static final String  SERVER_HTTP_URL    = GConstants.getValue("server.http.url");// 服务器HTTP基本地址，必须也反斜杠结尾
//    public static final String  SERVER_HTTPS_URL   = GConstants.getValue("server.https.url");// 服务器HTTPS基本地址，必须也反斜杠结尾
//    public static final String  SERVER_IP_URL      = GConstants.getValue("server.ip.url");// 服务器IP基本地址，必须也反斜杠结尾
    
//    public static final String  WAP_PAY_RETURN_URL = GConstants.getValue("wap.pay.return.url");// WAP支付PHP回调地址
//    public static final String  PUSH_SERVER_URL    = GConstants.getValue("push.server.url");// 推送服务器地址
    
    public static final String  FILE_UPLOAD_DIR    = GConstants.getValue("file.upload.dir");// 文件上传地址
    public static final String  FILE_PREFIX_URL    = GConstants.getValue("file.prefix.url");// 文件读取URL前缀
    public static final String  FILE_TMP_DIR       = FILE_UPLOAD_DIR+"tmp";// 临时文件地址
    public static final String  FILE_IMAGE_THUMBS  = "thumbs";// 缩略图文件顶级路径名称 201605/thumbs/images/user/png/0d7ed36548c9ea6586363d4de5cb322c.png
    public static final String  FILE_IMAGE_ACTUALS = "actuals";// 原图文件顶级路径名称  201605/actuals/images/user/png/0d7ed36548c9ea6586363d4de5cb322c.png
    public static final boolean FILE_ALLOW_THUMBS  = GConstants.getBoolean("file.image.thumb.is.allow", true);
    public static final int     PAGE_SIZE          = GConstants.getIntValue("page.size", 10);// 默认分页条数，默认10条
    public static final int     MAX_UPLOAD_SIZE    = GConstants.getIntValue("file.max.upload.size", 10485760);// 允许最多上传文件大小，默认10M
    // 是否开启手机视图拦截器
    public static final boolean IS_MOBILE_INTERCEPTOR  = GConstants.getBoolean("is.mobile.interceptor", true);
    
    public static final String  DEFAULT_USER_ROLE_CODE      = GConstants.getValue("default.user.role.code", "_USER");// 默认用户角色
//    public static final String  DEFAULT_USER_AVATAR      = GConstants.getValue("user.default.avatar", "");// 默认用户角色
    public static final String  DEFAULT_SEAT_PHOTO      = GConstants.getValue("seat.default.photo", "");// 默认用户角色
    public static final String  DEFAULT_APP_CODE      = GConstants.getValue("default.app.code", "00000");
    
    public static final String  JDAIR_BASE_API      = GConstants.getValue("jdair.api.base.url", "");
    public static final String  JDAIR_BASE_PARAM      = GConstants.getValue("jdair.api.base.param", "");
    public static final String  JDAIR_SMS_API      = GConstants.getValue("jdair.api.sms.url", "http://user.jdair.net/ussinterface/uss/json/mobile/messSend.json?ai.cp=10.68.26.52&ai.cc=5");
    
    private static final String CONF_DESC_KEY      = "2012PinganVitality075522628888ForShenZhenBelter075561869839";
    public static final String  JDBC_DRIVER_CLASS  = "jdbc.driver.class";
    public static final String  JDBC_URL           =  "jdbc.url";
    public static final String  JDBC_USERNAME      = "jdbc.username";
    public static final String  JDBC_PASSWORD      = "jdbc.password";
    
    // 电话类型
    public static final String  CALL_TYPE_MOBILE      = "MOBILE";// 移动电话
    public static final String  CALL_TYPE_HW_CORNET      = "HW_CORNET";// 华为短号
    public static final String  CALL_TYPE_PHONE      = "PHONE";// 座机

    /**
     * 默认头像
     */
    public static final String OUTER_USER_PHOTO            = "images/user/avatar/outuser_avatar.png";
    public static final String COMMON_USER_PHOTO_FEMALE    = "images/user/avatar/female_avatar.png";
    public static final String COMMON_USER_PHOTO_MALE      = "images/user/avatar/male_avatar.png";

    /**
     * 签到涉及到的相关配置
     */

    public static final int DEFAULT_PLAN_TIME = 40;
    public static final int DEFAULT_PRE40_TIME = 40;
    public static final String DEFAULT_SIGN_SEAT = "乘务调度席";

    private GConstants(){
        // 加载基本配置文件
        
        // 加载基本配置文件
        InputStream is = null;
        ResourceLoader resourceLoader = null;
        Resource resource = null;
        for(String location:resources){// 加载配置文件
            try {
                resourceLoader = new DefaultResourceLoader();
                resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                P.load(is);

                LOG.debug("加载"+location+"成功");
            } catch (Exception e) {
                LOG.info("加载"+location+"失败", e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        try {
            Enumeration<?> enu = P.propertyNames();  
            while (enu.hasMoreElements()) {
                try {
                    String key = (String) enu.nextElement();
                    String val = (String) P.get(key);
                    String decorded ="";
                    if(decorded != null){
                        P.put(key, decorded);
                    }
//                    if("is.devmode".equals(key) == false){// 测试模式不需要加密，正式才需要
//                        String val = (String) P.get(key);
//                        String decorded = DES3Utils.decrypt(val, CONF_DESC_KEY);
//                        if(decorded != null){
//                            P.put(key, decorded);
//                        }
//                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            // 上传文件扩展名称限制
            String extensions = GConstants.getValue("file.allow.extensions");
            if(extensions != null && extensions.contains(SEMICOLON)){
                if(extensions.length() != 0){
                    String[] patternList = extensions.split(SEMICOLON);
                    for (String ext : patternList) {
                        if(ext.trim().length() != 0){
                            FILE_FILTERS.put(ext.trim().toLowerCase(), false);
                        }
                   }
                }
            }
            // 图片后缀文件
            extensions = GConstants.getValue("file.image.extensions");
            if(extensions != null && extensions.contains(SEMICOLON)){
                if(extensions.length() != 0){
                    String[] patternList = extensions.split(SEMICOLON);
                    for (String ext : patternList) {
                        if(ext.trim().length() != 0){
                            FILE_FILTERS.put(ext.trim().toLowerCase(), true);
                        }
                   }
                }
            }
            // 临时文件夹路径
            if(P.getProperty("file.upload.dir") != null){
                File tmp = new File(P.getProperty("file.upload.dir")+"tmp");
                if(!tmp.exists())
                    tmp.mkdirs();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    /**
     * 获取属性，以自定义属性优先，然后回去系统属性
     * @param key
     * @return
     */
    public static String getValue(String key) {
        if(key == null)
            return null;
        if (P.containsKey(key)) {
            return P.getProperty(key);
        }
        return System.getProperty(key);
    }
    public static String getValue(String key, String want) {
        String val = getValue(key);
        if(val == null)
            return want;
        return val;
    }
    /**
     * 解析为Boolean类型
     * @param key
     * @param want
     * @return
     */
    public static boolean getBoolean(String key, boolean want) {
        String val = getValue(key);
        if(val == null || val.length() == 0)
            return want;
        try {
            return Boolean.parseBoolean(val);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return want;
    }
    /**
     * 获取整型数据，转换数据类型
     * @param key
     * @param want
     * @return
     */
    public static int getIntValue(String key, int want) {
        String val = getValue(key);
        if(val == null || val.length() == 0)
            return want;
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return want;
    }
    /**
     * 获取长整型数据
     * @param key
     * @param want
     * @return
     */
    public static long getLongValue(String key, long want) {
        String val = getValue(key);
        if(val == null || val.length() == 0)
            return want;
        try {
            return Long.parseLong(val);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return want;
    }
    public static float getFloatValue(String key, float want) {
        String val = getValue(key);
        if(val == null || val.length() == 0)
            return want;
        try {
            return Float.parseFloat(val);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return want;
    }
    public static double getDoubleValue(String key, double want) {
        String val = getValue(key);
        if(val == null || val.length() == 0)
            return want;
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return want;
    }
}
