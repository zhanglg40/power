/**
 * 
 */
package com.power.contrast.dao;

import com.power.contrast.entity.ShowDataEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月6日
 */
@MyBatisDao
public interface MonitorDao   extends CrudDao<ShowDataEntity>{

}
