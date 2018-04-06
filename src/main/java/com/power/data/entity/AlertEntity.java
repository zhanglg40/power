/**
 * 
 */
package com.power.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月20日
 */

public class AlertEntity extends DataEntity<AlertEntity> {
    private static final long serialVersionUID = 1L;
    private String sbbId;
    private String sbbType;
    private Date alertTime;
    private String alertType;
     private String delName;
    private String dateFrom;
    private String dateTo;
    private String reveiveNo;
	private String sbbName;
	public AlertEntity(){
		delFlag=null;
	}
    public String getSbbId() {
        return sbbId;
    }
    public void setSbbId(String sbbId) {
        this.sbbId = sbbId;
    }
    public String getSbbType() {
        return sbbType;
    }
    public void setSbbType(String sbbType) {
        this.sbbType = sbbType;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getAlertTime() {
        return alertTime;
    }
    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }
    public String getAlertType() {
        return alertType;
    }
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    public String getDateTo() {
        return dateTo;
    }
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    public String getSbbName() {
        return sbbName;
    }
    public void setSbbName(String sbbName) {
        this.sbbName = sbbName;
    }

    public String getDelName() {
        return delName;
    }

    public void setDelName(String delName) {
        this.delName = delName;
    }

	public String getReveiveNo() {
		return reveiveNo;
	}

	public void setReveiveNo(String reveiveNo) {
		this.reveiveNo = reveiveNo;
	}
}
