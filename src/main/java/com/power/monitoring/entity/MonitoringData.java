/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.monitoring.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 其他设备数据监测Entity
 * @author zhanglg
 * @version 2017-06-03
 */
public class MonitoringData extends DataEntity<MonitoringData> {
	
	private static final long serialVersionUID = 1L;
	private String sbbId;		// sbb_id
	private String sbbName;  
	private String monitoringData;		// monitoring_data
	private String type;		// type
	private String areaId;		// area_id
	private String areaName;
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	private String dateTo;
	private String dateFrom;
	private String monitoringType;
	private int hours;
	private int days;
	private int months;
	private int years;
	private String dayFrom;
	private String dayTo;
	public MonitoringData() {
		super();
	}

	public MonitoringData(String id){
		super(id);
	}

	@Length(min=0, max=255, message="sbb_id长度必须介于 0 和 255 之间")
	public String getSbbId() {
		return sbbId;
	}

	public void setSbbId(String sbbId) {
		this.sbbId = sbbId;
	}
	
	@Length(min=0, max=20, message="monitoring_data长度必须介于 0 和 20 之间")
	public String getMonitoringData() {
		return monitoringData;
	}

	public void setMonitoringData(String monitoringData) {
		this.monitoringData = monitoringData;
	}
	
	@Length(min=0, max=255, message="type长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="area_id长度必须介于 0 和 255 之间")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

    public String getSbbName() {
        return sbbName;
    }

    public void setSbbName(String sbbName) {
        this.sbbName = sbbName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getMonitoringType() {
        return monitoringType;
    }

    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public String getDayFrom() {
		return dayFrom;
	}

	public void setDayFrom(String dayFrom) {
		this.dayFrom = dayFrom;
	}

	public String getDayTo() {
		return dayTo;
	}

	public void setDayTo(String dayTo) {
		this.dayTo = dayTo;
	}
}