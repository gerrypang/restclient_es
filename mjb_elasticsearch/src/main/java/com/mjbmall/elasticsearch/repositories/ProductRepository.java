package com.mjbmall.elasticsearch.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.MjbProduct;

@Repository
public class ProductRepository{
	
	@Autowired
	private TemplateRepository<MjbProduct> templateRepository;

	public Page<HitEntity<MjbProduct>> findPageByOneParam(String query, Pageable page){
		String queryDSL = "{\"bool\" : "
			+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"mjbProductName\" : \""+query+"\"}}, "	 //商铺类别名
					+ "{\"match\" : {\"mjbCatalogName\" : \""+query+"\"}}," 				 // 商品名称  
					+ "{\"match\" : {\"mjbBrandName\" : \""+query+"\"}},"			 // 商品简介 
					+ "{\"match\" : {\"mjbStoreName\" : \""+query+"\"}},"	     // 商品介绍
					+ "{\"match\" : {\"searchKey\" : \""+query+"\"}},"			 // 搜索关键词
					+ "{\"match\" : {\"keywords\" : \""+query+"\"}},"			 // 搜索关键词
					+ "{\"match\" : {\"title\" : \""+query+"\"}},"			 	// 搜索关键词
					+ "{\"match\" : {\"introduce\" : \""+query+"\"}}"			 // 品牌名称
				+ "],"
				+ "\"minimum_should_match\": 1"
				+ ",\"filter\": {"
					+ "\"term\": { \"delFlag\": \"0\" }}"
				+ "}}";
		Page<HitEntity<MjbProduct>> entityPage = templateRepository.queryDSLByPage("sys", "product", queryDSL, page);
		return entityPage;
	}
	
	public List<HitEntity<MjbProduct>> findPageByOneParam(String query){
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
					+ "["
					+ "{\"match\" : {\"mjbProductName\" : {\"query\":\""+query+"\"} }}, "	  //商铺类别名
					+ "{\"match\" : {\"mjbCatalogName\" : {\"query\":\""+query+"\"} }}," 	  // 商品名称  
					+ "{\"match\" : {\"mjbBrandName\" : {\"query\":\""+query+"\"} }},"		  // 商品简介 
					+ "{\"match\" : {\"mjbStoreName\" : {\"query\":\""+query+"\"} }},"	      // 商品介绍
					+ "{\"match\" : {\"searchKey\" : {\"query\":\""+query+"\",\"boost\": 3} }},"			  // 搜索关键词
					+ "{\"match\" : {\"keywords\" : {\"query\":\""+query+"\",\"boost\": 2} }},"			  // 搜索关键词
					+ "{\"match\" : {\"title\" : {\"query\":\""+query+"\"} }},"			 	  // 搜索关键词
					+ "{\"match\" : {\"introduce\" : {\"query\":\""+query+"\"} }}"			  // 品牌名称
					+ "],"
				+ "\"minimum_should_match\": 1"
				+ ",\"filter\": {"
					+ "\"term\": { \"delFlag\": \"0\" }}"
				+ "}}";
		List<HitEntity<MjbProduct>> entityList = templateRepository.queryDSLByList("sys", "product", queryDSL);
		return entityList;
	}

	public TemplateRepository<MjbProduct> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<MjbProduct> templateRepository) {
		this.templateRepository = templateRepository;
	}
}
