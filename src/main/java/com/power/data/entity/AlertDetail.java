package com.power.data.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;


/**
 * 报警数据Entity
 * @author zhanglg
 * @version 2018-04-22
 */
public class AlertDetail extends DataEntity<AlertDetail> {
	
	private static final long serialVersionUID = 1L;
	private String sbbId;		// sbb_id
	private String sbbName;		// sbb_id
	private String powerFactor;		// power_factor
	private String activePower;		// active_power
	private String reactivePower;		// reactive_power
	private String apparentPower;		// apparent_power
	private String temperatureA;		// temperature_a
	private String temperatureB;		// temperature_b
	private String temperatureC;		// temperature_c
	private String temperatureN;		// temperature_n
	private String currentA;		// current_a
	private String currentB;		// current_b
	private String currentC;		// current_c
	private String voltageA;		// voltage_a
	private String voltageB;		// voltage_b
	private String voltageC;		// voltage_c
	private String electricalDegree;		// electrical_degree
	private String leakageElectricity;		// leakage_electricity
	
	public AlertDetail() {
		super();
	}

	public AlertDetail(String id){
		super(id);
	}

	public String getSbbId() {
		return sbbId;
	}

	public void setSbbId(String sbbId) {
		this.sbbId = sbbId;
	}
	
	@Length(min=0, max=20, message="power_factor长度必须介于 0 和 20 之间")
	public String getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(String powerFactor) {
		this.powerFactor = powerFactor;
	}
	
	@Length(min=0, max=25, message="active_power长度必须介于 0 和 25 之间")
	public String getActivePower() {
		return activePower;
	}

	public void setActivePower(String activePower) {
		this.activePower = activePower;
	}
	
	@Length(min=0, max=25, message="reactive_power长度必须介于 0 和 25 之间")
	public String getReactivePower() {
		return reactivePower;
	}

	public void setReactivePower(String reactivePower) {
		this.reactivePower = reactivePower;
	}
	
	@Length(min=0, max=25, message="apparent_power长度必须介于 0 和 25 之间")
	public String getApparentPower() {
		return apparentPower;
	}

	public void setApparentPower(String apparentPower) {
		this.apparentPower = apparentPower;
	}
	
	@Length(min=0, max=20, message="temperature_a长度必须介于 0 和 20 之间")
	public String getTemperatureA() {
		return temperatureA;
	}

	public void setTemperatureA(String temperatureA) {
		this.temperatureA = temperatureA;
	}
	
	@Length(min=0, max=20, message="temperature_b长度必须介于 0 和 20 之间")
	public String getTemperatureB() {
		return temperatureB;
	}

	public void setTemperatureB(String temperatureB) {
		this.temperatureB = temperatureB;
	}
	
	@Length(min=0, max=20, message="temperature_c长度必须介于 0 和 20 之间")
	public String getTemperatureC() {
		return temperatureC;
	}

	public void setTemperatureC(String temperatureC) {
		this.temperatureC = temperatureC;
	}
	
	@Length(min=0, max=20, message="temperature_n长度必须介于 0 和 20 之间")
	public String getTemperatureN() {
		return temperatureN;
	}

	public void setTemperatureN(String temperatureN) {
		this.temperatureN = temperatureN;
	}
	
	@Length(min=0, max=20, message="current_a长度必须介于 0 和 20 之间")
	public String getCurrentA() {
		return currentA;
	}

	public void setCurrentA(String currentA) {
		this.currentA = currentA;
	}
	
	@Length(min=0, max=20, message="current_b长度必须介于 0 和 20 之间")
	public String getCurrentB() {
		return currentB;
	}

	public void setCurrentB(String currentB) {
		this.currentB = currentB;
	}
	
	@Length(min=0, max=20, message="current_c长度必须介于 0 和 20 之间")
	public String getCurrentC() {
		return currentC;
	}

	public void setCurrentC(String currentC) {
		this.currentC = currentC;
	}
	
	@Length(min=0, max=20, message="voltage_a长度必须介于 0 和 20 之间")
	public String getVoltageA() {
		return voltageA;
	}

	public void setVoltageA(String voltageA) {
		this.voltageA = voltageA;
	}
	
	@Length(min=0, max=20, message="voltage_b长度必须介于 0 和 20 之间")
	public String getVoltageB() {
		return voltageB;
	}

	public void setVoltageB(String voltageB) {
		this.voltageB = voltageB;
	}
	
	@Length(min=0, max=20, message="voltage_c长度必须介于 0 和 20 之间")
	public String getVoltageC() {
		return voltageC;
	}

	public void setVoltageC(String voltageC) {
		this.voltageC = voltageC;
	}
	
	@Length(min=0, max=20, message="electrical_degree长度必须介于 0 和 20 之间")
	public String getElectricalDegree() {
		return electricalDegree;
	}

	public void setElectricalDegree(String electricalDegree) {
		this.electricalDegree = electricalDegree;
	}
	
	@Length(min=0, max=20, message="leakage_electricity长度必须介于 0 和 20 之间")
	public String getLeakageElectricity() {
		return leakageElectricity;
	}

	public void setLeakageElectricity(String leakageElectricity) {
		this.leakageElectricity = leakageElectricity;
	}

	public String getSbbName() {
		return sbbName;
	}

	public void setSbbName(String sbbName) {
		this.sbbName = sbbName;
	}
}