/**
 * 
 */
package com.power.mail.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.power.mail.bean.MailBean;
import com.power.mail.service.MailService;
import com.thinkgem.jeesite.common.api.RequestBean;
import com.thinkgem.jeesite.common.api.ResponseBean;




/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2016年10月28日
 */
@Controller
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    MailService mailService;
    
    @ResponseBody
    @RequestMapping("send")
    public ResponseBean send(RequestBean requestBean) {
        ResponseBean responseBean = new ResponseBean();
        MailBean bean = requestBean.getObjectBean(MailBean.class);

    
       
            mailService.sendEmail(bean);
            return responseBean;
       
     
    }
}
