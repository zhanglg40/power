package com.power.common.entity;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2018/3/4.
 */
public class UserDevice {
	private String  userId;
	private String deviceId;
	private String deviceType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
