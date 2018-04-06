/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.power.common.entity.DeviceArea;
import com.power.common.entity.DeviceEntity2;
import com.power.common.service.DeviceAreaService;
import com.power.common.service.DeviceEntity2Service;

/**
 * 设备管理Controller
 * @author zhanglg
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/common/deviceEntity2")
public class DeviceEntity2Controller extends BaseController {

	@Autowired
	private DeviceEntity2Service deviceEntity2Service;
	   @Autowired
	    private DeviceAreaService deviceAreaService;
	@ModelAttribute
	public DeviceEntity2 get(@RequestParam(required=false) String id) {
		DeviceEntity2 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deviceEntity2Service.get(id);
		}
		if (entity == null){
			entity = new DeviceEntity2();
		}
		return entity;
	}
	
	@RequiresPermissions("common:deviceEntity2:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeviceEntity2 deviceEntity2, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeviceEntity2> page = deviceEntity2Service.findPage(new Page<DeviceEntity2>(request, response), deviceEntity2); 
		model.addAttribute("page", page);
		List<DeviceArea> list=deviceAreaService.findList(new DeviceArea());
		model.addAttribute("areaList", list);
		return "modules/power/common/deviceEntity2List";
	}

	@RequiresPermissions("common:deviceEntity2:view")
	@RequestMapping(value = "form")
	public String form(DeviceEntity2 deviceEntity2, Model model) {
		model.addAttribute("deviceEntity2", deviceEntity2);
		List<DeviceArea> list=deviceAreaService.findList(new DeviceArea());
        model.addAttribute("areaList", list);
		return "modules/power/common/deviceEntity2Form";
	}

	@RequiresPermissions("common:deviceEntity2:edit")
	@RequestMapping(value = "save")
	public String save(DeviceEntity2 deviceEntity2, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deviceEntity2)){
			return form(deviceEntity2, model);
		}
		deviceEntity2Service.save(deviceEntity2);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/common/deviceEntity2/?repage";
	}
	
	@RequiresPermissions("common:deviceEntity2:edit")
	@RequestMapping(value = "delete")
	public String delete(DeviceEntity2 deviceEntity2, RedirectAttributes redirectAttributes) {
		deviceEntity2Service.delete(deviceEntity2);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/common/deviceEntity2/?repage";
	}

}