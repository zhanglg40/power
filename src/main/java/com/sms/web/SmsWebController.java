package com.sms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sms.entity.SmsTask;
import com.sms.service.SmsTaskService;
import com.solr.service.SolrService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/sms/web")
public class SmsWebController extends BaseController {

    @Autowired
    private SmsTaskService taskService;
    @Autowired
    private SolrService solrService;

    @ModelAttribute("smsTask")
    public SmsTask get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return taskService.get(id);
        } else {
            return new SmsTask();
        }
    }


    /**
     * @return
     */
    @RequestMapping("/taskList")
    public String testWeb1(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        if ("".equals(smsTask.getPlace()) || "全国".equals(smsTask.getPlace())) {
            smsTask.setPlace(null);
        }
        if ("".equals(smsTask.getId())) {
            smsTask.setId(null);
        }
        if ("".equals(smsTask.getStatus())) {
            smsTask.setStatus(null);
        }
        if ("".equals(smsTask.getKeyWord())) {
            smsTask.setKeyWord(null);
        }
        Page<SmsTask> page = taskService.findDataTest(new Page<SmsTask>(request, response), smsTask);
        model.addAttribute("page", page);
        return "modules/sms/taskList";
    }

    /**
     * @return
     */
    @RequestMapping("/SmsSearchForm")
    public String SmsSearchForm(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute(smsTask);
        return "modules/sms/searchForm";
    }

    /**
     * @return
     */
    @RequestMapping("/SmsSearch")
    public String SmsSearch(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        taskService.insertLog(smsTask);
        Long phoneNumber = (long) 0;
        if ("2".equals(smsTask.getPlaceType())) {
            phoneNumber = solrService.selectPhone(smsTask.getKeyWord(), "0", "1", smsTask.getPlace(), null, null).getPhoneNum();
        } else if ("1".equals(smsTask.getPlaceType())) {
            phoneNumber = solrService.selectPhone(smsTask.getKeyWord(), "0", "1", null, smsTask.getPlace(), null).getPhoneNum();
        } else {
            phoneNumber = solrService.selectPhone(smsTask.getKeyWord(), "0", "1", null, null, null).getPhoneNum();
        }
        smsTask.setTotalQuantity(phoneNumber );

        model.addAttribute(smsTask);
        return "modules/sms/searchResultForm";
    }

    /**
     * @return
     */
    @RequestMapping("/taskForm")
    public String taskForm(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute(smsTask);
        return "modules/sms/taskForm";
    }

    @RequestMapping("/saveTask")
    public String saveTask(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        taskService.save(smsTask);
        addMessage(redirectAttributes, "任务'" + smsTask.getKeyWord() + "'，保存成功");
        return "redirect:" + adminPath + "/sms/web/taskList?repage";
    }

    /**
     * @return
     */
    @RequestMapping("/changeTaskPQForm")
    public String changeTaskPQForm(SmsTask smsTask, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute(smsTask);
        return "modules/sms/addPlannedQuantityForm";
    }

}
