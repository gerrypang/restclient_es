package com.mjbmall.elasticsearch.repositories;

import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Store;

/**
 * StoreRepository
 * @author Gerry_Pang
 */
@Repository
public class StoreRepository{
	
	@Autowired
	private TemplateRepository<Store> templateRepository;

	/**
	 * 分页，排序，查询
	 * @param query
	 * @param page
	 * @param sortFiled  collect:人气，credit:信誉，stars:星级
	 * @param sortOrder  asc:递增，desc:递减
	 * @return
	 */
	public Page<HitEntity<Store>> findPageByOneParam(String query, Pageable page ,String sortFiled, String sortOrder){
		String now = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
					+ "["
						+ "{\"match\" : {\"storeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, "	 // 店铺名称
						+ "{\"match\" : {\"officeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}},"	 // 机构名称
						+ "{\"match\" : {\"areaName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," 	 	 // 地区名称
						+ "{\"match\" : {\"typeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}},"	 	 // 店铺类型名称
						+ "{\"match\" : {\"address.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}},"		 // 店铺位置
						+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}},"	 	 // 店铺关键字
						+ "{\"match\" : {\"description.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}"	 // 描述
					+ "],"
				+ "\"minimum_should_match\": 2,"
				+ "\"filter\": {"
					+ "\"bool\": { "
						+ "\"must\": {\"term\":  {\"status\": \"30\" }},"       // 店铺状态，30-审核通过
						+ "\"must\": {\"term\":  {\"delFlag\": \"0\" }},"        // 删除标记，0-正常
						+ "\"must\": {\"range\": {\"availFrom\" : {\"lte\": \""+ now + "\" }}},"
						+ "\"must\": {\"range\": {\"availTo\" : {\"gte\": \""+ now + "\" }}}"
					+ "}"
				+ "}}},"
				+ "\"track_scores\": true,"
				+ "\"sort\" : ["
					+ "{\"" + sortFiled + "\" : {\"order\" : \"" + sortOrder + "\"}},"
					+ "{\"_score\" : {\"order\" : \"desc\"}}"
				+ "]";
		Page<HitEntity<Store>> entityPage = templateRepository.queryDSLByPage("sys", "store", queryDSL, page);
		return entityPage;
	}
	
	/**
	 * 默认分值，分页，查询
	 * @param query
	 * @param page
	 * @return
	 */
	public Page<HitEntity<Store>> findPageByOneParam(String query, Pageable page){
		String now = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"storeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, "	 // 店铺名称
					+ "{\"match\" : {\"officeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}},"	 // 机构名称
					+ "{\"match\" : {\"areaName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," 	 	 // 地区名称
					+ "{\"match\" : {\"typeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":5 }}},"	 	 // 店铺类型名称
					+ "{\"match\" : {\"address.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}},"		 // 店铺位置
					+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}},"	 	 // 店铺关键字
					+ "{\"match\" : {\"description\" : {\"query\":\"" + query + "\", \"boost\":1 }}}"	 // 描述
				+ "],"
				+ "\"minimum_should_match\": 1,"
				+ "\"filter\": {"
					+ "\"bool\": { "
						+ "\"must\": {\"term\":  {\"status\": \"30\" }},"       // 店铺状态，30-审核通过
						+ "\"must\": {\"term\":  {\"delFlag\": \"0\" }},"        // 删除标记，0-正常
						+ "\"must\": {\"range\": {\"availFrom\" : {\"lte\": \""+ now + "\" }}},"
						+ "\"must\": {\"range\": {\"availTo\" : {\"gte\": \""+ now + "\" }}}"
					+ "}"
				+ "}}}";
		Page<HitEntity<Store>> entityPage = templateRepository.queryDSLByPage("sys", "store", queryDSL, page);
		return entityPage;
	}
	
	/**
	 * 默认分值，查询，不分页
	 * @param query
	 * @param page
	 * @return
	 */
	public List<HitEntity<Store>> findPageByOneParam(String query){
		String now = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
		String queryDSL = "{\"bool\" : "
				+ "{\"should\" : "
				+ "["
					+ "{\"match\" : {\"storeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":10 }}}, "	 // 店铺名称
					+ "{\"match\" : {\"officeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":1 }}},"	 // 机构名称
					+ "{\"match\" : {\"areaName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}}," 	 	 // 地区名称
					+ "{\"match\" : {\"typeName.pinyin\" : {\"query\":\"" + query + "\", \"boost\":5 }}},"	 	 // 店铺类型名称
					+ "{\"match\" : {\"address.pinyin\" : {\"query\":\"" + query + "\", \"boost\":3 }}},"		 // 店铺位置
					+ "{\"match\" : {\"keywords.pinyin\" : {\"query\":\"" + query + "\", \"boost\":8 }}},"	 	 // 店铺关键字
					+ "{\"match\" : {\"description\" : {\"query\":\"" + query + "\", \"boost\":1 }}}"	 // 描述
				+ "],"
				+ "\"minimum_should_match\": 1,"
				+ "\"filter\": {"
					+ "\"bool\": { "
						+ "\"must\": {\"term\":  {\"status\": \"30\" }},"       // 店铺状态，30-审核通过
						+ "\"must\": {\"term\":  {\"delFlag\": \"0\" }},"        // 删除标记，0-正常
						+ "\"must\": {\"range\": {\"availFrom\" : {\"lte\": \""+ now + "\" }}},"
						+ "\"must\": {\"range\": {\"availTo\" : {\"gte\": \""+ now + "\" }}}"
					+ "}"
				+ "}}}";
		List<HitEntity<Store>> entityList = templateRepository.queryDSLByList("sys", "store", queryDSL);
		return entityList;
	}

	public TemplateRepository<Store> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<Store> templateRepository) {
		this.templateRepository = templateRepository;
	}
}
