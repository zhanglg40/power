/**
 * 
 */
package com.sms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年6月13日
 */

public class SmsSetEntity  extends DataEntity<SmsSetEntity> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String receiveNo;
    private String leftCount;
    private String smsContent;
    private int sendTime;
    public String getReceiveNo() {
        return receiveNo;
    }
    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
    public String getLeftCount() {
        return leftCount;
    }
    public void setLeftCount(String leftCount) {
        this.leftCount = leftCount;
    }
    public String getSmsContent() {
        return smsContent;
    }
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
    public int getSendTime() {
        return sendTime;
    }
    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }
    
}
