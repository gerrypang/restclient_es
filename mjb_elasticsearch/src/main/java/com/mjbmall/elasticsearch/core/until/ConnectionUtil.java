package com.mjbmall.elasticsearch.core.until;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjbmall.elasticsearch.controller.HomeController;
import com.mjbmall.elasticsearch.core.entity.QueryEntity;
import com.mjbmall.elasticsearch.domain.Book;

public class ConnectionUtil {
	private RestClient restClient;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String host = "192.168.3.14";
	
	public RestClient connect(){
		HttpHost httpHost = new HttpHost(host, 9200, "http");
		
		RestClient restClient = RestClient.builder(httpHost)
		        .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
		            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
		                return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
		            }
		        })
		        .setMaxRetryTimeoutMillis(60000)
		        .build();
		return restClient;
	}
	
	public RestClient connect2(){
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password"));
		HttpHost httpHost = new HttpHost(host, 9200, "http");
		
		RestClient restClient = RestClient.builder(httpHost)
		        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
		            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
		                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		            }
		        }).build();
		return restClient;
	}
	
	public void disconnet(RestClient restClient){
		try {
			if(restClient!=null){
					restClient.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void demo1(RestClient restClient){
		try {
			Response response = restClient.performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void demo2(RestClient restClient){
		try {
			//index a document
			HttpEntity entity = new NStringEntity(
			        "{\n" +
			        "    \"user\" : \"kimchy\",\n" +
			        "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
			        "    \"message\" : \"trying out Elasticsearch\"\n" +
			        "}", ContentType.APPLICATION_JSON);

			Response indexResponse = restClient.performRequest("PUT", "/twitter/tweet/1", 
					Collections.<String, String>singletonMap("pretty", "true"), entity);
			System.out.println(EntityUtils.toString(indexResponse.getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void demo3(RestClient restClient){
		try {
			//index a document
			HttpEntity entity = new NStringEntity(
					"{ \"query\":"
					+ "{\"bool\" : "
							+ "{\"should\" : "
								+ "["
									+ "{\"match\" : {\"name\" : \"国\"}}, "	 //商铺类别名
									+ "{\"match\" : {\"message\" : \"国\"}}" 				 // 商品名称  
								+ "]"
						+ "}}}", ContentType.APPLICATION_JSON);

			Response indexResponse = restClient.performRequest("GET", "/sys/book/_search", 
					Collections.<String, String>singletonMap("pretty", "true"), entity);
			String queryResult = EntityUtils.toString(indexResponse.getEntity());
			ObjectMapper mapper = new ObjectMapper();
			QueryEntity<Book> query = mapper.readValue(queryResult, new TypeReference<QueryEntity<Book>>() { });
			System.out.println(queryResult);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnectionUtil conn = new ConnectionUtil();
		//conn.demo1(conn.connect());
		//conn.demo2(conn.connect());
		conn.demo3(conn.connect());
		
		// elastic search 返回结果
		//SearchResponse 
		
	}
}
