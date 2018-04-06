package com.solr.rest_entity;

import java.util.List;

import com.solr.solr_entity.Phone;

public class RestPhoneData {
	
	private Long phoneNum;
	private List<Phone> phones;
	
	public Long getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}
