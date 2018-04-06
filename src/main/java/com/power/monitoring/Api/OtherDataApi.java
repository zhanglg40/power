/**
 * 
 */
package com.power.monitoring.Api;

import com.power.common.entity.Device2Entity;
import com.power.common.service.Device2EntityService;
import com.power.monitoring.entity.MonitoringData;
import com.power.monitoring.service.MonitoringDataService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time 2018年2月2日
 */
@Controller
@RequestMapping("/api/other")
public class OtherDataApi {
    @Autowired
    MonitoringDataService service;
    @Autowired
    private Device2EntityService device2EntityService;
    @ResponseBody
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, HttpServletResponse response) {
       String sbbId= request.getParameter("sbbId");
       String sbbType= request.getParameter("sbbType");
        String dateFrom= request.getParameter("dateFrom");
        String dateTo= request.getParameter("dateTo");
       String pageNo= request.getParameter("pageNo");
       String pageSize= request.getParameter("pageSize");
		String userId =request.getParameter("userId");
		if(StringUtils.isBlank(sbbId)){
			return new ArrayList<MonitoringData>();
		}
       MonitoringData m=new MonitoringData();
		Device2Entity device=device2EntityService.get(sbbId);
		m.setSbbId(device.getSbbId());
       m.setType(sbbType);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
			if(StringUtils.isNotBlank(dateFrom))
            m.setBeginCreateDate(sdf.parse(dateFrom));
			if(StringUtils.isNotBlank(dateTo))
            m.setEndCreateDate(sdf.parse(dateTo));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		User user= new User("1",userId);
		//user.setLoginName(userId);
		m.setCreateBy(user);
       Page<MonitoringData> p = new Page<MonitoringData>(request, response);
       p.setPageNo(Integer.parseInt(pageNo));
       p.setPageSize(Integer.parseInt(pageSize));
        Page<MonitoringData> page = service.findPage(new Page<MonitoringData>(request, response), m);
		if(page.getCount()<=(p.getPageNo()-1)*p.getPageSize())
			return  null;
        return page.getList();
    }
    @ResponseBody
    @RequestMapping("/monthList")
    public Object monthList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Device2Entity entity=new Device2Entity();
        Map<String,Object> map=new HashMap<String, Object>();
        String sbbId= request.getParameter("sbbId");
        String dateFrom= request.getParameter("dateFrom");
        String dateTo= request.getParameter("dateTo");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        entity.setSbbId(sbbId);
        entity= device2EntityService.get(sbbId);
        map.put("name", entity.getSbbName());
        map.put("areaName",entity.getAreaName1());
        map.put("mType",entity.getTypeName());
        MonitoringData md=new MonitoringData();
        md.setSbbId(sbbId);
        md.setDayFrom(dateFrom);
        md.setDayTo(dateTo);
        Date date1 ;
        Date date2 ;
        Calendar cal = Calendar.getInstance();

        if(StringUtils.isBlank(md.getDayFrom())||StringUtils.isBlank(md.getDayTo())){
            date2= DateUtils.getBJDate();
            cal.setTime(date2);
            cal.add(Calendar.DATE, -7);
            date1=cal.getTime();
        }else{
            String dayFrom=md.getDayFrom();
            String dayTo=md.getDayTo();

            date1 = sdf.parse(dayFrom);
            date2 = sdf.parse(dayTo);
        }

        cal.setTime(date1);
        cal.add(Calendar.DAY_OF_MONTH, -1);


        md.setBeginCreateDate(cal.getTime());
        cal.setTime(date2);
        cal.add(Calendar.DAY_OF_MONTH, 1);


        Date datef;
        try {
            md.setEndCreateDate(cal.getTime());
            List<MonitoringData> list = service.getMainMonthList( md);
            List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();

                    for(MonitoringData e:list){
                        Map<String,Object> mgap=new HashMap<String, Object>();
                        mgap.put("day",e.getYears()+"-"+  String.format("%02d",e.getMonths())+"-"+  String.format("%02d",e.getDays()));

                        mgap.put("value",e.getMonitoringData());


                        listData.add(mgap);
                    }

            map.put("data", listData);
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
