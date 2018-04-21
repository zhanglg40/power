/**
 * 
 */
package com.power.contrast.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.contrast.entity.ShowDataEntity;
import com.power.contrast.service.MonitorService;
import com.thinkgem.jeesite.common.annotation.FieldName;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年5月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/power/monitor")
public class MonitorController extends BaseController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    MonitorService monitorService;
    @Autowired
    private DictService dictService;
    @RequiresPermissions("power:contrast")
    @RequestMapping(value ="realTimeDataLine")
    public String realTimeDataLine(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response,
            Model model) throws ParseException{
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
        if(StringUtils.isBlank(showDataEntity.getSbbId())){
            showDataEntity.setSbbId(deviceList.get(0).getSbbId());
        }
        if(StringUtils.isBlank(showDataEntity.getCondition())){
            showDataEntity.setCondition("temperatureA");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd=DateUtils.getBJDate();
        showDataEntity.setDayTo(dd);
        String time=sdf.format(dd);
        // String time="2017-04-21 12:00:00" ;
      
         DeviceEntity deviceEntity =  deviceService.get(showDataEntity.getSbbId());
          dd=sdf.parse(time);
         showDataEntity.setDayTo(dd);
         showDataEntity.setDateTo(sdf.format(dd));
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         cal.add(Calendar.MINUTE, -60);
         Date dateFrom=cal.getTime();
         showDataEntity.setDayFrom(dateFrom);
       List<ShowDataEntity> list=monitorService.findList(showDataEntity);
      //  List<ShowDataEntity> list=null;
        Map<String,Object> data=new HashMap<String, Object>();
        List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
        if(list==null||list.size()==0){
            for(int i=0;i<12;i++){
               int j=12-i;
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("y", 0);
                int n=0-5*j;
              Date date=  showDataEntity.getDayTo();
              cal.setTime(date);
              cal.add(Calendar.MINUTE, n);
              map.put("x",cal.getTime());
              ls.add(map);
            }
           
        }else{
            for(ShowDataEntity e:list){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("x", e.getCreateDate());
                Field field;
                try {
                    field = e.getClass().getDeclaredField(showDataEntity.getCondition());
                    field.setAccessible(true);
                  
                        double value=field.get(e)==null?0.0:(double) field.get(e);
                  
                      map.put("y", value);
                } catch (NoSuchFieldException | SecurityException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }catch (IllegalArgumentException | IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                ls.add(map);
            } 
        }
        try {
        Field field=showDataEntity.getClass().getDeclaredField(showDataEntity.getCondition());
        FieldName vf = field.getAnnotation(FieldName.class);
        model.addAttribute("title", vf.value());
        } catch (NoSuchFieldException | SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data.put("data", ls);
        data.put("name", deviceEntity.getSbbName());
        List<Map<String,Object>> lst=new ArrayList<Map<String,Object>>();
        lst.add(data);
        String datas = JSON.toJSONString(lst, true);  
        model.addAttribute("data", datas);
        
        return "modules/power/monitor/realTimeDataLine";
    }
    @RequestMapping(value ="getData")
    public String  getData(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response) throws ParseException{
        String time= request.getParameter("time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.parse(time);
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
        }
        
        Date dateFrom = sdf.parse(time);
        showDataEntity.setDayFrom(dateFrom);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFrom);
        cal.add(Calendar.MINUTE, 5);
        Date dateTo=cal.getTime();
        showDataEntity.setDayTo(dateTo);
        String timeTo=sdf.format(dateTo);
        List<ShowDataEntity> list=monitorService.findList(showDataEntity);
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> lst=new ArrayList<JSONObject>();
        JSONObject data = new JSONObject();
        if(list==null||list.size()==0){
            jsonObject.put("time", timeTo);
            data.put("x", dateTo);
            data.put("y", 0.0);
            lst.add(data);
        }else{
            ShowDataEntity e=list.get(list.size()-1);
            jsonObject.put("time",sdf.format( e.getCreateDate()));
            data.put("x", e.getCreateDate());
           Field field;
        try {
            field = e.getClass().getDeclaredField(showDataEntity.getCondition());
            field.setAccessible(true);
            
            double value;
           
                value = field.get(e)==null?0.0:(double) field.get(e);
           
           data.put("y", value);
        } catch (NoSuchFieldException | SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException | IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
           
            lst.add(data);
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
    
    @RequiresPermissions("power:contrast")
    @RequestMapping(value ="realTimeDataContrast")
    public String realTimeDataContrast(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response,
            Model model) throws ParseException{
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);

        if(StringUtils.isBlank(showDataEntity.getSbbId())){
            showDataEntity.setSbbId("5582B94F-76CC-437A-AD61-4B493F272277,8160E2F6-6A06-4B26-9F86-B35BD4375338");
        }
        if(StringUtils.isBlank(showDataEntity.getCondition())){
            showDataEntity.setCondition("temperatureA");
        }
        showDataEntity.setSbbCId(showDataEntity.getSbbId());
        showDataEntity.setDayTo(DateUtils.getBJDate());
         String time="2017-04-21 12:00:00" ;
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       //  DeviceEntity deviceEntity =  deviceService.get(showDataEntity.getSbbId());
         Date dd=sdf.parse(time);
         showDataEntity.setDayTo(dd);
         showDataEntity.setDateTo(sdf.format(dd));
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         cal.add(Calendar.MINUTE, -60);
         Date dateFrom=cal.getTime();
         showDataEntity.setDayFrom(dateFrom);
         List<Map<String,Object>> lst=new ArrayList<Map<String,Object>>();
         for(String sbbId:showDataEntity.getSbbId().split(",")){
             showDataEntity.setSbbId(sbbId);
        
       List<ShowDataEntity> list=monitorService.findList(showDataEntity);
      //  List<ShowDataEntity> list=null;
        Map<String,Object> data=new HashMap<String, Object>();
        List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
       
        if(list==null||list.size()==0){
            for(int i=0;i<12;i++){
               int j=12-i-1;
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("y", 0);
                int n=0-5*j;
              Date date=  showDataEntity.getDayTo();
              cal.setTime(date);
              cal.add(Calendar.MINUTE, n);
              map.put("x",cal.getTime());
              ls.add(map);
            }
           
        }else{
            for(ShowDataEntity e:list){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("x", e.getCreateDate());
                Field field;
                try {
                    field = e.getClass().getDeclaredField(showDataEntity.getCondition());
                    field.setAccessible(true);
                  
                        double value=field.get(e)==null?0.0:(double) field.get(e);
                  
                      map.put("y", value);
                } catch (NoSuchFieldException | SecurityException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }catch (IllegalArgumentException | IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                ls.add(map);
            } 
        }
        try {
        Field field=showDataEntity.getClass().getDeclaredField(showDataEntity.getCondition());
        FieldName vf = field.getAnnotation(FieldName.class);
        model.addAttribute("title", vf.value());
        } catch (NoSuchFieldException | SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data.put("data", ls);
        String name="";
        for(DeviceEntity d:deviceList){
            if(d.getSbbId().equals(showDataEntity.getSbbId())){
                name=showDataEntity.getSbbName();
                break;
            }
        }
        data.put("name", name);
        lst.add(data);
         }
       
        String datas = JSON.toJSONString(lst, true);  
        model.addAttribute("data", datas);
        
        return "modules/power/monitor/multiDataLine";
    }
    @RequestMapping(value ="getmultiData")
    public String  getmultiData(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response) throws ParseException{
        String time= request.getParameter("time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.parse(time);
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
        }
        Date dateFrom = sdf.parse(time);
        showDataEntity.setDayFrom(dateFrom);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFrom);
        cal.add(Calendar.MINUTE, 5);
        Date dateTo=cal.getTime();
        showDataEntity.setDayTo(dateTo);
        String timeTo=sdf.format(dateTo);
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> lst=new ArrayList<JSONObject>();
        int i=0;
        String[] sbbIds=showDataEntity.getSbbId().split(",");
        for(String sbbId:sbbIds){
           // i++;
            showDataEntity.setSbbId(sbbId);
        List<ShowDataEntity> list=monitorService.findList(showDataEntity);
      
       
        JSONObject data = new JSONObject();
        if(list==null||list.size()==0){
            jsonObject.put("time", timeTo);
            data.put("x", dateTo);
            data.put("y", 0.0);
           
        }else{
            ShowDataEntity e=list.get(list.size()-1);
            jsonObject.put("time",sdf.format( e.getCreateDate()));
            data.put("x", e.getCreateDate());
           Field field;
        try {
            field = e.getClass().getDeclaredField(showDataEntity.getCondition());
            field.setAccessible(true);
            
            double value;
           
                value = field.get(e)==null?0.0:(double) field.get(e);
           
           data.put("y", value);
        } catch (NoSuchFieldException | SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException | IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        }
        data.put("type", i);
        i++;
            lst.add(data);
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
    }

