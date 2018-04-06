package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;


/**
 * 报警类型Entity
 * @author zhanglg
 * @version 2018-01-27
 */
public class AlertManage extends DataEntity<AlertManage> {
	
	private static final long serialVersionUID = 1L;
	private String alertType;		// 类型
	private String value;		// 临界值
	private String tips;		// 提示
	private String remark;		// remark
	
	public AlertManage() {
		super();
	}

	public AlertManage(String id){
		super(id);
	}

	@Length(min=1, max=40, message="类型长度必须介于 1 和 40 之间")
	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=255, message="提示长度必须介于 1 和 255 之间")
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}