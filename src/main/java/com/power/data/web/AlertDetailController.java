package com.power.data.web;

import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.data.entity.AlertDetail;
import com.power.data.service.AlertDetailService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 报警数据Controller
 * @author zhanglg
 * @version 2018-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/data/alertDetail")
public class AlertDetailController extends BaseController {

	@Autowired
	private AlertDetailService alertDetailService;
	@Autowired
	private DeviceService deviceService;
	@ModelAttribute
	public AlertDetail get(@RequestParam(required=false) String id) {
		AlertDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = alertDetailService.get(id);
		}
		if (entity == null){
			entity = new AlertDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("data:alertDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlertDetail alertDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		String userId = UserUtils.getUser().getLoginName();
		alertDetail.setCreateBy(UserUtils.getUser());
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
		model.addAttribute("deviceList", deviceList);
		Page<AlertDetail> page = alertDetailService.findPage(new Page<AlertDetail>(request, response), alertDetail);
		model.addAttribute("page", page);
		return "modules/power/alert/alertDetailList";
	}

	@RequiresPermissions("data:alertDetail:view")
	@RequestMapping(value = "form")
	public String form(AlertDetail alertDetail, Model model) {
		model.addAttribute("alertDetail", alertDetail);
		return "modules/power/alert/alertDetailForm";
	}

	@RequiresPermissions("data:alertDetail:edit")
	@RequestMapping(value = "save")
	public String save(AlertDetail alertDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, alertDetail)){
			return form(alertDetail, model);
		}
		alertDetailService.save(alertDetail);
		addMessage(redirectAttributes, "保存报警数据成功");
		return "redirect:"+adminPath+"/data/alertDetail/?repage";
	}
	
	@RequiresPermissions("data:alertDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(AlertDetail alertDetail, RedirectAttributes redirectAttributes) {
		alertDetailService.delete(alertDetail);
		addMessage(redirectAttributes, "删除报警数据成功");
		return "redirect:"+adminPath+"/data/alertDetail/?repage";
	}

}