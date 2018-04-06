/**
 * 
 */
package com.power.data.api;

import com.power.data.entity.PowerDataEntity;
import com.power.data.service.PowerDataService;
import com.thinkgem.jeesite.common.api.RequestBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2018年1月25日
 */
@Controller
@RequestMapping("/api/power")
public class PowerApi {
    @Autowired
    PowerDataService service;
    @ResponseBody
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, RequestBean requestBean) {

        PowerDataEntity power = new PowerDataEntity();
        power.setSbbId(request.getParameter("sbbId"));
        power.setDateFrom(request.getParameter("dateFrom"));
        power.setDateTo(request.getParameter("dateTo"));
		if(StringUtils.isBlank(power.getSbbId())){
            return null;
		}
       List<PowerDataEntity> list= service.findApiList(power,null,null);
        return list;
    }
}
