/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.power.common.entity.Device2Entity;
import com.power.common.dao.Device2EntityDao;

/**
 * 设备管理Service
 * @author zhanglg
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class Device2EntityService extends CrudService<Device2EntityDao, Device2Entity> {

	public Device2Entity get(String id) {
		return super.get(id);
	}
	
	public List<Device2Entity> findList(Device2Entity device2Entity) {
		return super.findList(device2Entity);
	}
	
	public Page<Device2Entity> findPage(Page<Device2Entity> page, Device2Entity device2Entity) {
		return super.findPage(page, device2Entity);
	}
	
	@Transactional(readOnly = false)
	public void save(Device2Entity device2Entity) {
		super.save(device2Entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Device2Entity device2Entity) {
		super.delete(device2Entity);
	}

    /**
     * 
     * @author zhanglg
     * @time  2017年6月3日
     * @param sbbId
     * @return
     */
    
    public Device2Entity getBySbbId(String sbbId) {
        // TODO Auto-generated method stub
        return dao.getBySbbId( sbbId);
    }

	public List<Device2Entity> findListByUser(String userId) {
		return dao.findListByUser( userId);
	}
}