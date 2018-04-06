/**
 * 
 */
package com.power.mail.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;



/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年3月2日
 */

public class MailEntity  extends DataEntity<MailEntity> {


    private static final long serialVersionUID = 1L;
    private String mailAddress;
    private String password;
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

}
