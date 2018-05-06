/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.api;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.api.RequestBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2018年1月24日
 */
@Controller
@RequestMapping("/api/menu")
public class MenuApi {
    @ResponseBody
    @RequestMapping("/index_entry")
    public Object list(HttpServletRequest request, RequestBean requestBean) {
        List<JSONObject> list =new ArrayList<JSONObject>();
        JSONObject obj=new JSONObject();
        obj.put("id", "1");
        obj.put("path", "main");
        obj.put("image_url", "nav1.png");
        obj.put("title", "首页");
        list.add(obj);
        JSONObject obj1=new JSONObject();
        obj1.put("id", "2");
        obj1.put("path", "srhPD");
        obj1.put("image_url", "nav2.png");
        obj1.put("title", "电力数据");
        list.add(obj1);


        JSONObject obj4=new JSONObject();
        obj4.put("id", "5");
        obj4.put("path", "srhYear");
        obj4.put("image_url", "nav5.png");
        obj4.put("title", "年统计");
        list.add(obj4);
        JSONObject obj5=new JSONObject();
        obj5.put("id", "6");
        obj5.put("path", "srhMonth");
        obj5.put("image_url", "nav6.png");
        obj5.put("title", "月统计");
        list.add(obj5);
        JSONObject obj6=new JSONObject();
        obj6.put("id", "7");
        obj6.put("path", "srhDay");
        obj6.put("image_url", "nav7.png");
        obj6.put("title", "日统计");
        list.add(obj6);
        JSONObject obj7=new JSONObject();
        obj7.put("id", "8");
        obj7.put("path", "srhReal");
        obj7.put("image_url", "nav8.png");
        obj7.put("title", "电力实时");
        list.add(obj7);
		JSONObject obj3=new JSONObject();
		obj3.put("id", "4");
		obj3.put("path", "alertData");
		obj3.put("image_url", "nav4.png");
		obj3.put("title", "报警数据");
		list.add(obj3);
		JSONObject obj8=new JSONObject();
		obj8.put("id", "9");
		obj8.put("path", "advice");
		obj8.put("image_url", "nav9.png");
		obj8.put("title", "安检建议");
		list.add(obj8);
		JSONObject obj2=new JSONObject();
		obj2.put("id", "3");
		obj2.put("image_url", "nav3.png");
		obj2.put("path", "srhOH");
		obj2.put("title", "其他数据");
		list.add(obj2);
        return list;
    }
}
