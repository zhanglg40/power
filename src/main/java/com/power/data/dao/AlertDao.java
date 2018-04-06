/**
 * 
 */
package com.power.data.dao;

import com.power.data.entity.AlertEntity;
import com.sms.entity.SmsSetEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月20日
 */

@MyBatisDao
public interface AlertDao extends CrudDao<AlertEntity>{
   List<Map<String,Object>> getAlertPie(AlertEntity entity);

	List<AlertEntity> findAlert(SmsSetEntity sms);

	void updateStatus();

	List<String> phoneList(String sbbId);

	Integer findAlertCount(String sbbId);
}
