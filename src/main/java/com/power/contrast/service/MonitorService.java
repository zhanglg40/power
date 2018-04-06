/**
 * 
 */
package com.power.contrast.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.power.contrast.dao.MonitorDao;
import com.power.contrast.entity.ShowDataEntity;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月6日
 */
@Service
@Transactional(readOnly = true)
public class MonitorService extends CrudService<MonitorDao, ShowDataEntity> {

}
