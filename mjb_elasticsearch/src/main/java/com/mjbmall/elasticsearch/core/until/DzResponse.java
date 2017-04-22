package com.mjbmall.elasticsearch.core.until;

import java.io.Serializable;

/**
 * 接口响应包装
 */
public class DzResponse implements Serializable {
	private static final long serialVersionUID = 6394340375583494799L;
	/**
	 * 业务处理成功
	 */
	public static final int RESPONSE_CODE_SUCCESS = 0;
	/**
	 * 认证失败,没有token信息或token无效
	 */
	public static final int RESPONSE_CODE_AUTHEN = 1;
	/**
	 * 业务处理失败
	 */
	public static final int RESPONSE_CODE_FAILURE = 2;
	protected int state;
	protected Object data;
	protected String message;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DzResponse() {

	}

	/**
	 * 
	 * @param code 响应代码
	 * @param data 业务数据
	 * @param message 响应消息
	 */
	public DzResponse(int state, Object data, String message) {
		super();
		this.state = state;
		this.data = data;
		this.message = message;
	}

	public DzResponse(Object data) {
		this.data = data;
	}

	/**
	 * 业务处理成功响应结果
	 * @param data
	 * @return
	 */
	public static DzResponse SUCCESS(Object data) {
		DzResponse response = new DzResponse(RESPONSE_CODE_SUCCESS, data, "访问成功");
		return response;
	}

	/**
	 * 业务处理失败响应结果
	 * @param message
	 * @return
	 */
	public static DzResponse FAILURE(String message) {
		DzResponse response = new DzResponse(RESPONSE_CODE_FAILURE, null, message);
		return response;
	}

	/**
	 * 认证失败响应结果
	 * @return
	 */
	public static DzResponse AUTHEN_FAILURE() {
		DzResponse response = new DzResponse(RESPONSE_CODE_AUTHEN, null, null);
		return response;
	}
}
