package com.solr.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solr.rest_entity.RestPhoneData;
import com.solr.service.SolrService;
import com.solr.solr_entity.Phone;
import com.thinkgem.jeesite.common.web.BaseController;

@RestController
@RequestMapping(value = "${adminPath}/solr/rest")
public class SolrRestController extends BaseController{
	@Autowired
	private SolrService solrService;
	
	/**添加测试手机号
	 * @return
	 */
	@RequestMapping("/testAdd")
    public String addTestPhone() {
		for (int i = 0; i < 10; i++) {
			if (i==1) {
				solrService.savePhone("185"+i+"0513937", "帅", "测试添加");
			}else if (i>5) {
				solrService.savePhone("185"+i+"0513937", "传说", "测试添加");
				solrService.savePhone("185"+i+"0513937", "帅酷", "测试添加");
				if (i>8) {
					solrService.savePhone("185"+i+"0513937", "帅酷", "测试添加");
					solrService.savePhone("185"+i+"0513937", "帅", "测试添加");
				}
			}else {
				solrService.savePhone("185"+i+"0513937", "传说", "测试添加");
			}
		}
		
		return "完成";
    }
	
	/**添加手机号
	 * @param tag 标签
	 * @param phones 手机号s
	 * @return
	 */
	@RequestMapping("/addPhones")
    public String addPhone(
    		@RequestParam(value = "tag", defaultValue = "") String tag,
    		@RequestParam(value = "phones", defaultValue = "") String phones,
    		@RequestParam(value = "channel", defaultValue = "") String channel) {
		solrService.savePhone(phones, tag, channel);
		return "200";
    }
	
	/**根据查询手机号s
	 * @param tags
	 * @param start
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectPhone")
	public RestPhoneData selectPhone(
			@RequestParam(value = "tags", defaultValue = "") String tags,
			@RequestParam(value = "city", defaultValue = "") String city,
			@RequestParam(value = "province", defaultValue = "") String province,
			@RequestParam(value = "channels", defaultValue = "") String channels,
			@RequestParam(value = "start", defaultValue = "0") String start,
			@RequestParam(value = "rows", defaultValue = "20") String rows) {
		
		return solrService.selectPhone(tags, start, rows, city, province, channels);	
	}
	
	/**根据手机号模糊查询手机信息
	 * @param phone
	 * @return
	 */
	@RequestMapping("/fuzzyQueryByPhone")
	public List<Phone> fuzzyQueryByPhone(@RequestParam(value = "phone", defaultValue = "") String phone) {
		return solrService.fuzzyQueryPhoneByPhone(phone);	
	}
	
	/**根据手机号精确查询手机信息
	 * @param phone
	 * @return
	 */
	@RequestMapping("/exactQueryByPhone")
	public List<Phone> exactQueryByPhone(@RequestParam(value = "phone", defaultValue = "") String phone) {
		return solrService.exactQueryPhoneByPhone(phone);	
	}
	
}
