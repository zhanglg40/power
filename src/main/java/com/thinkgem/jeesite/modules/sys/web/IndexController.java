/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.web;

import com.alibaba.fastjson.JSON;
import com.power.common.entity.Device2Entity;
import com.power.common.entity.DeviceEntity;
import com.power.common.service.Device2EntityService;
import com.power.common.service.DeviceAreaService;
import com.power.common.service.DeviceService;
import com.power.data.entity.AlertEntity;
import com.power.data.service.AlertService;
import com.power.monitoring.entity.MonitoringData;
import com.power.monitoring.service.MonitoringDataService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年12月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/index")
public class IndexController {
    @Autowired
    private AlertService alertService;
    @Autowired
    private MonitoringDataService monitoringDataService;
    @Autowired
    Device2EntityService deviceService;
    @Autowired
    DeviceAreaService deviceAreaService;
    @Autowired
    DeviceService dservice;
    @RequestMapping(value = {"index", ""})
    private String index(Model model){
        MonitoringData monitoringData=new MonitoringData(); 
        List<AlertEntity> alertList=new ArrayList<AlertEntity>();
        List<AlertEntity> alertList1=alertService.list();
        User user = UserUtils.getUser();
        int b=0;
        for(AlertEntity entity :alertList1){
            alertList.add(entity);
            b++;
            if(b>10)break;
        }
        model.addAttribute("alertList", alertList);
        model.addAttribute("user", user);
        Device2Entity device=new Device2Entity();

        List<Device2Entity> deviceList =  deviceService.findListByUser(user.getLoginName());
       
        List<Device2Entity> deviceList1 = deviceList.stream().filter(p->"FC".equals(p.getMonitoringType())).collect(Collectors.toList());
        model.addAttribute("FCCount", deviceList1.size());        
        List<Device2Entity> deviceList2 = deviceList.stream().filter(p->"SW".equals(p.getMonitoringType())).collect(Collectors.toList());
        model.addAttribute("SWCount", deviceList2.size());
        List<DeviceEntity> lds=dservice.findListByUser(user.getLoginName());
        model.addAttribute("deviceCount", lds.size());
        if(monitoringData==null||monitoringData.getMonitoringType()==null){
            device.setMonitoringType("FC");
            deviceList= deviceList1;
        }else{
            device.setMonitoringType(monitoringData.getMonitoringType());
            deviceList= deviceList2;
        }
        List<String> ll=new ArrayList<String>();
        model.addAttribute("deviceList", deviceList);

        if(StringUtils.isBlank(monitoringData.getSbbId())){
			if(deviceList==null||deviceList.size()==0){
				device=null;
			}else{
				device=deviceList.get(0);
				monitoringData.setSbbId(deviceList.get(0).getSbbId());
			}

        }else{
            device=deviceService.getBySbbId(monitoringData.getSbbId());
        }
        if(device!=null){
			if(device.getAreaId1()!=null){
				ll.add(device.getAreaId1());
			}
			if(device.getAreaId2()!=null){
				ll.add(device.getAreaId2());
			}
			//Date dd=new Date();
			Date dd=DateUtils.getBJDate();
        /*       Calendar cal1 = Calendar.getInstance();
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
						int n=0-5*j;
						map.put("y", 0);
						Date date=  monitoringData.getEndCreateDate();
						cal.setTime(date);
						cal.add(Calendar.MINUTE, n);
						map.put("x",cal.getTime());
						ls.add(map);
					}
				}else{
					for(MonitoringData e:list){
						Map<String,Object> map=new HashMap<String, Object>();
						double value=e.getMonitoringData()==null?0.0:Double.parseDouble(e.getMonitoringData());
						map.put("x", e.getCreateDate());
						map.put("y", value);
						ls.add(map);
					}

				}

				String name=deviceAreaService.getByCode(area).getName();
				data.put("name", name);
				data.put("data", ls);
				lst.add(data);
			}
			String datas = JSON.toJSONString(lst, true);
			model.addAttribute("data", datas);
		}

        return "modules/sys/index";
    }
}
