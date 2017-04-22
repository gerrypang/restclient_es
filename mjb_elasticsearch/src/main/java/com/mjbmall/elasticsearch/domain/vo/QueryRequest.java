package com.mjbmall.elasticsearch.domain.vo;

/**
 * 接口调用查询对象
 * @author Gerry_Pang
 */
public class QueryRequest {
	/** 搜索关键字 */
	private String query;
	/** 当前查询页数 */
	private int page;
	/** 每页显示内容大小 */
	private int pageSize;
	/** 需要排序字段 */
	private String sortField;
	/** 排序方式 asc,desc */
	private String sortOrder;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
}
