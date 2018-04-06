/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.common.web;

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
import com.power.common.service.DeviceAreaService;

/**
 * 监测区域Controller
 * @author zhanglg
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/common/deviceArea")
public class DeviceAreaController extends BaseController {

	@Autowired
	private DeviceAreaService deviceAreaService;
	
	@ModelAttribute
	public DeviceArea get(@RequestParam(required=false) String id) {
		DeviceArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deviceAreaService.get(id);
		}
		if (entity == null){
			entity = new DeviceArea();
		}
		return entity;
	}
	
	@RequiresPermissions("common:deviceArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeviceArea deviceArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeviceArea> page = deviceAreaService.findPage(new Page<DeviceArea>(request, response), deviceArea); 
		model.addAttribute("page", page);
		return "modules/power/common/deviceAreaList";
	}

	@RequiresPermissions("common:deviceArea:view")
	@RequestMapping(value = "form")
	public String form(DeviceArea deviceArea, Model model) {
		model.addAttribute("deviceArea", deviceArea);
		return "modules/power/common/deviceAreaForm";
	}

	@RequiresPermissions("common:deviceArea:edit")
	@RequestMapping(value = "save")
	public String save(DeviceArea deviceArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deviceArea)){
			return form(deviceArea, model);
		}
		deviceAreaService.save(deviceArea);
		addMessage(redirectAttributes, "保存监测区域成功");
		return "redirect:"+Global.getAdminPath()+"/common/deviceArea/?repage";
	}
	
	@RequiresPermissions("common:deviceArea:edit")
	@RequestMapping(value = "delete")
	public String delete(DeviceArea deviceArea, RedirectAttributes redirectAttributes) {
		deviceAreaService.delete(deviceArea);
		addMessage(redirectAttributes, "删除监测区域成功");
		return "redirect:"+Global.getAdminPath()+"/common/deviceArea/?repage";
	}

}