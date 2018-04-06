/**
 * 
 */
package com.power.common.dao;

import com.power.common.entity.DeviceEntity;
import com.power.common.entity.UserDevice;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月5日
 */
@MyBatisDao
public interface DeviceDao   extends CrudDao<DeviceEntity>{

	List<UserDevice> findByUser(User user);

	void deleteDevices(User user);

	void insertDevices( UserDevice  devices);

	List<DeviceEntity> findListByUser(String userId);
}
