/**
 * 
 */
package com.power.gap.api;

import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.gap.entity.GapEntity;
import com.power.gap.service.GapService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2018年2月2日
 */
@Controller
@RequestMapping("/api/gap")
public class GapApi {
    @Autowired
    GapService service;
    @Autowired
    private DeviceService deviceService;
    @ResponseBody
    @RequestMapping("/yearList")
    public Object yearList(HttpServletRequest request, HttpServletResponse response) {
        DeviceEntity entity=new DeviceEntity();
        
        Map<String,Object> map=new HashMap<String, Object>();
        String sbbId= request.getParameter("sbbId");
        String itemType= request.getParameter("itemType");
        String dateFrom= request.getParameter("dateFrom");
        String dateTo= request.getParameter("dateTo");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        entity.setSbbId(sbbId);
        entity= deviceService.get(sbbId);
        map.put("name", entity.getSbbName());
        GapEntity gapEntity=new GapEntity();
        gapEntity.setSbbId(sbbId);
        gapEntity.setDayFrom(dateFrom);
        gapEntity.setDayTo(dateTo);
        Date date=DateUtils.getBJDate();
        Calendar cal = Calendar.getInstance();
        String  dateString1 = sdf.format(date);
        if(StringUtils.isBlank(gapEntity.getDayFrom())){
            cal.setTime(date);
            cal.add(Calendar.MONTH, -6);
            Date date1= cal.getTime();
            gapEntity.setDayFrom(sdf.format(date1));
        }
        if(StringUtils.isBlank(gapEntity.getDayTo())){
            gapEntity.setDayTo(dateString1);
        }
        if(StringUtils.isBlank(gapEntity.getDayFrom())){
            cal.setTime(date);
            cal.add(Calendar.MONTH, -6);
            Date date1= cal.getTime();
            gapEntity.setDayFrom(sdf.format(date1));
        }
        if(StringUtils.isBlank(gapEntity.getDayTo())){
            gapEntity.setDayTo(dateString1);
        }
        String  dateString0 = gapEntity.getDayFrom();
        dateString1=gapEntity.getDayTo();
        
        
       Date datef;
    try {
        datef = sdf.parse(dateString0);
    
       cal.setTime(datef);
       cal.add(Calendar.MONTH, -1);
       datef=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateFrom(datef);
       Date datet = sdf.parse(dateString1);
       cal.setTime(datet);
       cal.add(Calendar.MONTH, 1);
       datet=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateTo(datet);
       gapEntity.setSbbId(sbbId);
       List<GapEntity> list = service.getMainYearList( gapEntity);
       List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();
       Field[] f2 = gapEntity.getClass().getDeclaredFields();
       for (Field field : f2) {
        if(itemType.equals(field.getName())){
         for(GapEntity gap:list){
           Map<String,Object> mgap=new HashMap<String, Object>();
           mgap.put("month",gap.getYears()+"-"+  String.format("%02d",gap.getMonths()));
            String name =field.getName();
                    name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                     .toUpperCase());
             Method m = gapEntity.getClass().getMethod("get" + name);
             // 调用getter方法获取属性值
             Double value = (Double) m.invoke(gap);
           mgap.put("value",value.toString());
               
         
           listData.add(mgap);
          }
         break;
        }
       }
       map.put("data", listData);
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
        return map;
    }
    @ResponseBody
    @RequestMapping("/dayList")
    public Object dayList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DeviceEntity entity=new DeviceEntity();

        Map<String,Object> map=new HashMap<String, Object>();
        String sbbId= request.getParameter("sbbId");
        String itemType= request.getParameter("itemType");
        String dateTo= request.getParameter("dateTo");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        entity.setSbbId(sbbId);
        entity= deviceService.get(sbbId);
        map.put("name", entity.getSbbName()+"<br>"+dateTo);
        GapEntity gapEntity=new GapEntity();
        gapEntity.setSbbId(sbbId);
        gapEntity.setDayTo(dateTo);
       Date date= sdf.parse(dateTo);
        gapEntity.setDateTo(date);
        try {

            List<GapEntity> nlist = service.getMainDayList( gapEntity);
			List<GapEntity>  list =new ArrayList<>();
			if(nlist.size()>12){
				for(int n=nlist.size()-13;n<nlist.size();n++){
					list.add(nlist.get(n));
				}
			}else{
				list=nlist;
			}
            List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();
            Field[] f2 = gapEntity.getClass().getDeclaredFields();
            for (Field field : f2) {
                if(itemType.equals(field.getName())){
                    for(GapEntity gap:list){
                        Map<String,Object> mgap=new HashMap<String, Object>();
                        mgap.put("time",DateUtils.formatDate(gap.getDateTo(),"HH:mm"));
                        String name =field.getName();
                        name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                                .toUpperCase());
                        Method m = gapEntity.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        Double value = (Double) m.invoke(gap);
                        mgap.put("value",value.toString());


                        listData.add(mgap);
                    }
                    break;
                }
            }
            map.put("data", listData);
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/monthList")
    public Object monthList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DeviceEntity entity=new DeviceEntity();

