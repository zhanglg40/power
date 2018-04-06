/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.power.monitoring.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.power.common.entity.Device2Entity;
import com.power.common.service.Device2EntityService;
import com.power.common.service.DeviceAreaService;
import com.power.monitoring.entity.MonitoringData;
import com.power.monitoring.service.MonitoringDataService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 其他设备数据监测Controller
 * @author zhanglg
 * @version 2017-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/monitoring/monitoringData")
public class MonitoringDataController extends BaseController {

	@Autowired
	private MonitoringDataService monitoringDataService;
	@Autowired
	Device2EntityService deviceService;
	@Autowired
	DeviceAreaService deviceAreaService;
	@ModelAttribute
	public MonitoringData get(@RequestParam(required=false) String id) {
		MonitoringData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = monitoringDataService.get(id);
		}
		if (entity == null){
			entity = new MonitoringData();
		}
		return entity;
	}
	
	@RequiresPermissions("monitoring:monitoringData:view")
	@RequestMapping(value = {"list", ""})
	public String list(MonitoringData monitoringData, HttpServletRequest request, HttpServletResponse response, Model model) {
	    if(StringUtils.isBlank(monitoringData.getType())){
	        monitoringData.setType("FC");
	    }
		User user = UserUtils.getUser();
		monitoringData.setCreateBy(user);
		Page<MonitoringData> page = monitoringDataService.findPage(new Page<MonitoringData>(request, response), monitoringData); 
		model.addAttribute("page", page);
		return "modules/power/monitoring/monitoringDataList";
	}

	@RequiresPermissions("monitoring:monitoringData:view")
	@RequestMapping(value = "line")
	public String line(MonitoringData monitoringData, Model model) {
	    Device2Entity device=new Device2Entity();
	    if(monitoringData==null||monitoringData.getMonitoringType()==null){
	        device.setMonitoringType("FC");
	    }else{
	        device.setMonitoringType(monitoringData.getMonitoringType());
	    }
	   // List<Device2Entity> deviceList =  deviceService.findList(device);
		String userId = UserUtils.getUser().getLoginName();
		List<Device2Entity> deviceList =deviceService.findListByUser(userId);
				List<String> ll=new ArrayList<String>();
        model.addAttribute("deviceList", deviceList);
		if(StringUtils.isBlank(monitoringData.getSbbId())){
		    device=deviceList.get(0);
		    monitoringData.setSbbId(deviceList.get(0).getSbbId());
		}else{
		    device=deviceService.getBySbbId(monitoringData.getSbbId());
		}
		if(device.getAreaId1()!=null){
		    ll.add(device.getAreaId1());
		}
		if(device.getAreaId2()!=null){
		ll.add(device.getAreaId2());
		}
		//Date dd=new Date();
		Date dd=DateUtils.getBJDate();
		/*		 Calendar cal1 = Calendar.getInstance();
         cal1.setTime(dd);
         cal1.add(Calendar.DATE, -1);
         dd=cal1.getTime();*/
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
     
         monitoringData.setDateTo(sdf.format(dd));
		monitoringData.setEndCreateDate(dd);
		//  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Calendar cal = Calendar.getInstance();
	         cal.setTime(dd);
	         cal.add(Calendar.MINUTE, -60);
	         Date dateFrom=cal.getTime();
	         monitoringData.setBeginCreateDate(dateFrom);
	         List<Map<String,Object>> lst=new ArrayList<Map<String,Object>>();
		for(String area:ll){
		    monitoringData.setAreaId(area);
		    List<MonitoringData> list=monitoringDataService.findList(monitoringData);
		    Map<String,Object> data=new HashMap<String, Object>();
		    List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
		    if(list==null||list.size()==0){
	            for(int i=0;i<12;i++){
	               int j=12-i;
	                Map<String,Object> map=new HashMap<String, Object>();
	                
	                map.put("y", 0);
	                int n=0-5*j;
	              Date date=  monitoringData.getEndCreateDate();
	              cal.setTime(date);
	              cal.add(Calendar.MINUTE, n);
	              map.put("x",cal.getTime());
	              ls.add(map);
	            }
	           
	        }else{

	            for(MonitoringData e:list){
	                Map<String,Object> map=new HashMap<String, Object>();
	                map.put("x", e.getCreateDate());   
	                double value=e.getMonitoringData()==null?0.0:Double.parseDouble(e.getMonitoringData());
	                map.put("y", value);
	                ls.add(map);
	            } 
	        
	        }
		    data.put("data", ls);
		    String name=deviceAreaService.getByCode(area).getName();
	        data.put("name", name);
	        lst.add(data);
		}
		  String datas = JSON.toJSONString(lst, true);  
	        model.addAttribute("data", datas);
		return "modules/power/monitoring/monitoringDataLine";
	}

	    @RequestMapping(value ="getData")
	    public String  getData(MonitoringData monitoringData, HttpServletRequest request, HttpServletResponse response) throws ParseException{
	     Device2Entity   device=deviceService.getBySbbId(monitoringData.getSbbId());
	     
	     String time= request.getParameter("time");
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	            sdf.parse(time);
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.getMessage();
	        }
	        Date dateFrom = sdf.parse(time);
	        monitoringData.setBeginCreateDate(dateFrom);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dateFrom);
	        cal.add(Calendar.MINUTE, 5);
	        Date dateTo=cal.getTime();
	        monitoringData.setEndCreateDate(dateTo);
	        String timeTo=sdf.format(dateTo);
	        List<MonitoringData> list=monitoringDataService.findList(monitoringData);
	        JSONObject jsonObject = new JSONObject();
	        List<JSONObject> lst=new ArrayList<JSONObject>();
	       
	        if(list==null||list.size()==0){
	            jsonObject.put("time", timeTo);
	            JSONObject data = new JSONObject();
	            data.put("type", "1");
	            data.put("x", dateTo);
	            data.put("y", 0.0);
	            lst.add(data);
	            JSONObject data1 = new JSONObject();
	            data1.put("type", "2");
                data1.put("x", dateTo);
                data1.put("y", 0.0);
                lst.add(data1);
	        }else{
	            for(MonitoringData e:list){
	                JSONObject data = new JSONObject();
	                jsonObject.put("time",sdf.format( e.getCreateDate()));
	                data.put("x", e.getCreateDate());
	                double value;
	               
	                    value = e.getMonitoringData()==null?0.0:Double.parseDouble(e.getMonitoringData());
	               
	               data.put("y", value);
	           
	               if(e.getAreaId().equals(device.getAreaId1())){
	                   data.put("type", "1");
	               }else{
	                   data.put("type", "2");
	               }
	                lst.add(data);
	            }
	           
	            
	        }
	        jsonObject.put("data", lst);
	        try {
	         PrintWriter out = response.getWriter();
	       
	         out.print(jsonObject);
	     } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     } 
	        return null;  
	     }
	@RequestMapping(value =  "monthList/{showType}" )
	public String monthList(@PathVariable("showType") String showType , MonitoringData monitoringData, HttpServletRequest request, HttpServletResponse response,
							Model model) throws ParseException {
		String type=request.getParameter("type")==null?"1":request.getParameter("type");
		String userId = UserUtils.getUser().getLoginName();
		List<Device2Entity> list =deviceService.findListByUser(userId);
		model.addAttribute("deviceList",list);

		switch(type){
			case "1":
				model.addAttribute("showtype", "areaspline");
				model.addAttribute("view", "曲线图");
				break;
			case "2":
				model.addAttribute("showtype", "column");
				model.addAttribute("view", "柱状图");
				break;
		}
		model.addAttribute("type", type);
		model.addAttribute("entity", monitoringData);
		 return "modules/power/monitoring/monthList";
	}

}