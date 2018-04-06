/**
 * 
 */
package com.power.mail.dao;


import com.power.mail.entity.MailEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;



/**
 * 
 * @team IT Team
 * @author zhanglg
 * @version 1.0
 * @time  2016年10月28日
 */
@MyBatisDao
public interface MailDao  extends CrudDao<MailEntity> {
    MailEntity  getInfo();
}
