package com.thinkgem.jeesite.common.config;

/**
 * 错误代码表
 * 
 * @team IT Team
 * @author renmb
 * @version 1.0
 * @time  2016年8月28日
 */
public class ErrorCode {

    /**
     * 错误码前缀说明 20 - 服务器成功返回 40 - 一般性错误 50 - 服务不可用，系统错误 STATUS
     */
    /* 成功 */
    public static final String STATUS_CODE_2000 = "2000";// 成功
    public static final String STATUS_CODE_2001 = "2001";// 结果为空（主要正对于列表）
    
    /* 数据错误表 */
    public static final String STATUS_CODE_4001 = "4001";// 请求数据校验错误
    public static final String STATUS_CODE_4003 = "4003";//
    // 用户模块（41开头）
    public static final String STATUS_CODE_4101 = "4101";// 禁止登陆
    public static final String STATUS_CODE_4102 = "4102";// 用户不存在
    public static final String STATUS_CODE_4103 = "4103";// 密码错误
    public static final String STATUS_CODE_4104 = "4104";// 校验码错误
    public static final String STATUS_CODE_4105 = "4105";// 校验码已失效
    public static final String STATUS_CODE_4106 = "4106";// 需要重新登录
    // 
    public static final String STATUS_CODE_4004 = "4004";// 运行时错误
    
    // 群
    public static final String STATUS_CODE_4201 = "4201";//群已经存在 
    public static final String STATUS_CODE_4202 = "4202";//群不存在
    public static final String STATUS_CODE_4203 = "4203";//群已被删除
    
    public static final String STATUS_CODE_4301 = "4301";// 席位禁止登陆或者已经删除
    public static final String STATUS_CODE_4302 = "4302";// 席位还处于WEB端登录状态，不允许在APP上登录

}
