package com.mjbmall.elasticsearch.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Product;

/**
 * ProductRepository
 * @author Gerry_Pang
 */
@Repository
public class ProductRepository{
	
	@Autowired
	private TemplateRepository<Product> templateRepository;

	/**
	 * 分页，默认分值排序查询
	 * @param query
	 * @param page
	 * @return
	 */
	public Page<HitEntity<Product>> findPageByOneParam(String query, Pageable page){
		String queryDSL = "{\"bool\" : {"
				+ "\"should\" : "
					+ "["
						+ "{\"match\" : {\"mjbProductName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, " // 商品名称
						+ "{\"match\" : {\"mjbStoreName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商铺名称
						+ "{\"match\" : {\"mjbCatalogName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 商铺类别名称
						+ "{\"match\" : {\"mjbBrandName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 所属品牌名称
						+ "{\"match\" : {\"introduce.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商品简介
						+ "{\"match\" : {\"searchKey.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 搜索关键词
						+ "{\"match\" : {\"title.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 页面标题
						+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 页面关键词
						+ "{\"match\" : {\"description.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}}" // 页面描述
					+ "],"
				+ "\"minimum_should_match\": 2,"
				+ "\"filter\": {"
					+ "\"bool\": { "
						+ "\"must_not\": { \"term\": { \"status\": \"30\" } },"       // 商品状态!=30已下架
						+ "\"must\": { \"term\": { \"delFlag\": \"0\" } }"            // 删除标记0 正常
					+ "}"
				+ "}}}";
		Page<HitEntity<Product>> entityPage = templateRepository.queryDSLByPage("sys", "product", queryDSL, page);
		return entityPage;
	}
	
	/**
	 * 分页，排序，查询
	 * @param query
	 * @param page
	 * @param sortFiled favorite：人气，newPrice：现价，sellCount 销量
	 * @param sortOrder asc:递增，desc:递减
	 * @return
	 */
	public Page<HitEntity<Product>> findPageByOneParam(String query, Pageable page, String sortFiled, String sortOrder){
		String queryDSL = "{\"bool\" : {"
				+ "\"should\" : "
					+ "["
						+ "{\"match\" : {\"mjbProductName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, " // 商品名称
						+ "{\"match\" : {\"mjbStoreName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商铺名称
						+ "{\"match\" : {\"mjbCatalogName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 商铺类别名称
						+ "{\"match\" : {\"mjbBrandName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 所属品牌名称
						+ "{\"match\" : {\"introduce\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商品简介
						+ "{\"match\" : {\"searchKey.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 搜索关键词
						+ "{\"match\" : {\"title.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 页面标题
						+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 页面关键词
						+ "{\"match\" : {\"description\" : {\"query\":\"" + query + "\", \"boost\":1 }}}" // 页面描述
					+ "],"
				+ "\"minimum_should_match\": 1,"
				+ "\"filter\": {"
					+ "\"bool\": { "
						+ "\"must_not\": { \"term\": { \"status\": \"30\" } },"       // 商品状态!=30已下架
						+ "\"must\": { \"term\": { \"delFlag\": \"0\" } }"            // 删除标记0 正常
					+ "}"
				+ "}}},"
				+ "\"track_scores\": true,"
				+ "\"sort\" : ["
				+ "{\"" + sortFiled + "\" : {\"order\" : \"" + sortOrder + "\"}},"
					+ "{\"_score\" : {\"order\" : \"desc\"}}"
				+ "]";
		Page<HitEntity<Product>> entityPage = templateRepository.queryDSLByPage("sys", "product", queryDSL, page);
		return entityPage;
	}
	
	/**
	 * 查询
	 * @param query
	 * @return
	 */
	public List<HitEntity<Product>> findPageByOneParam(String query){
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"mjbProductName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, " // 商品名称
					+ "{\"match\" : {\"mjbStoreName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商铺名称
					+ "{\"match\" : {\"mjbCatalogName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 商铺类别名称
					+ "{\"match\" : {\"mjbBrandName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 所属品牌名称
					+ "{\"match\" : {\"introduce\" : {\"query\":\"" + query + "\", \"boost\":1 }}}," // 商品简介
					+ "{\"match\" : {\"searchKey.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 搜索关键词
					+ "{\"match\" : {\"title.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," // 页面标题
					+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}}," // 页面关键词
					+ "{\"match\" : {\"description\" : {\"query\":\"" + query + "\", \"boost\":1 }}}" // 页面描述
				+ "],"
				+ "\"minimum_should_match\": 1"
				+ ",\"filter\": {"
					+ "\"bool\": { "
						+ "\"must_not\": [ \"term\": { \"status\": \"30\" } ],"       // 商品状态!=30已下架
						+ "\"must\": [ \"term\": { \"delFlag\": \"0\" } ]"            // 删除标记0 正常
					+ "}"
				+ "}}}";
		List<HitEntity<Product>> entityList = templateRepository.queryDSLByList("sys", "product", queryDSL);
		return entityList;
	}

	public TemplateRepository<Product> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<Product> templateRepository) {
		this.templateRepository = templateRepository;
	}
}
