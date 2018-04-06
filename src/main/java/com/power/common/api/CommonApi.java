package com.power.common.api;

import com.power.common.entity.Device2Entity;
import com.power.common.service.Device2EntityService;
import com.power.common.service.DeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2018/2/6.
 */
@Controller
@RequestMapping("/api/common")
public class CommonApi {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private Device2EntityService device2EntityService;
    @ResponseBody
    @RequestMapping( "/deviceList")
    public Object list(HttpServletRequest request){
		String userId =request.getParameter("userId");
		if(StringUtils.isBlank(userId)){
			return null;
		}
        return deviceService.findListByUser(userId);
    }
    @ResponseBody
    @RequestMapping( "/device2List")
    public Object device2List(HttpServletRequest request){
		String userId =request.getParameter("userId");
		if(StringUtils.isBlank(userId)){
			return null;
		}
        return device2EntityService.findListByUser(userId);
    }
}
