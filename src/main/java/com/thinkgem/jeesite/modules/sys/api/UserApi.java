/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.api;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.api.RequestBean;
import com.thinkgem.jeesite.modules.sys.entity.Login;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2018年1月22日
 */

@Controller
@RequestMapping("/api/user")
public class UserApi {
	private final Logger LOG = Logger.getLogger(UserApi.class);
    @Autowired
    SystemService service;
    
    @ResponseBody
    @RequestMapping("/login")
    public Object login(HttpServletRequest request, RequestBean requestBean) {
        request.getParameterNames();
        Enumeration enu=request.getParameterNames();
		String data="";
        while(enu.hasMoreElements()){
        String paraName=(String)enu.nextElement();
        System.out.println(paraName+": "+request.getParameter(paraName));
			LOG.warn(paraName+": "+request.getParameter(paraName));
			data=paraName;
        }
        Map map=request.getParameterMap();
        Set keSet=map.entrySet();
        for(Iterator itr=keSet.iterator();itr.hasNext();){
            Map.Entry me=(Map.Entry)itr.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }
            for(int k=0;k<value.length;k++){
				LOG.warn(ok+"="+value[k]);

            }
          }
       String username= request.getParameter("username");
       String password= request.getParameter("password");
       if(StringUtils.isBlank(username)){
		   Login l = JSON.parseObject(data, Login.class);
		   username = l.getUsername();
		   password = l.getPassword();
	   }
       String body= request.getParameter("body");
       String value= request.getParameter("value");
       User loginBean =  service.getUserByLoginName(username);
		if(loginBean==null) return null;
       if(!SystemService.validatePassword(password,loginBean.getPassword())){
           return null;
       }
        
        return loginBean;
    }
}
