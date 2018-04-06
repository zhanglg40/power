/**
 * 
 */
package com.power.data.service;

import com.power.data.dao.AlertDao;
import com.power.data.entity.AlertEntity;
import com.sms.dao.SmsSetDao;
import com.sms.entity.SmsSetEntity;
import com.sms.util.SendSMS;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月20日
 */
@Service
@Lazy(false)
@Transactional(readOnly = true)
public class AlertService   extends CrudService<AlertDao,AlertEntity>{
@Autowired
	SmsSetDao sdao;
    /**
     * 
     * @author zhanglg
     * @time  2017年5月20日
     * @param page
     * @param entity
     * @return
     */
    
    public Page<AlertEntity> findList(Page<AlertEntity> page, AlertEntity entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }
    public List<Map<String,Object>> getAlertPie(AlertEntity entity){
		User user = UserUtils.getUser();
		entity.setCreateBy(user);
      return  dao.getAlertPie(entity);
    }
    public List<AlertEntity> list( ) {
		User user = UserUtils.getUser();
		AlertEntity entity=new AlertEntity();
		entity.setCreateBy(user);
        return dao.findList(entity);
    }
    /**
     * 
     * @author zhanglg
     * @time  2017年6月9日
     * @param entity
     */
    
    public void update(AlertEntity entity) {
        dao.update(entity);
        
    }
	@Transactional(readOnly = false)
    @Scheduled(cron = "0 0/5 * * * ?")
	public void AlertCheck() throws ParseException {
		SmsSetEntity sms=	(SmsSetEntity)CacheUtils.get("sms", "sms");
		if (sms == null){
			sms = sdao.get("");
			CacheUtils.put("sms", "sms", sms);
		}
		if(sms.getSendTime()>0)
		   sms.setSendTime(0-sms.getSendTime());
	    List<AlertEntity> list = dao.findAlert(sms);
		Map<String, List<AlertEntity>> collect =list.stream().collect(Collectors.groupingBy(c -> c.getSbbId()));
		Iterator<Map.Entry<String, List<AlertEntity>>> it = collect.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<AlertEntity>> entry = it.next();
			List<AlertEntity> los = collect.get(entry.getKey());
			AlertEntity entity = los.get(0);
            List<String> phonelist=dao.phoneList(entity.getSbbId());
			String phone=	String.join(",", phonelist);
			if(StringUtils.isNotBlank(phone)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String content=sms.getSmsContent().replace("{报警时间}",sdf.format(entity.getAlertTime()))
						.replace("{设备名称}",entity.getSbbName())
						.replace("{报警类型}",entity.getAlertType());
				SendSMS.sendSMS(content,phone);
			}


		}
		dao.updateStatus();
	}

	public Integer findAlert(String sbbId) {
		return dao.findAlertCount( sbbId);
	}
}
