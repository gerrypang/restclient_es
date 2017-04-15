package com.mjbmall.elasticsearch.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
	
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.core.entity.QueryEntity;

/**
 * Elasticsearch Rest API模板接入类
 * @author Gerry_Pang
 * @param <T>
 */
@Service
public class TemplateRepository<T> implements Repository<T>{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private ClusterNode clusterNode;
	/** 集群REST api 链接对象 */
	private RestClient restClient;
	
	public TemplateRepository(){
		super();
	}
	
	public TemplateRepository(ClusterNode clusterNode){
		this.clusterNode = clusterNode;
		restClient = clusterNode.getRestClient();
	}
	
	/**
	 * 对象销毁
	 */
	@PreDestroy
	public void destroy(){
		if (restClient != null) {
			try {
				restClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

    /**
     * 同步方式向ES集群写入数据
     * @param index
     * @param type
     * @param id
     * @param entity
     * @return boolean
     */
	public boolean putSync(String index, String type, String id, HttpEntity entity) {
        Response indexResponse = null;
        try {
        	// 拼接URL
        	String url = "/" + index + "/" + type + "/" + id;

        	// 配置请求参数
        	Map<String, String> paramMap = new HashMap<String, String>();
        	paramMap.put("pretty", "true");
        	
        	indexResponse = restClient.performRequest("PUT", url, paramMap, entity);
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        return (indexResponse != null);
	}
	
    /**
     * 异步的方式向ES写入数据
     * @param index
     * @param type
     * @param id
     * @param entity
     * @return boolean
     */
	public void putAsync(String index, String type, String id, HttpEntity entity) {
    	// 拼接URL
    	String url = "/" + index + "/" + type + "/" + id;

    	// 配置请求参数
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("pretty", "true");
		
    	restClient.performRequestAsync("PUT", url, paramMap, entity, new ResponseListener() { 
    		// 异步操作的监听器，在这里，注册listener，对操作成功或者失败进行后续的处理，比如在这里向前端反馈执行后的结果状态
    		public void onSuccess(Response response) {
    			logger.info(response.toString());
    		}

    		public void onFailure(Exception e) {
				logger.error("failure in async scenrio," + e.toString());
    		}
    	});
	}
	
	/**
	 * 查询获取list结果
	 * @param index 索引
	 * @param type 类型
	 * @param query DSL查询语句（不包括query）
	 */
	public List<HitEntity<T>> queryDSLByList(String index, String type, String query) {
		List<HitEntity<T>> returnList = null;
		StringBuffer queryDSL = new StringBuffer();
		try {
			// 拼接URL
			String url = "/" + index ;
			if(StringUtils.isNotBlank(type)){
				url += "/" + type+ "/_search";
			}else{
				url += "/_search";
			}
			if (query != null && query.length() > 0) {
				queryDSL.append("\"query\":").append(query).append("}");
				logger.debug("queryDSL："+queryDSL.toString());
			}
			
			// 配置请求参数
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("pretty", "true");
			
			
			HttpEntity entity = new NStringEntity(queryDSL.toString(), ContentType.APPLICATION_JSON);
			Response indexResponse = restClient.performRequest("GET", url, paramMap, entity);
			returnList = pareseJsonToLists(indexResponse.getEntity());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnList;
	}
	
	/**
	 * 查询获取list结果
	 * @param index 索引
	 * @param type 类型
	 * @param query 查询语句（不包括query）
	 */
	public List<HitEntity<T>> querySimpleByList(String index, String type, String query) {
		List<HitEntity<T>> returnList = null;
		String queryDSL = "";
		try {
			// 拼接URL
			String url = "/" + index ;
			if(StringUtils.isNotBlank(type)){
				url += "/" + type+ "/_search";
			}else{
				url += "/_search";
			}
			
			// 配置请求参数
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("pretty", "true");
			if (query!=null && query.length()>0) {
				paramMap.put("q", query);
				logger.debug("q："+query);
			}
			
			HttpEntity entity = new NStringEntity(queryDSL, ContentType.APPLICATION_JSON);
			Response indexResponse = restClient.performRequest("GET", url, paramMap, entity);
			returnList = pareseJsonToLists(indexResponse.getEntity());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnList;
	}

	/**
	 * 轻量查询获取page结果
	 * @param index 索引
	 * @param type 类型
	 * @param query DSL查询语句（不包括query、分页）
	 * @param pageable 分页
	 */
	public Page<HitEntity<T>> queryDSLByPage(String index, String type, String query, Pageable pageable) {
		Page<HitEntity<T>> returnPage = null;
		StringBuffer queryDSL = new StringBuffer();
		try {
			// 拼接URL
			String url = "/" + index ;
			if(StringUtils.isNotBlank(type)){
				url += "/" + type+ "/_search";
			}else{
				url += "/_search";
			}
			
			// 配置分页查询DSL
			int from = pageable.getPageNumber() * pageable.getPageSize();
			if (query != null && query.length() > 0) {
				queryDSL.append("{\"from\":").append(from)
						.append(", \"size\" :").append(pageable.getPageSize())
						.append(",\"query\":").append(query).append("}");
				logger.info("queryDSL："+queryDSL.toString());
			}
			
			// 配置请求参数
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("pretty", "true");
			
			HttpEntity entity = new NStringEntity(queryDSL.toString(), ContentType.APPLICATION_JSON);
			Response indexResponse = restClient.performRequest("GET", url, paramMap, entity);
			returnPage = pareseJsonToPages(indexResponse.getEntity(), pageable);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnPage;
	}
	
	/**
	 * 轻量查询获取page结果
	 * @param index 索引
	 * @param type 类型
	 * @param query DSL查询语句（不包括query、分页）
	 * @param pageable 分页
	 */
	public Page<HitEntity<T>> querySimpleByPage(String index, String type, String query, Pageable pageable) {
		Page<HitEntity<T>> returnPage = null;
		try {
			// 拼接URL
			String url = "/" + index ;
			if(StringUtils.isNotBlank(type)){
				url += "/" + type+ "/_search";
			}else{
				url += "/_search";
			}
			
			// 配置请求参数
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("pretty", "true");
			int from = pageable.getPageNumber() * pageable.getPageSize();
			if (query != null && query.length() > 0) {
				paramMap.put("from", from + "");
				paramMap.put("size", pageable.getPageSize() + "");
				paramMap.put("q", query);
				logger.debug("q："+query);
			}
			
			Response indexResponse = restClient.performRequest("GET", url, paramMap);
			returnPage = pareseJsonToPages(indexResponse.getEntity(), pageable);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnPage;
	}
	
	/**
	 * 转换entity为list
	 * @param entity
	 * @return
	 */
	public List<HitEntity<T>> pareseJsonToLists(HttpEntity entity){
		String queryResult;
		List<HitEntity<T>> returnList = new ArrayList<HitEntity<T>>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			queryResult = EntityUtils.toString(entity);
			QueryEntity<T> results = mapper.readValue(queryResult, new TypeReference<QueryEntity<T>>() { });
			if(results!=null &&  results.getHits().getHits().size()>0){
				List<HitEntity<T>> hits = results.getHits().getHits();
				returnList.addAll(hits);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnList;
	}
	
	/**
	 * 转换entity为Page
	 * @param entity
	 * @param pageable
	 * @return
	 */
	public Page<HitEntity<T>> pareseJsonToPages(HttpEntity entity, Pageable pageable){
		
		String queryResult;
		Page<HitEntity<T>> returnPage = null;
		List<HitEntity<T>> content = new ArrayList<HitEntity<T>>();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			queryResult = EntityUtils.toString(entity);
			QueryEntity<T> results = mapper.readValue(queryResult, new TypeReference<QueryEntity<T>>() { });
			if(results!=null &&  results.getHits().getHits().size()>0){
				List<HitEntity<T>> hits = results.getHits().getHits();
				content.addAll(hits);
			}
			long total = results.getHits().getTotal();
			returnPage = new PageImpl<HitEntity<T>>(content, pageable, total);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return returnPage;
	}

	public ClusterNode getClusterNode() {
		return clusterNode;
	}

	public void setClusterNode(ClusterNode clusterNode) {
		this.clusterNode = clusterNode;
	}
}