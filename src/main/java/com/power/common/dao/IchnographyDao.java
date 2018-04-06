/**
 * 
 */
package com.power.common.dao;

import com.power.common.entity.IchnographyEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月26日
 */
@MyBatisDao
public interface IchnographyDao  extends CrudDao<IchnographyEntity> {

    /**
     * 
     * @author zhanglg
     * @time  2017年6月10日
     * @param ichnographyEntity
     * @return
     */
    
    IchnographyEntity getByArea(IchnographyEntity ichnographyEntity);


   
}
