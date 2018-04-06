package com.power.contrast.service;

import com.power.contrast.dao.HistoryDataDao;
import com.power.contrast.entity.ShowDataEntity;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@Service
@Transactional(readOnly = true)
public class HistoryDataService extends CrudService<HistoryDataDao, ShowDataEntity> {
  public  List<ShowDataEntity> historyDataList(ShowDataEntity entity){
             return  dao.historyDataList(entity);
    }
  public  ShowDataEntity getHistoryData(ShowDataEntity entity){
      return  dao.getHistoryData(entity);
}
}
