package com.sms.web;

import com.sms.entity.ImportFile;
import com.sms.service.ImportFileService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "${adminPath}/import")
public class ImportFilesController extends BaseController {
    @Autowired
    private ImportFileService importFileService;

    @RequestMapping("/importFile")
    public String importFile(ImportFile importFile, Model model) {
        importFile = new ImportFile();
        model.addAttribute("importFile", importFile);
        return "modules/sms/addFile";
    }

    @RequestMapping("/save")
    public String save(ImportFile importFile, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(importFile.getImportFile())) {
            addMessage(redirectAttributes, "请选择上传文件");
        } else {
            importFileService.save(importFile);
            model.addAttribute("importFile", importFile);
            addMessage(redirectAttributes, "保存文件成功");
        }
        return "redirect:" + adminPath + "/import/importFile";
    }
}
