package com.power.data.dao;


import com.power.data.entity.AlertDetail;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 报警数据DAO接口
 * @author zhanglg
 * @version 2018-04-22
 */
@MyBatisDao
public interface AlertDetailDao extends CrudDao<AlertDetail> {
	
}