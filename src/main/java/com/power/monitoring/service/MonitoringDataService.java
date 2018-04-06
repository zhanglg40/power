/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.monitoring.service;

import com.power.gap.entity.GapEntity;
import com.power.monitoring.dao.MonitoringDataDao;
import com.power.monitoring.entity.MonitoringData;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 其他设备数据监测Service
 * @author zhanglg
 * @version 2017-06-03
 */
@Service
@Transactional(readOnly = true)
public class MonitoringDataService extends CrudService<MonitoringDataDao, MonitoringData> {

	public MonitoringData get(String id) {
		return super.get(id);
	}
	
	public List<MonitoringData> findList(MonitoringData monitoringData) {
		return super.findList(monitoringData);
	}
	
	public Page<MonitoringData> findPage(Page<MonitoringData> page, MonitoringData monitoringData) {
	    page.setOrderBy("a.create_date desc");
	    monitoringData.setPage(page);
	    return super.findPage(page, monitoringData);
	}
	
	@Transactional(readOnly = false)
	public void save(MonitoringData monitoringData) {
		super.save(monitoringData);
	}
	
	@Transactional(readOnly = false)
	public void delete(MonitoringData monitoringData) {
		super.delete(monitoringData);
	}

	public List<MonitoringData> getMainMonthList(MonitoringData gapEntity) {
		return dao.getMainMonthList(gapEntity);
	}
}