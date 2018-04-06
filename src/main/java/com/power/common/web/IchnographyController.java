/**
 * 
 */
package com.power.common.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.power.common.entity.IchnographyEntity;
import com.power.common.service.IchnographyService;
import com.power.data.entity.IchnographyDevice;
import com.power.data.entity.PowerDataEntity;
import com.power.data.service.IchnographyDeviceService;
import com.power.data.service.PowerDataService;
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
@RequestMapping(value = "${adminPath}/power/ichnography")
public class IchnographyController extends BaseController {
   @Autowired
   private IchnographyService service;
   
   @Autowired
   private IchnographyDeviceService ichnographyDeviceService;
   
   @Autowired
   private PowerDataService powerDataService;
   @ModelAttribute
   public IchnographyEntity get(@RequestParam(required = false) String id) {
       if (StringUtils.isNotBlank(id)) {
           return service.get(id);
       } else {
           return new IchnographyEntity();
       }
   }
   @RequiresPermissions("power:ichnography:view")
   @RequestMapping(value = { "list", "" })
   public String list(IchnographyEntity ichnographyEntity, HttpServletRequest request, HttpServletResponse response,
                      Model model){
       Page<IchnographyEntity> page = service.findList(new Page<IchnographyEntity>(request, response), ichnographyEntity);
       model.addAttribute("page", page);
       return "modules/power/ichnography/ichnographyList";
   }
   @RequiresPermissions("power:ichnography:view")
   @RequestMapping(value =  "form")
   public String form(IchnographyEntity ichnographyEntity, Model model){

       return "modules/power/ichnography/ichnographyForm";
   }
   @RequiresPermissions("power:ichnography:edit")
   @RequestMapping(value =  "delete")
   public String delete( IchnographyEntity ichnographyEntity, RedirectAttributes redirectAttributes){
     
       service.delete(ichnographyEntity);
       addMessage(redirectAttributes, "删除成功");
       return "redirect:" + adminPath + "/power/ichnography/list?repage";
       
   }
   @RequestMapping(value= "get")
   public String get(IchnographyEntity ichnographyEntity, Model model){
       ichnographyEntity=service.getByArea(ichnographyEntity);
       IchnographyDevice dev=new IchnographyDevice();
       dev.setIchnographyId(ichnographyEntity.getId());
       List<IchnographyDevice> list= ichnographyDeviceService.findList(dev);
       model.addAttribute("deviceList", list);
       model.addAttribute("ichnographyEntity", ichnographyEntity);
       
       return "modules/power/ichnography/ichnographyShow";
   }
   @RequestMapping(value= "getLastData")
   public String getLastData(HttpServletRequest request,HttpServletResponse response,IchnographyEntity ichnographyEntity, Model model){
      String id=request.getParameter("id");
       PowerDataEntity data=powerDataService.getLastData(id);
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("data", data);
       try {
          
           PrintWriter out = response.getWriter();
         
           out.print(jsonObject);
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } 
          return null;
   }
   
   @RequiresPermissions("power:ichnography:edit")
   @RequestMapping(value = "save")
   public String save(IchnographyEntity ichnographyEntity,  HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
       if (!beanValidator(model, ichnographyEntity)){
           return form(ichnographyEntity, model);
       }
       service.save(ichnographyEntity);
       addMessage(redirectAttributes, "保存成功");
       return "redirect:" + adminPath + "/power/ichnography/list?repage";
   }
}
