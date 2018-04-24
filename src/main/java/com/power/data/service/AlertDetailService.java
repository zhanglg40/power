package com.power.data.service;

import com.power.data.dao.AlertDetailDao;
import com.power.data.entity.AlertDetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报警数据Service
 * @author zhanglg
 * @version 2018-04-22
 */
@Service
@Transactional(readOnly = true)
public class AlertDetailService extends CrudService<AlertDetailDao, AlertDetail> {

	public AlertDetail get(String id) {
		return super.get(id);
	}
	
	public List<AlertDetail> findList(AlertDetail alertDetail) {
		return super.findList(alertDetail);
	}
	
	public Page<AlertDetail> findPage(Page<AlertDetail> page, AlertDetail alertDetail) {
		return super.findPage(page, alertDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(AlertDetail alertDetail) {
		super.save(alertDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(AlertDetail alertDetail) {
		super.delete(alertDetail);
	}
	
}