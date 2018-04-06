package com.power.gap.dao;

import com.power.contrast.entity.ShowDataEntity;
import com.power.gap.entity.GapEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@MyBatisDao
public interface GapDao extends CrudDao<GapEntity> {
    public List<GapEntity> getDayList(GapEntity entity);
    public List<GapEntity> getMonthList(GapEntity entity);

    
    public List<GapEntity> getYearList(GapEntity gapEntity);

    public List<Map<String, Object>> getSbbElec(ShowDataEntity showDataEntity);

    
    public List<GapEntity> getMonthArea(GapEntity gapEntity);
    /**
     * 
     * @author zhanglg
     * @time  2017年5月31日
     * @param gapEntity
     * @return
     */
    
    public List<GapEntity> getYearArea(GapEntity gapEntity);
    /**
     * 
     * @author zhanglg
     * @time  2017年6月1日
     * @param gapEntity
     * @return
     */
    
    public List<GapEntity> getDayArea(GapEntity gapEntity);
    /**
     * 
     * @author zhanglg
     * @time  2018年2月3日
     * @param gapEntity
     * @return
     */
    
    public List<GapEntity> getMainYearList(GapEntity gapEntity);


    public List<GapEntity> getMainMonthList(GapEntity gapEntity);

    List<GapEntity> getRealList(GapEntity gapEntity);

    List<GapEntity> getMainDayList(GapEntity gapEntity);
}
