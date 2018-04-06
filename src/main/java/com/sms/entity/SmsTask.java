/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sms.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 */
public class SmsTask extends DataEntity<SmsTask> {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String keyWord;
	private String place;
	private String placeType;
	private Long totalQuantity;
	private Long plannedQuantity;
	private String processingQuantity;
	private Date beginTime;
	private Date endTime;
	private String status;
	private String remarks;
	private User createBy;
	private Date createDate;
	private User updateBy;
	private Date updateDate;
	private String delFlag;
	
	
	
	
	public SmsTask() {
		super();
	}
	
	public SmsTask(String id){
		super(id);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPlaceType() {
		return placeType;
	}
	
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public Long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Long getPlannedQuantity() {
		return plannedQuantity;
	}
	public void setPlannedQuantity(Long plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}
	public String getProcessingQuantity() {
		return processingQuantity;
	}
	public void setProcessingQuantity(String processingQuantity) {
		this.processingQuantity = processingQuantity;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public User getCreateBy() {
		return createBy;
	}
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	
	
}

