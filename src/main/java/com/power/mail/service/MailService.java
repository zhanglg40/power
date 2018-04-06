/**
 * 
 */
package com.power.mail.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.power.mail.bean.MailBean;
import com.power.mail.dao.MailDao;
import com.power.mail.entity.MailEntity;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.VerifyEmailHandler;



/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2016年10月28日
 */
@Service
@Transactional(readOnly = true)
public class MailService extends CrudService<MailDao,MailEntity>{

    /**
     * 
     * @author zhanglg
     * @time  2016年10月28日
     * @param bean
     */
    
    public void sendEmail(MailBean bean) {
        VerifyEmailHandler.getInstance().sendEmail(bean);

        
    }
  
}
