/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.monitoring.dao;

import com.power.gap.entity.GapEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.power.monitoring.entity.MonitoringData;

import java.util.List;

/**
 * 其他设备数据监测DAO接口
 * @author zhanglg
 * @version 2017-06-03
 */
@MyBatisDao
public interface MonitoringDataDao extends CrudDao<MonitoringData> {

    List<MonitoringData> getMainMonthList(MonitoringData gapEntity);
}