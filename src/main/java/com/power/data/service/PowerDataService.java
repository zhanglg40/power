/**
 * 
 */
package com.power.data.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.power.data.dao.PowerDataDao;
import com.power.data.entity.PowerDataEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月13日
 */
@Service
@Transactional(readOnly = true)
public class PowerDataService  extends CrudService<PowerDataDao,PowerDataEntity>{

    /**
     * 
     * @author zhanglg
     * @time  2017年5月14日
     * @param page
     * @param powerDataEntity
     * @return
     */
    
    public Page<PowerDataEntity> findList(Page<PowerDataEntity> page, PowerDataEntity powerDataEntity) {
        // TODO Auto-generated method stub
        powerDataEntity.setPage(page);
        page.setList(dao.findList(powerDataEntity));
        return page;
    }

    /**
     * 
     * @author zhanglg
     * @time  2017年5月30日
     * @param sbbId
     * @return
     */
    
    public PowerDataEntity getLastData(String sbbId) {
        // TODO Auto-generated method stub
        return dao.getLastData( sbbId);
    }

    /**
     * 
     * @author zhanglg
     * @time  2018年1月25日
     * @param powerDataEntity
     * @return
     */
    
    public  List<PowerDataEntity> findApiList(PowerDataEntity powerDataEntity,String pageSize,String pageNO) {
        // TODO Auto-generated method stub
        PageInfo<PowerDataEntity> pageInfo = null;
        // 必须紧贴dao的查询方法
        PageHelper.startPage(1, 10);
        pageInfo = new PageInfo<PowerDataEntity>(dao.findApiList(powerDataEntity));
        return pageInfo.getList();
    }



}
