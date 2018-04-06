/**
 * 
 */
package com.sms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sms.entity.SmsSetEntity;
import com.sms.service.SmsSetService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年6月13日
 */
@Controller
@RequestMapping(value = "${adminPath}/sms/set")
public class SmsSetController extends BaseController {
    @Autowired
    private SmsSetService service;
    @ModelAttribute
    public SmsSetEntity get(@RequestParam(required=false) String id) {
        SmsSetEntity entity = null;
      
            entity = service.get(id);
       
    
        return entity;
    }
    @RequiresPermissions("common:deviceArea:view")
    @RequestMapping(value = "form")
    public String form(SmsSetEntity smsSetEntity, Model model) {
        model.addAttribute("smsSetEntity", smsSetEntity);
        return "modules/sms/setForm";
    }

    @RequiresPermissions("common:deviceArea:edit")
    @RequestMapping(value = "save")
    public String save(SmsSetEntity smsSetEntity, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, smsSetEntity)){
            return form(smsSetEntity, model);
        }
        service.update(smsSetEntity);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:"+Global.getAdminPath()+"/sms/set/form/?repage";
    }
}
