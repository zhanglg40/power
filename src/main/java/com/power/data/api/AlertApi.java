package com.power.data.api;

import com.power.data.entity.AlertEntity;
import com.power.data.service.AlertService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanglg
 * @version 1.0
 * @team IT Team
 * @time 2018/2/8.
 */
@Controller
@RequestMapping("/api/alert")
public class AlertApi {
    @Autowired
    private AlertService service;
    @ResponseBody
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, HttpServletResponse response) {
		String userId =request.getParameter("userId");
		if(StringUtils.isBlank(userId)){
			return null;
		}
        String pageNo= request.getParameter("pageNo");
        String pageSize= request.getParameter("pageSize");
        AlertEntity entity = new AlertEntity();
        entity.setSbbId(request.getParameter("sbbId"));
        entity.setDateFrom(request.getParameter("dateFrom"));
        entity.setDelFlag(request.getParameter("delFlag"));
        entity.setDateTo(request.getParameter("dateTo"));
        Page<AlertEntity> p = new Page<AlertEntity>(request, response);
        p.setPageNo(Integer.parseInt(pageNo));
        p.setPageSize(Integer.parseInt(pageSize));
		User user= new User();
		user.setLoginName(userId);
		entity.setCreateBy(user);
        Page<AlertEntity> page = service.findPage(new Page<AlertEntity>(request, response), entity);
       if(page.getCount()<=(p.getPageNo()-1)*p.getPageSize())
       	return  null;
        return page.getList();
    }
}
