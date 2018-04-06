package com.sms.util;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {

  private static final String MAC_NAME = "HmacSHA1";
  private static final String ENCODING = "UTF-8";

  public static String MD5(String str, int number) {
    if (number != 16 && number != 32) {
      return null;
    }
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(str.getBytes());
      if (number == 16)
        return getEncode16(digest);
      if (number == 32)
        return getEncode32(digest);
    } catch (Exception e) {

    }
    return null;
  }

  /**
   * 32位加密
   * 
   * @param digest
   * @return
   */
  private static String getEncode32(MessageDigest digest) {
    StringBuilder builder = new StringBuilder();
    for (byte b : digest.digest()) {
      builder.append(Integer.toHexString((b >> 4) & 0xf));
      builder.append(Integer.toHexString(b & 0xf));
    }
    // return builder.toString().toLowerCase(); // 小写
    // return builder.toString().toUpperCase(); // 大写
    // java.lang.String.toUpperCase(Locale locale) 方法将在此字符串中的所有字符为大写的规则给定的Locale.
    // return builder.toString().toUpperCase(Locale.getDefault()); // 大写
    return builder.toString();
  }

  /**
   * 16位加密
   * 
   * @param digest
   * @return
   */
  private static String getEncode16(MessageDigest digest) {
    StringBuilder builder = new StringBuilder();
    for (byte b : digest.digest()) {
      builder.append(Integer.toHexString((b >> 4) & 0xf));
      builder.append(Integer.toHexString(b & 0xf));
    }

    // 16位加密，从第9位到25位
    // return builder.substring(8, 24).toString().toUpperCase();
    return builder.substring(8, 24).toString();
  }

  public static String mac(byte[] key, String preStr) throws Exception {
    byte[] data = key;
    // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
    SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
    // 生成一个指定 Mac 算法 的 Mac 对象
    Mac mac = Mac.getInstance(MAC_NAME);
    // 用给定密钥初始化 Mac 对象
    mac.init(secretKey);

    byte[] text = preStr.getBytes(ENCODING);
    // 完成 Mac 操作
    // return mac.doFinal(text);

    return toHEX(mac.doFinal(text));
  }

  private static String hexStr = "0123456789abcdef";

  public static String toHEX(byte[] bytes) {

    String result = "";
    String hex = "";
    for (int i = 0; i < bytes.length; i++) {
      // 字节高4位
      hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
      // 字节低4位
      hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
      result += hex;
    }
    return result;
  }
}
