package com.thinkgem.jeesite.modules.sys.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.AlertManage;

/**
 * 报警类型DAO接口
 * @author zhanglg
 * @version 2018-01-27
 */
@MyBatisDao
public interface AlertManageDao extends CrudDao<AlertManage> {
	
}