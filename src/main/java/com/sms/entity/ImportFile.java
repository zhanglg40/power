/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 上传文件实体
 */
public class ImportFile extends DataEntity<ImportFile> {
    private static final long serialVersionUID = 1L;

    private String id;

    private String importFile; // 保存文件路径

    private String status; // 状态

    public ImportFile() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportFile() {
        return importFile;
    }

    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }

}

