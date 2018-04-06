package com.thinkgem.jeesite.modules.sys.web;

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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.AlertManage;
import com.thinkgem.jeesite.modules.sys.service.AlertManageService;

/**
 * 报警类型Controller
 * @author zhanglg
 * @version 2018-01-27
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/alertManage")
public class AlertManageController extends BaseController {

	@Autowired
	private AlertManageService alertManageService;
	
	@ModelAttribute
	public AlertManage get(@RequestParam(required=false) String id) {
		AlertManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = alertManageService.get(id);
		}
		if (entity == null){
			entity = new AlertManage();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:alertManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlertManage alertManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AlertManage> page = alertManageService.findPage(new Page<AlertManage>(request, response), alertManage); 
		model.addAttribute("page", page);
		return "modules/sys/alertManageList";
	}

	@RequiresPermissions("sys:alertManage:view")
	@RequestMapping(value = "form")
	public String form(AlertManage alertManage, Model model) {
		model.addAttribute("alertManage", alertManage);
		return "modules/sys/alertManageForm";
	}

	@RequiresPermissions("sys:alertManage:edit")
	@RequestMapping(value = "save")
	public String save(AlertManage alertManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, alertManage)){
			return form(alertManage, model);
		}
		alertManageService.save(alertManage);
		addMessage(redirectAttributes, "保存报警类型成功");
		return "redirect:"+adminPath+"/sys/alertManage/?repage";
	}
	
	@RequiresPermissions("sys:alertManage:edit")
	@RequestMapping(value = "delete")
	public String delete(AlertManage alertManage, RedirectAttributes redirectAttributes) {
		alertManageService.delete(alertManage);
		addMessage(redirectAttributes, "删除报警类型成功");
		return "redirect:"+adminPath+"/sys/alertManage/?repage";
	}

}