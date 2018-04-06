package com.power.contrast.dao;

import com.power.contrast.entity.ShowDataEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@MyBatisDao
public interface HistoryDataDao  extends CrudDao<ShowDataEntity> {
    List<ShowDataEntity> historyDataList(ShowDataEntity entity);

    /**
     * 
     * @author zhanglg
     * @time  2017年5月5日
     * @param entity
     * @return
     */
    
    ShowDataEntity getHistoryData(ShowDataEntity entity);
}
