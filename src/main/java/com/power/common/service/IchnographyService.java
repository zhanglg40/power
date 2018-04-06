/**
 * 
 */
package com.power.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.power.common.dao.IchnographyDao;
import com.power.common.entity.IchnographyEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月26日
 */
@Service
@Transactional(readOnly = true)
public class IchnographyService   extends CrudService<IchnographyDao, IchnographyEntity>{

    /**
     * 
     * @author zhanglg
     * @time  2017年5月26日
     * @param page
     * @param deviceEntity
     * @return
     */
  
    public Page<IchnographyEntity> findList(Page<IchnographyEntity> page, IchnographyEntity entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }

    /**
     * 
     * @author zhanglg
     * @time  2017年6月10日
     * @param ichnographyEntity
     * @return
     */
    
    public IchnographyEntity getByArea(IchnographyEntity ichnographyEntity) {
        // TODO Auto-generated method stub
        return dao.getByArea(ichnographyEntity);
    }



}
