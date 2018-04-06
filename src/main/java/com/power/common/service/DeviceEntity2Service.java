/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.power.common.entity.DeviceEntity2;
import com.power.common.dao.DeviceEntity2Dao;

/**
 * 设备管理Service
 * @author zhanglg
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class DeviceEntity2Service extends CrudService<DeviceEntity2Dao, DeviceEntity2> {

	public DeviceEntity2 get(String id) {
		return super.get(id);
	}
	
	public List<DeviceEntity2> findList(DeviceEntity2 deviceEntity2) {
		return super.findList(deviceEntity2);
	}
	
	public Page<DeviceEntity2> findPage(Page<DeviceEntity2> page, DeviceEntity2 deviceEntity2) {
		return super.findPage(page, deviceEntity2);
	}
	
	@Transactional(readOnly = false)
	public void save(DeviceEntity2 deviceEntity2) {
		super.save(deviceEntity2);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeviceEntity2 deviceEntity2) {
		super.delete(deviceEntity2);
	}
	
}