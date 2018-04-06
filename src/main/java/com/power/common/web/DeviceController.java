/**
 * 
 */
package com.power.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/power/device")
public class DeviceController extends BaseController {
   @Autowired
   private DeviceService deviceService;
   @ModelAttribute
   public DeviceEntity get(@RequestParam(required = false) String id) {
       if (StringUtils.isNotBlank(id)) {
           return deviceService.get(id);
       } else {
           return new DeviceEntity();
       }
   }
   @RequiresPermissions("power:device:view")
   @RequestMapping(value = { "list", "" })
   public String list(DeviceEntity deviceEntity, HttpServletRequest request, HttpServletResponse response,
                      Model model){
       Page<DeviceEntity> page = deviceService.findList(new Page<DeviceEntity>(request, response), deviceEntity);
       model.addAttribute("page", page);
       return "modules/power/device/deviceList";
   }
   @RequiresPermissions("power:device:view")
   @RequestMapping(value =  "form")
   public String form(DeviceEntity deviceEntity, Model model){
     

       return "modules/power/device/deviceForm";
   }
   @RequiresPermissions("power:device:edit")
   @RequestMapping(value =  "delete")
   public String delete( DeviceEntity deviceEntity, RedirectAttributes redirectAttributes){
     
       deviceService.delete(deviceEntity);
       addMessage(redirectAttributes, "删除成功");
       return "redirect:" + adminPath + "/power/device/list?repage";
       
   }
   @RequestMapping(value = "save")
   public String save(DeviceEntity deviceEntity,  HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
       if (!beanValidator(model, deviceEntity)){
           return form(deviceEntity, model);
       }
       deviceService.save(deviceEntity);
       addMessage(redirectAttributes, "保存成功");
       return "redirect:" + adminPath + "/power/device/list?repage";
   }
}
