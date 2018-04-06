package com.solr.util;

import org.apache.solr.client.solrj.SolrQuery;

import com.thinkgem.jeesite.common.utils.StringUtils;

public class SolrUtil {
	
	/**精确查询
	 * @param type 查询方式
	 * @param word 查询关键词
	 * @param start 
	 * @param rows
	 * @param place 
	 * @param province 
	 * @param channels 
	 * @return 
	 * 默认查询方式  seachType_selectByTag
	 */
	public static SolrQuery getExactQueryRule(int type, String word, String start, String rows, String city, String province, String channel) {
		SolrQuery solrQuery = new SolrQuery();
		//查询方式
		solrQuery.add("defType", "edismax");
		// 分页
		solrQuery.add("start", start);
		solrQuery.add("rows", rows);
		
		switch (type) {
		case Constants.seachType_selectByTag:
			solrQuery.add("q",  word );
			solrQuery.add("qf", "tags_ss");
			break;
		case Constants.seachType_selectByPhone:
			solrQuery.add("q",  word );
			solrQuery.add("qf", "id");
			break;
		case Constants.seachPhone:
			//查询条件
			solrQuery.add("q",  getQByTags(word) );
			solrQuery.add("qf", "tags_ss");
			
			//过滤
			if (StringUtils.isNotEmpty(city)) {
				solrQuery.add("fq", "city_s:"+city);
			}
			if (StringUtils.isNotEmpty(province)) {
				solrQuery.add("fq", "province_s:"+province);
			}
			if (StringUtils.isNotEmpty(province)) {
				solrQuery.add("fq", "channel_ss:"+channel);
			}
			
			break;
		default:
			solrQuery.add("qf", "tags_ss");
			break;
		}
		return solrQuery;
	}
	
	
	/**模糊查询
	 * @param type 查询方式
	 * @param word 查询关键词
	 * @param start 
	 * @param rows
	 * @return 
	 * 默认查询方式  seachType_selectByTag
	 */
	public static SolrQuery getFuzzyQueryRule(int type, String word, String start, String rows) {
		SolrQuery solrQuery = new SolrQuery();
		//查询方式
		solrQuery.add("defType", "edismax");
		solrQuery.add("q", "(*" + word + "* OR " + word + ")");
		// 分页
		solrQuery.add("start", start);
		solrQuery.add("rows", rows);
		
		solrQuery.add("sort", "last_date_s asc");
		
		switch (type) {
		case Constants.seachType_selectByTag:
			solrQuery.add("qf", "tags_ss");
			break;
		case Constants.seachType_selectByPhone:
			solrQuery.add("qf", "id");
			break;

		default:
			solrQuery.add("qf", "tags_ss");
			break;
		}
		return solrQuery;
	}
	
	
	
	private static String getQByTags(String tags) {
		String q="(";
		String[] strings=tags.split(",");
		for (int i = 0; i < strings.length; i++) {
			if (i<strings.length-1) {
				q=q+strings[i]+" AND ";
			}else {
				q=q+strings[i];
			}
		}
		q=q+")";
		return q;
	}
}
