package com.solr.solr_entity;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Phone {
	@Field("id")
	private String phone;
	@Field("tags_ss")
	private List<String> tags;
	@Field("city_s")
	private String city;
	@Field("province_s")
	private String province;
	@Field("channel_ss")
	private List<String> channels;
	@Field("times_s")
	private String times;
	@Field("last_date_s")
	private String last_date;
	
	
	
	
	public Phone(String phone, List<String> tags, String city, String province, List<String> channels, String times, String last_date) {
		super();
		this.phone = phone;
		this.tags = tags;
		this.city = city;
		this.province = province;
		this.channels = channels;
		this.times = times;
		this.last_date = last_date;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public List<String> getTags() {
		return tags;
	}




	public void setTags(List<String> tags) {
		this.tags = tags;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getProvince() {
		return province;
	}




	public void setProvince(String province) {
		this.province = province;
	}




	public List<String> getChannels() {
		return channels;
	}




	public void setChannels(List<String> channels) {
		this.channels = channels;
	}




	public String getTimes() {
		return times;
	}




	public void setTimes(String times) {
		this.times = times;
	}




	public String getLast_date() {
		return last_date;
	}




	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}




	public Phone() {
		super();
	}
}
