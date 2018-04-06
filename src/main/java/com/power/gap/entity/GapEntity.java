package com.power.gap.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;

import java.util.Date;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
public class GapEntity extends DataEntity<GapEntity> {
    private static final long serialVersionUID = 1L;
    private String sbbId;
    private String sbbName;
	private String sbbType;
    private int hours;
    private int days;
    private int months;
    private int years;
    private double electricalDegree;
    private double gap;
    private String checkDate;
    private String dayFrom;
    private String dayTo;
    private String monthFrom;
    private String monthTo;
    private Date dateFrom;
    private Date dateTo;
    private Area area;
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
	private double  leakageElectricity;
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

    public double getElectricalDegree() {
        return electricalDegree;
    }

    public void setElectricalDegree(double electricalDegree) {
        this.electricalDegree = electricalDegree;
    }

    public double getGap() {
        return gap;
    }

    public void setGap(double gap) {
        this.gap = gap;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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

    public String getMonthFrom() {
        return monthFrom;
    }

    public void setMonthFrom(String monthFrom) {
        this.monthFrom = monthFrom;
    }

    public String getMonthTo() {
        return monthTo;
    }

    public void setMonthTo(String monthTo) {
        this.monthTo = monthTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

	public String getSbbType() {
		return sbbType;
	}

	public void setSbbType(String sbbType) {
		this.sbbType = sbbType;
	}

	public double getLeakageElectricity() {
		return leakageElectricity;
	}

	public void setLeakageElectricity(double leakageElectricity) {
		this.leakageElectricity = leakageElectricity;
	}
}
