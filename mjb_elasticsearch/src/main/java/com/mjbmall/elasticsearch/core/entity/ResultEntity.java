package com.mjbmall.elasticsearch.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultEntity<T> implements Serializable {
	private static final long serialVersionUID = -2507683410346218002L;
	@JsonProperty("total")
	private long total;
	@JsonProperty("max_score")
	private double maxScore;
	@JsonProperty("hits")
	private List<HitEntity<T>> hits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setHits(List<HitEntity<T>> hits) {
		this.hits = hits;
	}

	public List<HitEntity<T>> getHits() {
		return hits;
	}

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}
