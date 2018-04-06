/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.data.web;

import com.power.common.entity.DeviceEntity;
import com.power.common.entity.IchnographyEntity;
import com.power.common.service.DeviceService;
import com.power.common.service.IchnographyService;
import com.power.data.entity.IchnographyDevice;
import com.power.data.service.IchnographyDeviceService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 平面图设备Controller
 * @author zhanglg
 * @version 2017-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/data/ichnographyDevice")
public class IchnographyDeviceController extends BaseController {
    @Autowired
    private IchnographyService service;
	@Autowired
	private IchnographyDeviceService ichnographyDeviceService;
	@Autowired
	private DeviceService deviceService;
	@ModelAttribute
	public IchnographyDevice get(@RequestParam(required=false) String id) {
		IchnographyDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ichnographyDeviceService.get(id);
		}
		if (entity == null){
			entity = new IchnographyDevice();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(IchnographyDevice ichnographyDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
      
	    Page<IchnographyDevice> page = ichnographyDeviceService.findPage(new Page<IchnographyDevice>(request, response), ichnographyDevice); 
		model.addAttribute("page", page);
		return "modules/power/data/ichnographyDeviceList";
	}


	@RequestMapping(value = "form")
	public String form(IchnographyDevice ichnographyDevice, Model model) {
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
        IchnographyEntity ichnographyEntity= service.get(ichnographyDevice.getIchnographyId());
		model.addAttribute("ichnographyDevice", ichnographyDevice);
		model.addAttribute("ichnographyEntity", ichnographyEntity);
		return "modules/power/data/ichnographyDeviceForm";
	}

	@RequestMapping(value= "show")
    public String show(IchnographyDevice ichnographyDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
      
        
        return "modules/power/data/ichnographyShow";
    }
	

	@RequestMapping(value = "save")
	public String save(IchnographyDevice ichnographyDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ichnographyDevice)){
			return form(ichnographyDevice, model);
		}
		ichnographyDeviceService.save(ichnographyDevice);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/data/ichnographyDevice/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(IchnographyDevice ichnographyDevice, RedirectAttributes redirectAttributes) {
		ichnographyDeviceService.delete(ichnographyDevice);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/data/ichnographyDevice/?repage";
	}

}