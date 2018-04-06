package com.solr.base;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;

import com.thinkgem.jeesite.common.service.BaseService;

public class SolrBaseService extends BaseService{
	@Value("${solr.address}")
	private String solrUrl;
	private SolrServer solrServer = null;
	
	
	/**
	 * 初始化
	 */
	public void init() {
		if (solrServer==null) {
			solrServer = new HttpSolrServer(solrUrl);
		}
    }
	
	public SolrServer getSolrServer() {
		init();
        return solrServer;
    }

}
