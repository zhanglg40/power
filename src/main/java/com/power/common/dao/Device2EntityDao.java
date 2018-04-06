/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.power.common.entity.Device2Entity;

import java.util.List;

/**
 * 设备管理DAO接口
 * @author zhanglg
 * @version 2017-06-01
 */
@MyBatisDao
public interface Device2EntityDao extends CrudDao<Device2Entity> {

    /**
     * 
     * @author zhanglg
     * @time  2017年6月3日
     * @param sbbId
     * @return
     */
    
    Device2Entity getBySbbId(@Param(value="sbbId")String sbbId);

	List<Device2Entity> findListByUser(String userId);
}