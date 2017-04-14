package com.mjbmall.elasticsearch.core.entity;

import java.io.Serializable;

/**
 * RistCient链接 Host实体类
 * @author Gerry_Pang
 */
public class HostEntity implements Serializable{
	
	private static final long serialVersionUID = 3494524362451384269L;
	private String ip;
	private String port;
	
	public HostEntity(){
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
