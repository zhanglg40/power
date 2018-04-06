/**
 * 
 */
package com.sms.service;

import org.springframework.stereotype.Service;

import com.sms.dao.SmsSetDao;
import com.sms.entity.SmsSetEntity;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年6月13日
 */
@Service
public class SmsSetService  extends CrudService<SmsSetDao, SmsSetEntity> {

    /**
     * 
     * @author zhanglg
     * @time  2017年6月13日
     * @param smsSetEntity
     */
    
    public void update(SmsSetEntity smsSetEntity) {
       dao.update(smsSetEntity);
        
    }

}
