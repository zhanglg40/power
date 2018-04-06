package com.thinkgem.jeesite.common.api;

import java.text.SimpleDateFormat;

import com.thinkgem.jeesite.common.config.ErrorCode;
import com.thinkgem.jeesite.common.config.GConstants;



/**
 * 所有的接口返回实体类
 * @team IT Team
 * @author  renmb
 * @version 1.0
 * @time 2016-03-10
 *
 */
public class ResponseBean {
    
//    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private String status  = ErrorCode.STATUS_CODE_2000;
    private String message = GConstants.OK;
//    @JSONField(name="current_datetime")
    private String current;//FORMAT.format(System.currentTimeMillis());
    private Long   currentTimeMillis;//FORMAT.format(System.currentTimeMillis());
    private Object data;
    
    public ResponseBean(){
        
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getCurrent() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        current = format.format(System.currentTimeMillis());
        return current;
    }
    public Long getCurrentTimeMillis() {
        this.currentTimeMillis = System.currentTimeMillis();
        return this.currentTimeMillis;
    }
    public void setCurrentTimeMillis(Long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }
    public void setCurrent(String current) {
        this.current = current;
    }
}
