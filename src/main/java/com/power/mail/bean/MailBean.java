/**
 * 
 */
package com.power.mail.bean;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2016年10月28日
 */

public class MailBean {
    @NotBlank(message="from不允许为空")
    private String from;
    @NotBlank(message="to不允许为空")
    private String to;
    @NotBlank(message="password不允许为空")
    private String password;
    @NotBlank(message="hostName不允许为空")
    private String hostName;
    @NotBlank(message="smtpPort不允许为空")
    private String smtpPort;
    @NotBlank(message="subTitle不允许为空")
    private String subTitle;
    @NotBlank(message="body不允许为空")
    private String body;
    private String name;
    private List<String> maillist;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public String getSmtpPort() {
        return smtpPort;
    }
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public List<String> getMaillist() {
        return maillist;
    }
    public void setMaillist(List<String> maillist) {
        this.maillist = maillist;
    }
    
}
