/**
 * 
 */
package com.power.data.web;

import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.data.entity.PowerDataEntity;
import com.power.data.service.PowerDataService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月13日
 */
@Controller
@RequestMapping(value = "${adminPath}/power/powerData")
public class PowerDataController  extends BaseController{
    @Autowired
    private PowerDataService  powerDataService;
    @Autowired
    private DeviceService deviceService;
    @RequiresPermissions("power:gap")
    @RequestMapping(value = { "list", "" })
    public String list(PowerDataEntity powerDataEntity, HttpServletRequest request, HttpServletResponse response,
            Model model) {
      //  List<DeviceEntity> deviceList =  deviceService.findAllList();
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
		Page<PowerDataEntity> page =null;
		if(StringUtils.isBlank(powerDataEntity.getSbbId())){
            if(deviceList==null||deviceList.size()==0){
				model.addAttribute("page", page);
				return  "modules/power/powerData/dataList";
			}else {
				powerDataEntity.setSbbId(deviceList.get(0).getSbbId());
			}
		}
        page = powerDataService.findList(new Page<PowerDataEntity>(request, response), powerDataEntity);
        model.addAttribute("page", page);
        return  "modules/power/powerData/dataList";
    }
	@RequestMapping(value = "exportFile")
	public String exportFile(PowerDataEntity powerDataEntity, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "设备数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<PowerDataEntity> page1 = new Page<PowerDataEntity>(request, response);
			page1.setPageNo(1);
			page1.setPageSize(10000);
			Page<PowerDataEntity> page  =   page = powerDataService.findList(new Page<PowerDataEntity>(request, response), powerDataEntity);
			new ExportExcel("设备数据", PowerDataEntity.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/power/powerData/?repage";
	}
}
