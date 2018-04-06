/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.power.common.entity.DeviceArea;
import com.power.common.dao.DeviceAreaDao;

/**
 * 监测区域Service
 * @author zhanglg
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class DeviceAreaService extends CrudService<DeviceAreaDao, DeviceArea> {

	public DeviceArea get(String id) {
		return super.get(id);
	}
	   public DeviceArea getByCode(String code) {
	        return dao.getByCode(code);
	    }
	public List<DeviceArea> findList(DeviceArea deviceArea) {
		return super.findList(deviceArea);
	}
	
	public Page<DeviceArea> findPage(Page<DeviceArea> page, DeviceArea deviceArea) {
		return super.findPage(page, deviceArea);
	}
	
	@Transactional(readOnly = false)
	public void save(DeviceArea deviceArea) {
		super.save(deviceArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeviceArea deviceArea) {
		super.delete(deviceArea);
	}
	
}