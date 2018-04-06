/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.power.common.entity.DeviceArea;

/**
 * 监测区域DAO接口
 * @author zhanglg
 * @version 2017-06-01
 */
@MyBatisDao
public interface DeviceAreaDao extends CrudDao<DeviceArea> {

    /**
     * 
     * @author zhanglg
     * @time  2017年6月3日
     * @param code
     * @return
     */
    
    DeviceArea getByCode(String code);
	
}