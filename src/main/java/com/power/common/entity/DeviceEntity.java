/**
 * 
 */
package com.power.common.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月5日
 */

public class DeviceEntity  extends DataEntity<DeviceEntity> {
    private static final long serialVersionUID = 1L;
    private String sbbId;
    private String sbbName;
    private String longitude;
    private String latitude;  
    private String sbbPosition;
    private String  sbbType;
    private String  internetNo;
    private String  qyId;
    private String  qyName;
    private String  monitoringType;
	private String  reveiveNo;
    public String getSbbId() {
        return sbbId;
    }
    public void setSbbId(String sbbId) {
        this.sbbId = sbbId;
    }
    public String getSbbName() {
        return sbbName;
    }
    public void setSbbName(String sbbName) {
        this.sbbName = sbbName;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getSbbPosition() {
        return sbbPosition;
    }
    public void setSbbPosition(String sbbPosition) {
        this.sbbPosition = sbbPosition;
    }
    public String getSbbType() {
        return sbbType;
    }
    public void setSbbType(String sbbType) {
        this.sbbType = sbbType;
    }
    public String getInternetNo() {
        return internetNo;
    }
    public void setInternetNo(String internetNo) {
        this.internetNo = internetNo;
    }
    public String getQyId() {
        return qyId;
    }
    public void setQyId(String qyId) {
        this.qyId = qyId;
    }
    public String getQyName() {
        return qyName;
    }
    public void setQyName(String qyName) {
        this.qyName = qyName;
    }
    public String getMonitoringType() {
        return monitoringType;
    }
    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

	public String getReveiveNo() {
		return reveiveNo;
	}

	public void setReveiveNo(String reveiveNo) {
		this.reveiveNo = reveiveNo;
	}
}
