package com.power.contrast.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.annotation.FieldName;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
public class ShowDataEntity extends DataEntity<ShowDataEntity> {
    private static final long serialVersionUID = 1L;
    private String sbbId;
    private String sbbCId;
    private String sbbName;
    @FieldName(value="温度A")
    private double temperatureA;
    @FieldName(value="温度B")
    private double temperatureB;
    @FieldName(value="温度C")
    private double temperatureC;
    @FieldName(value="温度N")
    private double temperatureN;
    @FieldName(value="电流A")
    private double  currentA;
    @FieldName(value="电流B")
    private double  currentB;
    @FieldName(value="电流C")
    private double  currentC;
    @FieldName(value="电压A")
    private double  voltageA;
    @FieldName(value="电压B")
    private double  voltageB;
    @FieldName(value="电压C")
    private double  voltageC;
    @FieldName(value="电度")
    private double electricalDegree;
    @FieldName(value="漏电")
    private double leakageElectricity;
    private String sbbList;
    private Date dayFrom;
    private Date dayTo;
    private String dateFrom;
    private String dateTo;
    private String condition;
    private Area area;
    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getSbbList() {
        return sbbList;
    }

    public void setSbbList(String sbbList) {
        this.sbbList = sbbList;
    }

   



    public Date getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(Date dayFrom) {
        this.dayFrom = dayFrom;
    }

    public Date getDayTo() {
        return dayTo;
    }

    public void setDayTo(Date dayTo) {
        this.dayTo = dayTo;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getSbbCId() {
        return sbbCId;
    }

    public void setSbbCId(String sbbCId) {
        this.sbbCId = sbbCId;
    }

 

}
