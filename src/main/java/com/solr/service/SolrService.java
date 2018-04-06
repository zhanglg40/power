package com.solr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sms.entity.SmsTask;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solr.base.SolrBaseService;
import com.solr.rest_entity.RestPhoneData;
import com.solr.solr_entity.Phone;
import com.solr.util.Constants;
import com.solr.util.ListUtil;
import com.solr.util.SolrUtil;
import com.solr.util.UtilPhone;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
public class SolrService extends SolrBaseService {
	
	@Autowired
	private UserDao userDao;
	
	public Page<User> findDataTest(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		return page;
	}
	
	
	/**根据手机号和标签，获取phone对象
	 * @param tag 标签
	 * @param phoneNumber 手机号
	 * @return
	 */
	public Phone getPhone(String phoneNumber, String tag, String channel) {
		List<Phone> phones =exactQueryPhoneByPhone(phoneNumber);
		Phone phone = null;
		if (phones.size()>0) {
			phone=phones.get(0);
			//标签
			phone.setTags(ListUtil.listAddOnly(phone.getTags(), tag));
			//渠道
			phone.setChannels(ListUtil.listAddOnly(phone.getChannels(), channel));
			//最后操作时间戳
			phone.setLast_date(System.currentTimeMillis()+"");
		}else {
			List<String> tags=new ArrayList<String>();
			tags.add(tag);
			List<String> channels=new ArrayList<String>();
			channels.add(channel);
			//获取手机号归属地信息
			String[] place= UtilPhone.getPhoneAdderss(phoneNumber);
			
			phone = new Phone();
			phone.setPhone(phoneNumber);
			phone.setTags(tags);
			if (StringUtils.isNotEmpty(place[1])) {
				phone.setCity(place[1]);
			}else {
				phone.setCity("未知");
			}
			if (StringUtils.isNotEmpty(place[0])) {
				phone.setProvince(place[0]);
			}else {
				phone.setProvince("未知");
			}
			phone.setChannels(channels);
			phone.setTimes("0");
			phone.setLast_date(System.currentTimeMillis()+"");
		}
		return phone;
	}
	
	 /**保存手机号集合
	 * @param phones 手机号集合
	 * @param tag 标签
	 * @param channel 渠道
	 */
	public void savePhone(String phones, String tag, String channel ) {
		List<Phone> phoneList=new ArrayList<Phone>();
		
		String[] phoneNumbers= phones.split(",");
		
		int i = 1;
		for (String phoneNumber : phoneNumbers) {
			
			phoneList.add(getPhone(phoneNumber, tag, channel));
			
			if (i >= 500) {
				saveInSolr(phoneList);
				phoneList.clear();
			}else {
				i++;
			}
		}
		saveInSolr(phoneList);
	}
	
	/**向solr保存对象
	 * 
	 * @param beans 需要保存的对象集合
	 * @return
	 */
    public String saveInSolr(Collection<?> beans) {
    	try {
    		UpdateResponse response = null;
    		try {
    			response = getSolrServer().addBeans(beans);
    			getSolrServer().commit();
    		} catch (SolrServerException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return response.getStatus()+"";
    		
    	} catch (Exception e) {
    		return "500";    	
    	}
    }
    
    /*查询-----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/**查询手机号
	 * @param tags
	 * @param start
	 * @param rows
	 * @param channels 
	 * @param place 
	 * @return
	 */
	public RestPhoneData selectPhone(String tags, String start, String rows, String city, String province, String channels) {
		RestPhoneData restPhoneData=new RestPhoneData();
		if (tags == null) {
			return null;
		}
		//定义各类型 查询规则
		SolrQuery solrQuery = SolrUtil.getExactQueryRule(Constants.seachPhone, tags, start, rows, city, province, channels);
		System.out.println(solrQuery);
		QueryResponse queryResponse = null;
		try {
			queryResponse = getSolrServer().query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		restPhoneData.setPhones(queryResponse.getBeans(Phone.class));
		restPhoneData.setPhoneNum(queryResponse.getResults().getNumFound());
		return restPhoneData;	
	}
	
	/**根据手机号模糊查询
	 * @param phone
	 * @return
	 */
	public List<Phone> fuzzyQueryPhoneByPhone(String phone) {
		if (phone == null) {
			return null;
		}
		//定义各类型 查询规则
		SolrQuery solrQuery = SolrUtil.getFuzzyQueryRule(Constants.seachType_selectByPhone, phone, "0", "6");
		System.out.println(solrQuery);
		
		QueryResponse queryResponse = null;
		try {
			queryResponse = getSolrServer().query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return queryResponse.getBeans(Phone.class);	
	}
	
	/**根据手机号精确查询
	 * @param phone
	 * @return
	 */
	public List<Phone> exactQueryPhoneByPhone(String phone) {
		if (phone == null) {
			return null;
		}
		//定义各类型 查询规则
		SolrQuery solrQuery = SolrUtil.getExactQueryRule(Constants.seachType_selectByPhone, phone, "0", "6", null, null, null);
		System.out.println(solrQuery);
		
		QueryResponse queryResponse = null;
		try {
			queryResponse = getSolrServer().query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return queryResponse.getBeans(Phone.class);	
	}


}
