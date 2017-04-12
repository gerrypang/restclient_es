package com.mjbmall.elasticsearch.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Demotable;

@Repository
public class DemotableRepository{
	
	@Autowired
	private TemplateRepository<Demotable> templateRepository;
	
	public Page<HitEntity<Demotable>> findPageByOneParam(String query, Pageable page){
		String queryDSL = "{\"bool\" : "
			+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"demo\" : \"" + query + "\"}}, "	 	 //商铺类别名
					+ "{\"match\" : {\"type\" : \"" + query + "\"}}"  	 // 商品名称  
				+ "]"
				+ ",\"minimum_should_match\": 1"				
				+ ",\"filter\": {"
				+ "\"term\": { \"delflag\": \"0\" }}"
			+ "}}";
		Page<HitEntity<Demotable>> entityPage = templateRepository.queryDSLByPage("sys", "demotable", queryDSL, page);
		return entityPage;
	}

	public List<HitEntity<Demotable>> findPageByOneParam(String query){
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
					+ "["
					+ "{\"match\" : {\"demo\" : \"" + query + "\"}}, "	 	 //商铺类别名
					+ "{\"match\" : {\"type\" : \"" + query + "\"}}"  	 // 商品名称  
					+ "]"
					+ ",\"minimum_should_match\": 1"				
					+ ",\"filter\": {"
					+ "\"term\": { \"delflag\": \"0\" }}"
				+ "}}";
		List<HitEntity<Demotable>> entityList = templateRepository.queryDSLByList("sys", "demotable", queryDSL);
		return entityList;
	}

	public TemplateRepository<Demotable> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<Demotable> templateRepository) {
		this.templateRepository = templateRepository;
	}
}
