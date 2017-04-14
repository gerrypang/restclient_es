package com.mjbmall.elasticsearch.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Book;

@Repository
public class BookRepository{
	
	@Autowired
	private TemplateRepository<Book> templateRepository;
	
	/**
	 * 分页查询，使用ik-pinyin分析器
	 * @param query
	 * @param page
	 * @return
	 */
	public Page<HitEntity<Book>> findPageByOneParam(String query, Pageable page){
		String queryDSL = "{\"bool\" : "
			+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"name.pinyin\" : \"" + query + "\"}}, "	 	 
					+ "{\"match\" : {\"message.pinyin\" : \"" + query + "\"}}"  	 // 商品名称  
				+ "],"
				+ "\"minimum_should_match\": 1"
		+ "}}";
		Page<HitEntity<Book>> entityPage = templateRepository.queryDSLByPage("sys", "book", queryDSL, page);
		return entityPage;
	}
	
	/**
	 * 列表查询，使用ik-pinyin分析器
	 * @param query
	 * @return
	 */
	public List<HitEntity<Book>> findPageByOneParam(String query){
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
					+ "["
					+ "{\"match\" : {\"name.pinyin\" : \"" + query + "\"}}, "	 	 //商铺类别名
					+ "{\"match\" : {\"message.pinyin\" : \"" + query + "\"}}"  	 // 商品名称  
					+ "],"
				+ "\"minimum_should_match\": 1"
				+ "}}";
		List<HitEntity<Book>> entityList = templateRepository.queryDSLByList("sys", "book", queryDSL);
		return entityList;
	}

	public TemplateRepository<Book> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<Book> templateRepository) {
		this.templateRepository = templateRepository;
	}
}
