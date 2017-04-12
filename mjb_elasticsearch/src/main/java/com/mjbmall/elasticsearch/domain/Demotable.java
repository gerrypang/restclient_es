package com.mjbmall.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Demotable {
	@Id
	private String id;
    @Version
    private Long version;
	private String demo;
	private String type;
	private String delflag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
