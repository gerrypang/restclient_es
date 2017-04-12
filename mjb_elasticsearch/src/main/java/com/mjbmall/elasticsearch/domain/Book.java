package com.mjbmall.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Book {
	@Id
	private String id;
    @Version
    private Long version;
	private Double price;
	private String name;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
