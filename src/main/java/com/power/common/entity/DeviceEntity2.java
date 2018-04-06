/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Area;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备管理Entity
 * @author zhanglg
 * @version 2017-06-01
 */
public class DeviceEntity2 extends DataEntity<DeviceEntity2> {
	
	private static final long serialVersionUID = 1L;
	private String sbbName;		// 设备名称
	private String sbbId;		// 设备ID
	private Date installDate;		// install_date
	private Area area;		// area_id
	private String monitoringType;		// 监测类型
	private String areaId1;		// area_id1
	private String areaId2;		// area_id2
	
	public DeviceEntity2() {
		super();
	}

	public DeviceEntity2(String id){
		super(id);
	}

	@Length(min=0, max=50, message="设备名称长度必须介于 0 和 50 之间")
	public String getSbbName() {
		return sbbName;
	}

	public void setSbbName(String sbbName) {
		this.sbbName = sbbName;
	}
	
	@Length(min=0, max=50, message="设备ID长度必须介于 0 和 50 之间")
	public String getSbbId() {
		return sbbId;
	}

	public void setSbbId(String sbbId) {
		this.sbbId = sbbId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="监测类型长度必须介于 0 和 255 之间")
	public String getMonitoringType() {
		return monitoringType;
	}

	public void setMonitoringType(String monitoringType) {
		this.monitoringType = monitoringType;
	}
	
	@Length(min=0, max=64, message="area_id1长度必须介于 0 和 64 之间")
	public String getAreaId1() {
		return areaId1;
	}

	public void setAreaId1(String areaId1) {
		this.areaId1 = areaId1;
	}
	
	@Length(min=0, max=64, message="area_id2长度必须介于 0 和 64 之间")
	public String getAreaId2() {
		return areaId2;
	}

	public void setAreaId2(String areaId2) {
		this.areaId2 = areaId2;
	}
	
}