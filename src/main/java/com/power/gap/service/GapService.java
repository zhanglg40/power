package com.power.gap.service;

import com.power.contrast.entity.ShowDataEntity;
import com.power.gap.dao.GapDao;
import com.power.gap.entity.GapEntity;
import com.thinkgem.jeesite.common.service.CrudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@Service
@Transactional(readOnly = true)
public class GapService extends CrudService<GapDao, GapEntity> {
  public  List<GapEntity> getDayList(GapEntity entity){

      return  dao.getDayList(entity);
    }

/**
 * 
 * @author zhanglg
 * @time  2017年5月20日
 * @param gapEntity
 * @return
 */

public List<GapEntity> getMonthList(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getMonthList(gapEntity);
}
public List<GapEntity> getYearList(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getYearList(gapEntity);
}

/**
 * 
 * @author zhanglg
 * @time  2017年5月23日
 * @param showDataEntity
 * @return
 */

public List<Map<String, Object>> getSbbElec(ShowDataEntity showDataEntity) {
    // TODO Auto-generated method stub
    return dao.getSbbElec(showDataEntity);
}

/**
 * 
 * @author zhanglg
 * @time  2017年5月29日
 * @param gapEntity
 * @return
 */

public List<GapEntity> getMonthArea(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getMonthArea(gapEntity);
}

/**
 * 
 * @author zhanglg
 * @time  2017年5月31日
 * @param gapEntity
 * @return
 */

public List<GapEntity> getYearArea(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getYearArea(gapEntity);
}

/**
 * 
 * @author zhanglg
 * @time  2017年6月1日
 * @param gapEntity
 * @return
 */

public List<GapEntity> getDayArea(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getDayArea(gapEntity);
}

/**
 * 
 * @author zhanglg
 * @time  2018年2月3日
 * @param gapEntity
 * @return
 */

public List<GapEntity> getMainYearList(GapEntity gapEntity) {
    // TODO Auto-generated method stub
    return dao.getMainYearList(gapEntity);
}


    public List<GapEntity> getMainMonthList(GapEntity gapEntity) {
        return dao.getMainMonthList(gapEntity);
    }

    public List<GapEntity> getRealList(GapEntity gapEntity) {
        return dao.getRealList(gapEntity);
    }

    public List<GapEntity> getMainDayList(GapEntity gapEntity) {
        return dao.getMainDayList(gapEntity);
    }
}
