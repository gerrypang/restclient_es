package com.mjbmall.elasticsearch.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * elasticsearch查询 search 返回查询结果 Hit 实体类
 * @author Gerry_Pang
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HitEntity<T> implements Serializable {

	private static final long serialVersionUID = -2367273772058002706L;
	@JsonProperty("_index")
	private String index;
	@JsonProperty("_type")
	private String type;
	@JsonProperty("_id")
	private String id;
	@JsonProperty("_score")
	private double score;
	@JsonProperty("_version")
	private int version;
	@JsonProperty("_source")
	private T source;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("_index", index).append("_type", type)
				.append("_id", id).append("_score", score)
				.append("_version", version)
				.append("_source", source).toString();
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public T getSource() {
		return source;
	}

	public void setSource(T source) {
		this.source = source;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}
