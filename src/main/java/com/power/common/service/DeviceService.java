/**
 * 
 */
package com.power.common.service;

import com.power.common.dao.DeviceDao;
import com.power.common.entity.DeviceEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月5日
 */
@Service
@Transactional(readOnly = true)
public class DeviceService  extends CrudService<DeviceDao, DeviceEntity> {
   public List<DeviceEntity> findAllList(){
       return dao.findAllList(new DeviceEntity());
   }

/**
 * 
 * @author zhanglg
 * @time  2017年5月7日
 * @param page
 * @param entity
 * @return
 */

public Page<DeviceEntity> findList(Page<DeviceEntity> page, DeviceEntity entity) {
    entity.setPage(page);
    page.setList(dao.findList(entity));
    return page;
   
}

public List<DeviceEntity>findList(DeviceEntity entity) {
    return dao.findList(entity);
   
}
public void save(DeviceEntity deviceEntity){
    if(StringUtils.isBlank(deviceEntity.getSbbId())){
        dao.insert(deviceEntity);
    }else{
        dao.update(deviceEntity);
    }
}

	public List<DeviceEntity> findListByUser(String userId) {
		return dao.findListByUser(userId);
	}
}
