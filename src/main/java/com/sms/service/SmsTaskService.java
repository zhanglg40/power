package com.sms.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dao.SmsTaskDao;
import com.sms.entity.SmsTask;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
public class SmsTaskService extends CrudService<SmsTaskDao, SmsTask> {
	
	@Autowired
	private SmsTaskDao smsTaskDao;
	
	@Transactional(readOnly = true)
	public Page<SmsTask> findDataTest(Page<SmsTask> page, SmsTask smsTask) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		smsTask.getSqlMap().put("dsf", "");
		// 设置分页参数
		smsTask.setPage(page);
		// 执行分页查询
		page.setList(smsTaskDao.findList(smsTask));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SmsTask smsTask) {
		Date date= new Date();
		User user = UserUtils.getUser();
		
		if (StringUtils.isNotBlank(smsTask.getId())){
			if (StringUtils.isNotBlank(user.getId())){
				smsTask.setUpdateDate(date);
				smsTask.setUpdateBy(user); 
			}
			smsTaskDao.update(smsTask);
		}else {
			if (StringUtils.isNotBlank(user.getId())){
				smsTask.setUpdateDate(date);
				smsTask.setUpdateBy(user); 
				smsTask.setCreateDate(date);
				smsTask.setCreateBy(user); 
			}
			smsTask.setStatus("0");
			smsTaskDao.insert(smsTask);
			
		}
	}

	/**
	 * 保存搜索日志
	 * @param smsTask
	 */
	public void insertLog(SmsTask smsTask) {
		Date date= new Date();
		User user = UserUtils.getUser();
		smsTask.setUpdateDate(date);
		smsTask.setUpdateBy(user);
		smsTask.setCreateDate(date);
		smsTask.setCreateBy(user);
		smsTaskDao.insertLog(smsTask);
	}
}
