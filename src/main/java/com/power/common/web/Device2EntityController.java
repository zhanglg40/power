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
import com.power.common.entity.Device2Entity;
import com.power.common.entity.DeviceArea;
import com.power.common.service.Device2EntityService;
import com.power.common.service.DeviceAreaService;

/**
 * 设备管理Controller
 * @author zhanglg
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/common/device2Entity")
public class Device2EntityController extends BaseController {
    @Autowired
    private DeviceAreaService deviceAreaService;
	@Autowired
	private Device2EntityService device2EntityService;
	
	@ModelAttribute
	public Device2Entity get(@RequestParam(required=false) String id) {
		Device2Entity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = device2EntityService.get(id);
		}
		if (entity == null){
			entity = new Device2Entity();
		}
		return entity;
	}
	
	@RequiresPermissions("common:device2Entity:view")
	@RequestMapping(value = {"list", ""})
	public String list(Device2Entity device2Entity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Device2Entity> page = device2EntityService.findPage(new Page<Device2Entity>(request, response), device2Entity); 
		model.addAttribute("page", page);
		return "modules/power/common/device2EntityList";
	}

	@RequiresPermissions("common:device2Entity:view")
	@RequestMapping(value = "form")
	public String form(Device2Entity device2Entity, Model model) {
	       List<DeviceArea> list=deviceAreaService.findList(new DeviceArea());
	        model.addAttribute("areaList", list);
		model.addAttribute("device2Entity", device2Entity);
		return "modules/power/common/device2EntityForm";
	}

	@RequiresPermissions("common:device2Entity:edit")
	@RequestMapping(value = "save")
	public String save(Device2Entity device2Entity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, device2Entity)){
			return form(device2Entity, model);
		}
		device2EntityService.save(device2Entity);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/common/device2Entity/?repage";
	}
	
	@RequiresPermissions("common:device2Entity:edit")
	@RequestMapping(value = "delete")
	public String delete(Device2Entity device2Entity, RedirectAttributes redirectAttributes) {
		device2EntityService.delete(device2Entity);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/common/device2Entity/?repage";
	}

}