package com.sms.service;

import com.sms.dao.ImportFileDao;
import com.sms.entity.ImportFile;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ImportFileService extends CrudService<ImportFileDao, ImportFile> {
    @Value("${userfiles.basedir}")
    private String preUrl;
    @Autowired
    private ImportFileDao importFileDao;

    @Transactional(readOnly = false)
    public void save(ImportFile importFile) {
        Date date = new Date();
        User user = UserUtils.getUser();
        if (StringUtils.isBlank(importFile.getId())) {
            if (StringUtils.isNotBlank(user.getId())) {
                importFile.setCreateDate(date);
                importFile.setCreateBy(user);
            }
            importFile.setImportFile(preUrl + importFile.getImportFile());
            importFile.setStatus("0");
            importFile.setDelFlag("0");
            importFileDao.insert(importFile);
        }
    }
}
