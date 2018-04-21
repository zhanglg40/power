/**
 * 
 */
package com.power.data.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.data.entity.AlertEntity;
import com.power.data.entity.PowerDataEntity;
import com.power.data.service.AlertService;
import com.power.data.service.PowerDataService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月20日
 */
@Controller
@RequestMapping(value = "${adminPath}/power/alert")
public class AlertController   extends BaseController{
    @Autowired
    private AlertService service;
	@Autowired
	private PowerDataService pservice;
    @Autowired
    private DeviceService deviceService;
    @RequestMapping(value = { "list", "" })
    public String list(AlertEntity entity, HttpServletRequest request, HttpServletResponse response,
            Model model) {
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
		entity.setCreateBy(UserUtils.getUser());
        Page<AlertEntity> page = service.findList(new Page<AlertEntity>(request, response), entity);
        model.addAttribute("page", page);
        return  "modules/power/alert/alertList";
    }

    @RequestMapping(value = "getData")
    public String getData(AlertEntity entity, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        entity.setDelFlag("0");
        Date date=new Date();
      
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        entity.setDateTo(sdf.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -2);
        Date dateFrom=cal.getTime();
        entity.setDateFrom(sdf.format(dateFrom));
        List<AlertEntity> page = service.findList(entity);
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", page);
        try {
            PrintWriter out = response.getWriter();
          
            out.print(jsonObject);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
           return null;  
    }
    @RequestMapping(value = "delData")
    public String delData(AlertEntity entity, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        entity.setDelFlag("1");
       
      
       
         service.update(entity);
        
           return null;  
    }
    @RequestMapping(value = "alertPie")
    public String alertPie( AlertEntity entity,HttpServletRequest request, HttpServletResponse response,
            Model model) {
       // String type=request.getParameter("type");
     //   String type=entity.getAlertType();
		entity.setCreateBy(UserUtils.getUser());
        if(entity==null||StringUtils.isBlank(entity.getAlertType())){
            entity.setAlertType( "alert_type");
        }
       
        List<Map<String,Object>> list = service.getAlertPie(entity);
        if(list==null||list.size()==0){
            model.addAttribute("message", "没有数据");
        }
        String data = JSON.toJSONString(list, true);  
        model.addAttribute("data", data);
       
        return  "modules/power/alert/alertPie";
    }
    @RequestMapping(value = "map")
    public String map(AlertEntity entity,
                          Model model) {

        return "modules/power/alert/map";
    }

    @RequestMapping(value = "deviceList")
    public String deviceList(AlertEntity entity, HttpServletResponse response,
                      Model model) {
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        List<JSONObject> devices=new ArrayList<JSONObject >();
        for(DeviceEntity e:deviceList){
            if(StringUtils.isNotBlank(e.getLongitude())&&StringUtils.isNotBlank(e.getLatitude())){
                JSONObject  ja=new JSONObject ();
                ja.put("name",e.getSbbName());
                ja.put("latitude",e.getLatitude());
                ja.put("longitude",e.getLongitude());
				Integer al=service.findAlert(e.getSbbId());
				if(al>0){
					ja.put("alert",1);
				}else{
					ja.put("alert",0);
				}
				PowerDataEntity power = pservice.getLastData(e.getSbbId());
				ja.put("TA",power.getTemperatureA());
				ja.put("TB",power.getTemperatureB());
				ja.put("TC",power.getTemperatureC());
				ja.put("TN",power.getTemperatureN());
				ja.put("CA",power.getCurrentA());
				ja.put("CB",power.getCurrentB());
				ja.put("CC",power.getCurrentC());
				ja.put("CD",power.getCreateDate());
				ja.put("ED",power.getElectricalDegree());
				ja.put("VA",power.getVoltageA());
				ja.put("VB",power.getVoltageB());
				ja.put("VC",power.getVoltageC());
				ja.put("LE",power.getLeakageElectricity());
				ja.put("AP",power.getActivePower());
				ja.put("PF",power.getPowerFactor());
                devices.add(ja);
            }
        }
        try {
        response.setContentType("text/json");
            /*设置字符集为'UTF-8'*/
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        // 修改全局的全局日期格式
        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String jsonObj1 = JSONObject.toJSONStringWithDateFormat(devices, "yyyy-MM-dd");
        //String jsonObj1 = JSONObject.toJSONString(obj);

            out.println(jsonObj1);
            response.flushBuffer();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
