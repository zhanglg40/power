/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.data.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.power.data.entity.IchnographyDevice;
import com.power.data.dao.IchnographyDeviceDao;

/**
 * 平面图设备Service
 * @author zhanglg
 * @version 2017-06-10
 */
@Service
@Transactional(readOnly = true)
public class IchnographyDeviceService extends CrudService<IchnographyDeviceDao, IchnographyDevice> {

	public IchnographyDevice get(String id) {
		return super.get(id);
	}
	
	public List<IchnographyDevice> findList(IchnographyDevice ichnographyDevice) {
		return super.findList(ichnographyDevice);
	}
	
	public Page<IchnographyDevice> findPage(Page<IchnographyDevice> page, IchnographyDevice ichnographyDevice) {
		return super.findPage(page, ichnographyDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(IchnographyDevice ichnographyDevice) {
		super.save(ichnographyDevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(IchnographyDevice ichnographyDevice) {
		super.delete(ichnographyDevice);
	}
	
}