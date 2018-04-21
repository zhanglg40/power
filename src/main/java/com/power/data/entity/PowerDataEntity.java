/**
 * 
 */
package com.power.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;

import java.util.Date;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月13日
 */

public class PowerDataEntity extends DataEntity<PowerDataEntity> {
    private static final long serialVersionUID = 1L;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String sbbId;
    private String sbbName;
    private String qyId;
    private double temperatureA;
    private double temperatureB;
    private double temperatureC;
    private double temperatureN;
    private double  currentA;
    private double  currentB;
    private double  currentC;
    private double  voltageA;
    private double  voltageB;
    private double  voltageC;
    private double electricalDegree;
    private double leakageElectricity;
	private double powerFactor;
	private double activePower;
	private String dateFrom;
    private String dateTo;
    private Area area;      // 归属区域
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
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
    public double getTemperatureA() {
        return temperatureA;
    }
    public void setTemperatureA(double temperatureA) {
        this.temperatureA = temperatureA;
    }
    public double getTemperatureB() {
        return temperatureB;
    }
    public void setTemperatureB(double temperatureB) {
        this.temperatureB = temperatureB;
    }
    public double getTemperatureC() {
        return temperatureC;
    }
    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }
    public double getTemperatureN() {
        return temperatureN;
    }
    public void setTemperatureN(double temperatureN) {
        this.temperatureN = temperatureN;
    }
    public double getCurrentA() {
        return currentA;
    }
    public void setCurrentA(double currentA) {
        this.currentA = currentA;
    }
    public double getCurrentB() {
        return currentB;
    }
    public void setCurrentB(double currentB) {
        this.currentB = currentB;
    }
    public double getCurrentC() {
        return currentC;
    }
    public void setCurrentC(double currentC) {
        this.currentC = currentC;
    }
    public double getVoltageA() {
        return voltageA;
    }
    public void setVoltageA(double voltageA) {
        this.voltageA = voltageA;
    }
    public double getVoltageB() {
        return voltageB;
    }
    public void setVoltageB(double voltageB) {
        this.voltageB = voltageB;
    }
    public double getVoltageC() {
        return voltageC;
    }
    public void setVoltageC(double voltageC) {
        this.voltageC = voltageC;
    }
    public double getElectricalDegree() {
        return electricalDegree;
    }
    public void setElectricalDegree(double electricalDegree) {
        this.electricalDegree = electricalDegree;
    }
    public double getLeakageElectricity() {
        return leakageElectricity;
    }
    public void setLeakageElectricity(double leakageElectricity) {
        this.leakageElectricity = leakageElectricity;
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
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }
    public String getQyId() {
        return qyId;
    }
    public void setQyId(String qyId) {
        this.qyId = qyId;
    }

	public double getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}

	public double getActivePower() {
		return activePower;
	}

	public void setActivePower(double activePower) {
		this.activePower = activePower;
	}
}
