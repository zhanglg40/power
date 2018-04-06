package com.power.contrast.web;

import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.contrast.entity.ShowDataEntity;
import com.power.contrast.service.HistoryDataService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@Controller
@RequestMapping(value = "${adminPath}/power/contrast/historyData")
public class HistoryDataController   extends BaseController {
    @Autowired
    private HistoryDataService historyDataService;
    @Autowired
    private DeviceService deviceService;
    @RequiresPermissions("power:contrast")
    @RequestMapping(value = { "dayList", "" })
    public String list(ShowDataEntity historyDataEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        if(StringUtils.isBlank(historyDataEntity.getDateTo())){
           
           
           // String str=sdf.format(new Date()); 
            String str="2017-04-23";
            historyDataEntity.setDateTo(str);
        }
        if(StringUtils.isBlank(historyDataEntity.getDateFrom())){
          
          
         
            Date date= sdf.parse(historyDataEntity.getDateTo()); 
            Calendar calendar  =   Calendar.getInstance();
            
            calendar.setTime(date); //需要将date数据转移到Calender对象中操作
            calendar.add(Calendar.DATE, -12);//把日期往后增加n天.正数往后推,负数往前移动 
            date=calendar.getTime(); 
            String str=sdf.format(date);  
            historyDataEntity.setDateFrom(str);
        }
        if(StringUtils.isBlank(historyDataEntity.getCondition())){
            historyDataEntity.setCondition("currentA");
        }
        Date date = sdf.parse(historyDataEntity.getDateFrom()); 
        Date dateTo = sdf.parse(historyDataEntity.getDateTo()); 
        List<String> th=new ArrayList<String>();
        th.add("");
        if(StringUtils.isBlank(historyDataEntity.getSbbList())){
            historyDataEntity.setSbbList("8160E2F6-6A06-4B26-9F86-B35BD4375338,5582B94F-76CC-437A-AD61-4B493F272277");
        }
        String[] sbbList=historyDataEntity.getSbbList().split(",");
        for(String subId:sbbList){
            for(DeviceEntity dev:deviceList){
                if(dev.getSbbId().equals(subId)){
                    th.add(dev.getSbbName());
                    break;
                }
            }
           
        }
        

         List<List<String>> tdList=new ArrayList<List<String>>();
        while(!date.after(dateTo)){
            List<String> td=new ArrayList<String>();
           
            td.add(sdf.format(date));
            
            for(String subId:sbbList){
                historyDataEntity.setSbbId(subId);
                historyDataEntity.setCreateDate(date);
               ShowDataEntity enData=historyDataService.getHistoryData(historyDataEntity);
               if(enData==null){
                   td.add("0");
               }else{
                   Field field =  enData.getClass().getDeclaredField(historyDataEntity.getCondition());
              
                   field.setAccessible(true);
                 String value=field.get(enData)==null?"0":field.get(enData).toString();
              
                 td.add(value);
               }
            }
            tdList.add(td);
            Calendar calendar  =   Calendar.getInstance();
            
            calendar.setTime(date); 
            calendar.add(Calendar.DATE, 1);
            date=calendar.getTime(); 
        }
       
       // tdList.add(th);
     /*   for(String sbbId:sbbList){
            historyDataEntity.setSbbId(sbbId); 
            Date  date1 = sdf.parse(historyDataEntity.getDateFrom()); 
            List<String> td=new ArrayList<String>();
            List<HistoryDataEntity> list = historyDataService.historyDataList( historyDataEntity);
            td.add(sbbId);
            while(!date1.after(dateTo)){
            Boolean f=false;
            HistoryDataEntity entity=new HistoryDataEntity();
            for(HistoryDataEntity e:list){
                String dd=sdf.format(date1);
                if(e.getDateFrom().equals(dd)){
                    f=true;
                    entity=e;
                    break;
                }
            }
            if(!f){
                entity=et;
            }
       //     HistoryDataEntity entity= list.stream().filter(e->e.getDateFrom().equals(sdf.format(date1))).findFirst().orElse(et);
            Field field =  entity.getClass().getDeclaredField(historyDataEntity.getCondition());
              System.out.println(1);
              field.setAccessible(true);
            String value=field.get(entity)==null?"0":field.get(entity).toString();
            System.out.println("value:"+value);
            td.add(value);
              Calendar calendar  =   Calendar.getInstance();
              
              calendar.setTime(date1); 
              calendar.add(Calendar.DATE, 1);
              date1=calendar.getTime(); 
            }
            tdList.add(td);
        }
*/
       
      // list.stream().filter(name -> name.startsWith("N")).count();
       
        model.addAttribute("listth", th);
        model.addAttribute("tdList", tdList);
        return "modules/power/contrast/historyDataDay";
    }
    @RequiresPermissions("power:contrast")
    @RequestMapping(value ="listBar")
    public String listBar(ShowDataEntity historyDataEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        model.addAttribute("deviceList", deviceList);
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        if(StringUtils.isBlank(historyDataEntity.getDateTo())){
           
           
           // String str=sdf.format(new Date()); 
            String str="2017-04-23";
            historyDataEntity.setDateTo(str);
        }
        if(StringUtils.isBlank(historyDataEntity.getDateFrom())){
          
          
         
            Date date= sdf.parse(historyDataEntity.getDateTo()); 
            Calendar calendar  =   Calendar.getInstance();
            
            calendar.setTime(date); //需要将date数据转移到Calender对象中操作
            calendar.add(Calendar.DATE, -12);//把日期往后增加n天.正数往后推,负数往前移动 
            date=calendar.getTime(); 
            String str=sdf.format(date);  
            historyDataEntity.setDateFrom(str);
        }
        if(StringUtils.isBlank(historyDataEntity.getCondition())){
            historyDataEntity.setCondition("currentA");
        }
        Date date = sdf.parse(historyDataEntity.getDateFrom()); 
        Date dateTo = sdf.parse(historyDataEntity.getDateTo()); 
        List<String> th=new ArrayList<String>();
        th.add("");
        if(StringUtils.isBlank(historyDataEntity.getSbbList())){
            historyDataEntity.setSbbList("8160E2F6-6A06-4B26-9F86-B35BD4375338,5582B94F-76CC-437A-AD61-4B493F272277");
        }
        String[] sbbList=historyDataEntity.getSbbList().split(",");
        for(String subId:sbbList){
            for(DeviceEntity dev:deviceList){
                if(dev.getSbbId().equals(subId)){
                    th.add(dev.getSbbName());
                    break;
                }
            }
           
        }
        

         List<List<String>> tdList=new ArrayList<List<String>>();
        while(!date.after(dateTo)){
            List<String> td=new ArrayList<String>();
           
            td.add(sdf.format(date));
            
            for(String subId:sbbList){
                historyDataEntity.setSbbId(subId);
                historyDataEntity.setCreateDate(date);
               ShowDataEntity enData=historyDataService.getHistoryData(historyDataEntity);
               if(enData==null){
                   td.add("0");
               }else{
                   Field field =  enData.getClass().getDeclaredField(historyDataEntity.getCondition());
              
                   field.setAccessible(true);
                 String value=field.get(enData)==null?"0":field.get(enData).toString();
              
                 td.add(value);
               }
            }
            tdList.add(td);
            Calendar calendar  =   Calendar.getInstance();
            
            calendar.setTime(date); 
            calendar.add(Calendar.DATE, 1);
            date=calendar.getTime(); 
        }
       
       // tdList.add(th);
     /*   for(String sbbId:sbbList){
            historyDataEntity.setSbbId(sbbId); 
            Date  date1 = sdf.parse(historyDataEntity.getDateFrom()); 
            List<String> td=new ArrayList<String>();
            List<HistoryDataEntity> list = historyDataService.historyDataList( historyDataEntity);
            td.add(sbbId);
            while(!date1.after(dateTo)){
            Boolean f=false;
            HistoryDataEntity entity=new HistoryDataEntity();
            for(HistoryDataEntity e:list){
                String dd=sdf.format(date1);
                if(e.getDateFrom().equals(dd)){
                    f=true;
                    entity=e;
                    break;
                }
            }
            if(!f){
                entity=et;
            }
       //     HistoryDataEntity entity= list.stream().filter(e->e.getDateFrom().equals(sdf.format(date1))).findFirst().orElse(et);
            Field field =  entity.getClass().getDeclaredField(historyDataEntity.getCondition());
              System.out.println(1);
              field.setAccessible(true);
            String value=field.get(entity)==null?"0":field.get(entity).toString();
            System.out.println("value:"+value);
            td.add(value);
              Calendar calendar  =   Calendar.getInstance();
              
              calendar.setTime(date1); 
              calendar.add(Calendar.DATE, 1);
              date1=calendar.getTime(); 
            }
            tdList.add(td);
        }
*/
       
      // list.stream().filter(name -> name.startsWith("N")).count();
       
        model.addAttribute("listth", th);
        model.addAttribute("tdList", tdList);
        return "modules/power/contrast/historyDataDayBar";
    }
}
