package com.power.gap.web;

import com.alibaba.fastjson.JSON;
import com.power.common.entity.DeviceEntity;
import com.power.common.service.DeviceService;
import com.power.contrast.entity.ShowDataEntity;
import com.power.data.entity.PowerDataEntity;
import com.power.data.service.PowerDataService;
import com.power.gap.entity.GapEntity;
import com.power.gap.service.GapService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2017/5/4.
 */
@Controller
@RequestMapping(value = "${adminPath}/power/gap")
public class GapController extends BaseController {
    @Autowired
    private GapService gapService;
    @Autowired
    AreaService areaService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PowerDataService  powerDataService;
    @RequiresPermissions("power:gap")
    @RequestMapping(value = { "dayList", "" })
    public String list(GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
     String type=request.getParameter("type")==null?"1":request.getParameter("type");
       if(StringUtils.isBlank(gapEntity.getSbbId())){
           gapEntity.setSbbId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
       }
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
       Date date=DateUtils.getBJDate();
       
       if(StringUtils.isBlank(gapEntity.getCheckDate())){
           gapEntity.setCheckDate(sdf.format(date));
       }
  /*      List<GapEntity> list = gapService.getDayList( gapEntity);
        String hours =null;
        String gaps = null;
        String sbbName = null;
        
       if(list!=null&&list.size()>0){
            sbbName=list.get(0).getSbbName();
            StringBuilder hourList=new StringBuilder();
            StringBuilder gapList=new StringBuilder();
            double gap=0.0;
            double fDegree=0;
            for(GapEntity e:list){
                if(fDegree==0.0){
                    gapList.append("0").append(",");
                     
                }else{
                    gap=e.getElectricalDegree()-fDegree;
                    DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
                 String d=   df.format(gap);
                    gapList.append(d).append(",");
                }
            //    System.out.println(gapList);
                fDegree=e.getElectricalDegree();
                hourList.append(e.getHours()).append(",");
            }

             hours = hourList.toString();
            hours=hours.substring(0,hours.length()-1);
             gaps = gapList.toString();
            gaps=gaps.substring(0,gaps.length()-1);
          
            
        }
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

        model.addAttribute("hourList", hours);
        model.addAttribute("gapList", gaps);
        model.addAttribute("sbbName", sbbName);
    //  List<DeviceEntity> deviceList =  deviceService.findAllList();


     */
		switch(type){
			case "1":
				model.addAttribute("showtype", "spline");
				model.addAttribute("view", "曲线图");
				break;
			case "2":
				model.addAttribute("showtype", "column");
				model.addAttribute("view", "柱状图");
				break;
		}
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
		model.addAttribute("deviceList", deviceList);
		model.addAttribute("type", type);
        return "modules/power/gap/dayList";
    }
    @RequiresPermissions("power:gap")
    @RequestMapping(value =  "dayListBar")
    public String dayListBar(GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
     
       if(StringUtils.isBlank(gapEntity.getSbbId())){
           gapEntity.setSbbId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
       }
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
       Date date=DateUtils.getBJDate();
       
       if(StringUtils.isBlank(gapEntity.getCheckDate())){
           gapEntity.setCheckDate(sdf.format(date));
       }
       List<GapEntity> list = gapService.getDayList( gapEntity);
        String hours =null;
        String gaps = null;
        String sbbName = null;
        if(list!=null&&list.size()>0){
            sbbName=list.get(0).getSbbName();
            StringBuilder hourList=new StringBuilder();
            StringBuilder gapList=new StringBuilder();
            double gap=0.0;
            double fDegree=0;
            for(GapEntity e:list){
                if(fDegree==0.0){
                    gapList.append("0").append(",");
                     
                }else{
                    gap=e.getElectricalDegree()-fDegree;
                    DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
                 String d=   df.format(gap);
                    gapList.append(d).append(",");
                }
                System.out.println(gapList);
                fDegree=e.getElectricalDegree();
                hourList.append(e.getHours()).append(",");
            }

             hours = hourList.toString();
            hours=hours.substring(0,hours.length()-1);
             gaps = gapList.toString();
            gaps=gaps.substring(0,gaps.length()-1);
          
            
        }
        model.addAttribute("hourList", hours);
        model.addAttribute("gapList", gaps);
        model.addAttribute("sbbName", sbbName);
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
      model.addAttribute("deviceList", deviceList);
        return "modules/power/gap/dayListBar";
    }
    @RequestMapping(value="DataPie")
    public String DataPie(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response,
            Model model){
        List<Map<String,Object>> list = gapService.getSbbElec(showDataEntity);
       /* List list=new ArrayList();
        Map map = new HashMap();  
        map.put("name", "a12");
        map.put("y", "12");
        list.add(map);
        Map map1 = new HashMap();  
        map1.put("name", "a13");
        map1.put("y", "1");
        list.add(map1);*/
        String data = JSON.toJSONString(list, true);  
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "a12");
        jsonObject.put("y", "12");*/
        if(list==null||list.size()==0){
            model.addAttribute("message", "没有数据");
        }
        model.addAttribute("data", data);
       
