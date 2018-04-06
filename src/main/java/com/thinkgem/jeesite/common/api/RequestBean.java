package com.thinkgem.jeesite.common.api;

import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSON;

/**
 * 所有的接口请求实体类
 * @team IT Team
 * @author  renmb
 * @version 1.0
 * @time 2016-03-10
 */
public class RequestBean {

    @NotEmpty(message="参数sign不能为NULL")
    private String sign;
    @NotEmpty(message="参数content不能为NULL")
    private String content;
//    @NotEmpty(message="无法获取IP地址")
    private String clientip;
    
    public String getClientip() {
        return clientip;
    }
    public void setClientip(String clientip) {
        this.clientip = clientip;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取对应对象
     * @param clazz
     * @return
     */
    public <T> T getObjectBean(Type clazz){
        if(content == null)
            return null;
        try {
            return JSON.parseObject(content, clazz);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    /**
     * 获取验证的错误信息
     * @param errors
     * @return
     */
    public String getValidateMessage(BindingResult errors){
        if(errors !=null && errors.hasErrors()){
            List<ObjectError>  allErrors = errors.getAllErrors();
            if(allErrors !=null && allErrors.size()>0)
                return allErrors.get(0).getDefaultMessage();
        }
        return null;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
