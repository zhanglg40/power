/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.data.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.power.data.entity.IchnographyDevice;

/**
 * 平面图设备DAO接口
 * @author zhanglg
 * @version 2017-06-10
 */
@MyBatisDao
public interface IchnographyDeviceDao extends CrudDao<IchnographyDevice> {
	
}