        return "modules/power/gap/dataPie";
    }
    @RequestMapping(value="dataGapBar")
    public String dataGapBar(ShowDataEntity showDataEntity, HttpServletRequest request, HttpServletResponse response,
            Model model){
        if(StringUtils.isBlank(showDataEntity.getSbbId())){
            showDataEntity.setSbbId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
        }
        if(StringUtils.isBlank(showDataEntity.getSbbCId())){
            showDataEntity.setSbbCId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
        }
        PowerDataEntity data=powerDataService.getLastData(showDataEntity.getSbbId());
        PowerDataEntity data1=powerDataService.getLastData(showDataEntity.getSbbCId());
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
        StringBuilder tmp=new StringBuilder();
        StringBuilder vol=new StringBuilder();
        StringBuilder cur=new StringBuilder();
        StringBuilder tmp1=new StringBuilder();
        StringBuilder vol1=new StringBuilder();
        StringBuilder cur1=new StringBuilder();
        if(data==null){
            tmp.append("0,").append("0,").append("0,").append("0,");
            vol.append("0,").append("0,").append("0,");
            cur.append("0,").append("0,").append("0,");
        }else{
            tmp.append("-").append(data.getTemperatureA()).append(",").append("-").append(data.getTemperatureB()).append(",")
            .append("-").append(data.getTemperatureC()).append(",").append("-").append(data.getTemperatureN()).append(",");
            vol.append("-").append(data.getVoltageA()).append(",").append("-").append(data.getVoltageB()).append(",")
            .append("-").append(data.getVoltageC()).append(",");
            cur.append("-").append(data.getCurrentA()).append(",").append("-").append(data.getCurrentB()).append(",")
            .append("-").append(data.getCurrentC()).append(",");
        }
        if(data1==null){
            tmp1.append("0,").append("0,").append("0,").append("0,");
            vol1.append("0,").append("0,").append("0,");
            cur1.append("0,").append("0,").append("0,");
        }else{
            tmp1.append(data1.getTemperatureA()).append(",").append(data1.getTemperatureB()).append(",")
            .append(data1.getTemperatureC()).append(",").append(data1.getTemperatureN()).append(",");
            vol1.append(data1.getVoltageA()).append(",").append(data1.getVoltageB()).append(",")
            .append(data1.getVoltageC()).append(",");
            cur1.append(data1.getCurrentA()).append(",").append(data1.getCurrentB()).append(",")
            .append(data1.getCurrentC()).append(",");
        }
        String tmps=tmp.toString();
        tmps=tmps.substring(0,tmps.length()-1);
        String tmps1=tmp1.toString();
        tmps1=tmps1.substring(0,tmps1.length()-1);
        String vols=vol.toString();
        vols=vols.substring(0,vols.length()-1);
        String vols1=vol1.toString();
        vols1=vols1.substring(0,vols1.length()-1);
        String curs=cur.toString();
        curs=curs.substring(0,curs.length()-1);
        String curs1=cur1.toString();
        curs1=curs1.substring(0,curs1.length()-1);
        String devName=  deviceList.stream().filter(e->e.getSbbId().equals(showDataEntity.getSbbId())).findFirst().get().getSbbName();
        String devName1=  deviceList.stream().filter(e->e.getSbbId().equals(showDataEntity.getSbbCId())).findFirst().get().getSbbName();
        model.addAttribute("tmpList", tmps);
        model.addAttribute("tmp1List", tmps1);
        model.addAttribute("volsList", vols);
        model.addAttribute("vols1List", vols1);
        model.addAttribute("cursList", curs);
        model.addAttribute("curs1List", curs1);
        
        model.addAttribute("devName", devName);
        model.addAttribute("devName1", devName1);
        model.addAttribute("deviceList", deviceList);
        return "modules/power/gap/dataGapBar";
    }
    @RequiresPermissions("power:gap:month")
    @RequestMapping(value =  "monthList/{showType}" )
    public String monthList(@PathVariable("showType") String showType ,GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
        String type=request.getParameter("type")==null?"1":request.getParameter("type");
       if(StringUtils.isBlank(gapEntity.getSbbId())){
           gapEntity.setSbbId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
       }
       Date date1 ;
       Date date2 ;
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       Calendar cal = Calendar.getInstance();

       if(StringUtils.isBlank(gapEntity.getDayFrom())||StringUtils.isBlank(gapEntity.getDayTo())){
           date2=DateUtils.getBJDate();
           cal.setTime(date2);
           cal.add(Calendar.DATE, -7);
           date1=cal.getTime();
		   gapEntity.setDayFrom(sdf.format(date1));
		   gapEntity.setDayTo(sdf.format(date2));
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
       gapEntity.setDateTo(cal.getTime());
       /*List<GapEntity> list = gapService.getMonthList( gapEntity);
       StringBuilder dayList=new StringBuilder();
       StringBuilder gapList=new StringBuilder();
       while(date1.getTime()<=date2.getTime()){
           Double d0=0.0;
           Double d1=0.0;
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date1);
           calendar.add(Calendar.DAY_OF_MONTH, -1);
           Date date0= calendar.getTime();
           String  dateString0 = sdf.format(date0);
           for(GapEntity e:list){
               if(e.getCheckDate().equals(dateString0)){
                   d0=e.getElectricalDegree();
                   break;
               }
           }
           calendar.setTime(date1);
           calendar.add(Calendar.DAY_OF_MONTH, 1);
           
         String  dateString = sdf.format(date1);
         dayList.append(dateString).append(",");
       
         for(GapEntity e:list){
             if(e.getCheckDate().equals(dateString)){
                 d1=e.getElectricalDegree();
                 break;
             }
         }
         if(d0==0.0|d1==0.0){
             gapList.append("0.0,");
         }else{
             DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
             String d=   df.format(d1-d0);
             gapList.append(d).append(",");
         }
       //  gapList.append(value).append(",");
        
          
           
           date1= calendar.getTime();
           
       }
      String sbbName="";
      if(list!=null&&list.size()>0){
          sbbName=list.get(0).getSbbName();
      }
      String days = dayList.toString();
      days=days.substring(0,days.length()-1);
      String   gaps = gapList.toString();
       gaps=gaps.substring(0,gaps.length()-1);
        
        model.addAttribute("dayList", days);
        model.addAttribute("gapList", gaps);
        model.addAttribute("sbbName", sbbName);*/
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
      model.addAttribute("deviceList", deviceList);
      switch(type){
      case "1":
          model.addAttribute("showtype", "spline");
          model.addAttribute("view", "曲线图");
          break;
      case "2":
          model.addAttribute("showtype", "column");
          model.addAttribute("view", "柱状图");
          break;
      }
      model.addAttribute("type", type);
      model.addAttribute("showType", showType);
        return "modules/power/gap/monthList";
    }
    @RequiresPermissions("power:gap:month")
    @RequestMapping(value =  "yearList/{showType}" )
    public String yearList(@PathVariable("showType") String showType ,GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
        String type=request.getParameter("type")==null?"1":request.getParameter("type");
        if(StringUtils.isBlank(gapEntity.getSbbId())){
            gapEntity.setSbbId("8160E2F6-6A06-4B26-9F86-B35BD4375338");
        }
        Date date=DateUtils.getBJDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        String  dateString0 = gapEntity.getDayFrom();
        dateString1=gapEntity.getDayTo();
        
        
       Date datef = sdf.parse(dateString0);
       cal.setTime(datef);
       cal.add(Calendar.MONTH, -1);
       datef=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateFrom(datef);
       Date datet = sdf.parse(dateString1);
       cal.setTime(datet);
       cal.add(Calendar.MONTH, 1);
       datet=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateTo(datet);
       StringBuilder dayList=new StringBuilder();
       StringBuilder gapList=new StringBuilder();
/*       List<GapEntity> list = gapService.getYearList( gapEntity);
       while(datef.getTime()<datet.getTime()){
           Double d0=0.0;
           Double d1=0.0;
           String  m0 = sdf.format(datef);
           for(GapEntity e:list){
               if(e.getCheckDate().equals(m0)){
                   d0=e.getElectricalDegree();
                   break;
               }
           }
           cal.setTime(datef);
           cal.add(Calendar.MONTH, 1);
           //当前月
           Date daten=   sdf.parse(sdf.format(cal.getTime())+"-01 00:00:00");
           String  m1 = sdf.format(daten);
           for(GapEntity e:list){
               if(e.getCheckDate().equals(m1)){
                   d1=e.getElectricalDegree();
                   break;
               }
           }
           dayList.append(m1).append(",");
           if(d1==0.0){
               gapList.append("0.0,");
           }else{
               DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
               String d=   df.format(d1-d0);
               gapList.append(d).append(",");
           }
           datef=daten;
       }
       String sbbName="";
       if(list!=null&&list.size()>0){
           sbbName=list.get(0).getSbbName();
       }
       String days = dayList.toString();
       days=days.substring(0,days.length()-1);
       String   gaps = gapList.toString();
        gaps=gaps.substring(0,gaps.length()-1);
		model.addAttribute("dayList", days);
		model.addAttribute("gapList", gaps);
		model.addAttribute("sbbName", sbbName);*/
        switch(type){
        case "1":
            model.addAttribute("showtype", "spline");
            model.addAttribute("view", "曲线图");
            break;
        case "2":
            model.addAttribute("showtype", "column");
            model.addAttribute("view", "柱状图");
            break;
        }

       /*  model.addAttribute("showType", showType);*/
         model.addAttribute("type", type);
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
       model.addAttribute("deviceList", deviceList);
       return "modules/power/gap/yearListLine"; 
      
       
    }
    @RequiresPermissions("power:gap:month")
    @RequestMapping(value =  "monthArea/{showType}" )
    public String monthArea(@PathVariable("showType") String showType ,GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
        Date date1 ;
        Date date2 ;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
        
        String type=request.getParameter("type")==null?"1":request.getParameter("type");
        
        if(gapEntity.getArea()==null||StringUtils.isBlank(gapEntity.getArea().getId())){
            Area area=new Area();
            area.setId("d792cb08fca3425db50fbb3bce802b61");
            gapEntity.setArea(area);
        }
       
        
      /*  String dayFrom=gapEntity.getDayFrom();
        String dayTo=gapEntity.getDayTo();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(dayFrom);
        Date date2 = sdf.parse(dayTo);*/
        
      //  Calendar cal = Calendar.getInstance();
        
        
        cal.setTime(date1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
       
        gapEntity.setDateFrom(cal.getTime());
        cal.setTime(date2);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        gapEntity.setDateTo(cal.getTime());
        List<GapEntity> list = gapService.getMonthArea( gapEntity);
        StringBuilder dayList=new StringBuilder();
        StringBuilder gapList=new StringBuilder();
        while(date1.getTime()<=date2.getTime()){
            Double d0=0.0;
            Double d1=0.0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date0= calendar.getTime();
            String  dateString0 = sdf.format(date0);
            for(GapEntity e:list){
                if(e.getCheckDate().equals(dateString0)){
                    d0=e.getElectricalDegree();
                    break;
                }
            }
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            
          String  dateString = sdf.format(date1);
          dayList.append(dateString).append(",");
        
          for(GapEntity e:list){
              if(e.getCheckDate().equals(dateString)){
                  d1=e.getElectricalDegree();
                  break;
              }
          }
          if(d0==0.0|d1==0.0){
              gapList.append("0.0,");
          }else{
              DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
              String d=   df.format(d1-d0);
              gapList.append(d).append(",");
          }
        //  gapList.append(value).append(",");
         
           
            
            date1= calendar.getTime();
            
        }
       String sbbName=areaService.get(gapEntity.getArea()).getName();
      
       String days = dayList.toString();
       days=days.substring(0,days.length()-1);
       String   gaps = gapList.toString();
        gaps=gaps.substring(0,gaps.length()-1);
         
         model.addAttribute("dayList", days);
         model.addAttribute("gapList", gaps);
         model.addAttribute("sbbName", sbbName);
		String userId = UserUtils.getUser().getLoginName();
		List<DeviceEntity> deviceList=  deviceService.findListByUser(userId);
       model.addAttribute("deviceList", deviceList);
/*       switch(showType){
       case "Line":
           model.addAttribute("type", "areaspline");
           model.addAttribute("view", "曲线图");
           break;
       case "Bar":
           model.addAttribute("type", "column");
           model.addAttribute("view", "柱状图");
           break;
       }*/
       switch(type){
       case "1":
           model.addAttribute("viewtype", "areaspline");
           model.addAttribute("view", "曲线图");
           break;
       case "2":
           model.addAttribute("viewtype", "column");
           model.addAttribute("view", "柱状图");
           break;
       }
       model.addAttribute("type", type);
       model.addAttribute("showType", showType);
        return "modules/power/gap/monthArea";
    }
    @RequiresPermissions("power:gap:month")
    @RequestMapping(value =  "yearArea/{showType}" )
    public String yearArea(@PathVariable("showType") String showType ,GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
        String type=request.getParameter("type")==null?"1":request.getParameter("type");

        if(gapEntity.getArea()==null||StringUtils.isBlank(gapEntity.getArea().getId())){
            Area area=new Area();
            area.setId("d792cb08fca3425db50fbb3bce802b61");
            gapEntity.setArea(area);
        }
        Date date=DateUtils.getBJDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        String  dateString0 = gapEntity.getDayFrom();
        dateString1=gapEntity.getDayTo();
        
        
       Date datef = sdf.parse(dateString0);
       cal.setTime(datef);
       cal.add(Calendar.MONTH, -1);
       datef=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateFrom(datef);
       Date datet = sdf.parse(dateString1);
       cal.setTime(datet);
       cal.add(Calendar.MONTH, 1);
       datet=sdf1.parse(sdf.format(cal.getTime())+"-01 00:00:00");
       gapEntity.setDateTo(datet);
       StringBuilder dayList=new StringBuilder();
       StringBuilder gapList=new StringBuilder();

       DeviceEntity entity1=new DeviceEntity();
       entity1.setQyId(gapEntity.getArea().getId());
		String userId = UserUtils.getUser().getLoginName();
       List<DeviceEntity> list1=  deviceService.findListByUser(userId);
       if(list1!=null&&list1.size()>0){
           gapEntity.setSbbId(list1.get(0).getSbbId());
       }
       List<GapEntity> list = gapService.getYearList( gapEntity);
       while(datef.getTime()<datet.getTime()){
           Double d0=0.0;
           Double d1=0.0;
           String  m0 = sdf.format(datef);
           for(GapEntity e:list){
               if(e.getCheckDate().equals(m0)){
                   d0=e.getElectricalDegree();
                   break;
               }
           }
           cal.setTime(datef);
           cal.add(Calendar.MONTH, 1);
           //当前月
           Date daten=   sdf.parse(sdf.format(cal.getTime())+"-01 00:00:00");
           String  m1 = sdf.format(daten);
           for(GapEntity e:list){
               if(e.getCheckDate().equals(m1)){
                   d1=e.getElectricalDegree();
                   break;
               }
           }
           dayList.append(m1).append(",");
           if(d1==0.0){
               gapList.append("0.0,");
           }else{
               DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
               String d=   df.format(d1-d0);
               gapList.append(d).append(",");
           }
           datef=daten;
       }
       String sbbName=areaService.get(gapEntity.getArea()).getName();

       String days = dayList.toString();
       days=days.substring(0,days.length()-1);
       String   gaps = gapList.toString();
        gaps=gaps.substring(0,gaps.length()-1);
         
         model.addAttribute("dayList", days);
         model.addAttribute("gapList", gaps);
         model.addAttribute("sbbName", sbbName);
         model.addAttribute("showType", showType);
	
       model.addAttribute("deviceList", list1);

       switch(type){
       case "1":
           model.addAttribute("viewtype", "areaspline");
           model.addAttribute("view", "曲线图");
           break;
       case "2":
           model.addAttribute("viewtype", "column");
           model.addAttribute("view", "柱状图");
           break;
       }
       model.addAttribute("type", type);
       return "modules/power/gap/yearArea"; 
      
       
    }
    @RequiresPermissions("power:gap")
    @RequestMapping(value =  "dayArea/{showType}" )
    public String dayArea(@PathVariable("showType") String showType ,GapEntity gapEntity, HttpServletRequest request, HttpServletResponse response,
                       Model model) throws ParseException {
        String type=request.getParameter("type")==null?"1":request.getParameter("type");
        if(gapEntity.getArea()==null||StringUtils.isBlank(gapEntity.getArea().getId())){
            Area area=new Area();
            area.setId("d792cb08fca3425db50fbb3bce802b61");
            gapEntity.setArea(area);
        }
        
/*       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
  
       if(gapEntity.getCreateDate()==null){
           gapEntity.setCreateDate(sdf.parse("2017-04-21"));
       }*/
       if(StringUtils.isBlank(gapEntity.getCheckDate())){
           gapEntity.setCheckDate("2017-04-21");
       }
       List<GapEntity> list = gapService.getDayArea( gapEntity);
        String hours =null;
        String gaps = null;
        String sbbName = areaService.get(gapEntity.getArea()).getName();
        if(list!=null&&list.size()>0){
            
            StringBuilder hourList=new StringBuilder();
            StringBuilder gapList=new StringBuilder();
            double gap=0.0;
            double fDegree=0;
            for(GapEntity e:list){
                if(fDegree==0.0){
                    gapList.append("0").append(",");
                     
                }else{
                    gap=e.getElectricalDegree()-fDegree;
                    DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
                 String d=   df.format(gap);
                    gapList.append(d).append(",");
                }
            //    System.out.println(gapList);
                fDegree=e.getElectricalDegree();
                hourList.append(e.getHours()).append(",");
            }

             hours = hourList.toString();
            hours=hours.substring(0,hours.length()-1);
             gaps = gapList.toString();
            gaps=gaps.substring(0,gaps.length()-1);
          
            
        }
        switch(type){
        case "1":
            model.addAttribute("viewtype", "areaspline");
            model.addAttribute("view", "曲线图");
            break;
        case "2":
            model.addAttribute("viewtype", "column");
            model.addAttribute("view", "柱状图");
            break;
        }
        model.addAttribute("type", type);
      /*  switch(showType){
        case "Line":
            model.addAttribute("type", "areaspline");
            model.addAttribute("view", "曲线图");
            break;
        case "Bar":
            model.addAttribute("type", "column");
            model.addAttribute("view", "柱状图");
            break;
        }*/
        model.addAttribute("hourList", hours);
        model.addAttribute("gapList", gaps);
        model.addAttribute("sbbName", sbbName);
       // model.addAttribute("showType", showType);
        return "modules/power/gap/dayArea";
    }
}
