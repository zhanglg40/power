/**
 * 
 */
package com.power.common.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.power.common.entity.Device2Entity;
import com.power.common.service.Device2EntityService;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2017年7月22日
 */
@Controller
@RequestMapping("${adminPath}/deviceApi")
public class Device2EntityApi {
    @Autowired
    private Device2EntityService device2EntityService;
    @ResponseBody
    @RequestMapping("list")
    public List<Device2Entity> list(Device2Entity device2Entity, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Device2Entity> list = device2EntityService.findList(device2Entity); 
      
        return list;
    }

}
