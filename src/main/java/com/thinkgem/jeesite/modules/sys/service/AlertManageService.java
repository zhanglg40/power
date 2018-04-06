package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.AlertManage;
import com.thinkgem.jeesite.modules.sys.dao.AlertManageDao;

/**
 * 报警类型Service
 * @author zhanglg
 * @version 2018-01-27
 */
@Service
@Transactional(readOnly = true)
public class AlertManageService extends CrudService<AlertManageDao, AlertManage> {

	public AlertManage get(String id) {
		return super.get(id);
	}
	
	public List<AlertManage> findList(AlertManage alertManage) {
		return super.findList(alertManage);
	}
	
	public Page<AlertManage> findPage(Page<AlertManage> page, AlertManage alertManage) {
		return super.findPage(page, alertManage);
	}
	
	@Transactional(readOnly = false)
	public void save(AlertManage alertManage) {
		super.save(alertManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(AlertManage alertManage) {
		super.delete(alertManage);
	}
	
}