        Map<String,Object> map=new HashMap<String, Object>();
        String sbbId= request.getParameter("sbbId");
        String itemType= request.getParameter("itemType");
        String dateFrom= request.getParameter("dateFrom");
        String dateTo= request.getParameter("dateTo");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        entity.setSbbId(sbbId);
        entity= deviceService.get(sbbId);
        map.put("name", entity.getSbbName());
        GapEntity gapEntity=new GapEntity();
        gapEntity.setSbbId(sbbId);
        gapEntity.setDayFrom(dateFrom);
        gapEntity.setDayTo(dateTo);
        Date date1 ;
        Date date2 ;
        Calendar cal = Calendar.getInstance();

        if(StringUtils.isBlank(gapEntity.getDayFrom())||StringUtils.isBlank(gapEntity.getDayTo())){
            date2=DateUtils.getBJDate();
            cal.setTime(date2);
            cal.add(Calendar.DATE, -7);
            date1=cal.getTime();
        }else{
            String dayFrom=gapEntity.getDayFrom();
            String dayTo=gapEntity.getDayTo();

            date1 = sdf.parse(dayFrom);
            date2 = sdf.parse(dayTo);
        }

        cal.setTime(date1);
        cal.add(Calendar.DAY_OF_MONTH, -1);


        gapEntity.setDateFrom(cal.getTime());
        cal.setTime(date2);
        cal.add(Calendar.DAY_OF_MONTH, 1);


        Date datef;
        try {
            gapEntity.setDateTo(cal.getTime());
            List<GapEntity> list = service.getMainMonthList( gapEntity);
            List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();
            Field[] f2 = gapEntity.getClass().getDeclaredFields();
            for (Field field : f2) {
                if(itemType.equals(field.getName())){
                    for(GapEntity gap:list){
                        Map<String,Object> mgap=new HashMap<String, Object>();
                        mgap.put("day",gap.getYears()+"-"+  String.format("%02d",gap.getMonths())+"-"+  String.format("%02d",gap.getDays()));
                        String name =field.getName();
                        name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                                .toUpperCase());
                        Method m = gapEntity.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        Double value = (Double) m.invoke(gap);
                        mgap.put("value",value.toString());


                        listData.add(mgap);
                    }
                    break;
                }
            }
            map.put("data", listData);
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/realList")
    public Object  realList(HttpServletRequest request, HttpServletResponse response){
        DeviceEntity entity=new DeviceEntity();

        Map<String,Object> map=new HashMap<String, Object>();
        String sbbId= request.getParameter("sbbId");
        String itemType= request.getParameter("itemType");
        String type= request.getParameter("type");
        entity.setSbbId(sbbId);
        entity= deviceService.get(sbbId);
        map.put("name", entity.getSbbName());
        map.put("itemType", itemType);
        GapEntity gapEntity=new GapEntity();
        Date date=DateUtils.getBJDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if("1".equals(type)){

            cal.add(Calendar.HOUR, -1);
        }else{
            cal.add(Calendar.MINUTE, -5);
        }

        Date date1=cal.getTime();
        gapEntity.setDateFrom(date1);
        gapEntity.setSbbId(sbbId);
        List<GapEntity> list = service.getRealList( gapEntity);
        List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();
        Field[] f2 = gapEntity.getClass().getDeclaredFields();
        for (Field field : f2) {
            if(itemType.equals(field.getName())) {
                for (GapEntity gap : list) {

                    try {
                        Map<String,Object> mgap=new HashMap<String, Object>();
                        mgap.put("time",DateUtils.formatDate(gap.getDateTo(),"HH:mm"));
                       // mgap.put("itemType",itemType);
                        String name =field.getName();
                        name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                                .toUpperCase());
                        Method m  = gapEntity.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        Double value =  (Double) m.invoke(gap);
                        mgap.put("value",value.toString());
                        listData.add(mgap);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        map.put("data", listData);
        return map;
    }
}
