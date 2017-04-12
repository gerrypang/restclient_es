package com.mjbmall.elasticsearch.core;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 初始化链接类
 * @author Gerry_Pang
 */
@Service
public class ClusterNode {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	/** RestClient链接对象 */
	private RestClient restClient;	
	/** 字符串list配置链接节点，格式ip:port形式 */
	private List<String> hosts;
	/** httpHosts链接节点数组 */
	private HttpHost[] httpHosts;
	/** 连接超时，默认：5000ms */
	private final static int DEF_CONNECT_TIME_OUT = 5000;
	/** 接口超时，默认：6000ms */
	private final static int DEF_SOCKET_TIME_OUT = 60000;
	/** 连接最大重试时间，默认：60000ms */
	private final static int DEF_MAX_RETRY_TIME_OUT_MILLIS = 60000;
	/** 连接超时，默认：5000ms */
	private int connectTimeout = 0;
	/** 接口超时，默认：6000ms */
	private int socketTimeout = 0;
	/** 连接最大重试时间，默认：60000ms */
	private int maxRetryTimeoutMillis = 0;
	/** 登录用户名 */
	private String username = "";
	/** 登录用户密码 */
	private String password = "";
	
	public ClusterNode(){
		super();
	}
	
	public ClusterNode(List<String> hosts){
		this(hosts, DEF_CONNECT_TIME_OUT, DEF_SOCKET_TIME_OUT, DEF_MAX_RETRY_TIME_OUT_MILLIS);
	}

	public ClusterNode(List<String> hosts, int connectTimeout, int socketTimeout, int maxRetryTimeoutMillis){
		this.hosts = hosts;
		this.connectTimeout = connectTimeout;
		this.socketTimeout = socketTimeout;
		this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
		this.restClient = simpleConnect();
		logger.debug(restClient.toString());
	}

	public ClusterNode(List<String> hosts, String username, String password){
		this.hosts = hosts;
		this.username = username;
		this.password = password;
		this.restClient = secureConnect();
		logger.debug(restClient.toString());
	}
	
	/**
	 * 简单配置
	 * @return RestClient链接对象
	 */
	public RestClient simpleConnect(){
		restClient = RestClient.builder(setHttpHosts()).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
			public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
				return requestConfigBuilder.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout);
			}
		}).setMaxRetryTimeoutMillis(maxRetryTimeoutMillis).build();
		return restClient;
	}

	/**
	 * 安全配置
	 * @return RestClient链接对象
	 */
	public RestClient secureConnect(){
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, credentials);
		restClient = RestClient.builder(setHttpHosts()).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		    }
		}).build();
		return restClient;
	}

	/**
	 * 根据初始化hosts列表，配置httpHosts数组
	 * @return
	 */
	private HttpHost[] setHttpHosts(){
		if(hosts!=null && hosts.size()>0){
			int size = hosts.size();
			httpHosts = new HttpHost[size];
			for (int i = 0; i < hosts.size(); i++) {
				String tmp[] = hosts.get(i).split(":");
				HttpHost newHttpHost = new HttpHost(tmp[0], Integer.valueOf(tmp[1]).intValue(), "http");
				httpHosts[i] = newHttpHost;
			}
		}
		return httpHosts;
	}
	
	// ***** setter and getter methods ***** //
	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getMaxRetryTimeoutMillis() {
		return maxRetryTimeoutMillis;
	}

	public void setMaxRetryTimeoutMillis(int maxRetryTimeoutMillis) {
		this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

	public HttpHost[] getHttpHosts() {
		return httpHosts;
	}

	public void setHttpHosts(HttpHost[] httpHosts) {
		this.httpHosts = httpHosts;
	}
}
