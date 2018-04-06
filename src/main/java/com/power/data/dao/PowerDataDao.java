/**
 * 
 */
package com.power.data.dao;

import java.util.List;

import com.power.data.entity.PowerDataEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月13日
 */
@MyBatisDao
public interface PowerDataDao extends CrudDao<PowerDataEntity> {


    
    PowerDataEntity getLastData(String sbbId);

    /**
     * 
     * @author zhanglg
     * @time  2018年1月25日
     * @param powerDataEntity
     * @return
     */
    
    List<PowerDataEntity> findApiList(PowerDataEntity powerDataEntity);
       
}
