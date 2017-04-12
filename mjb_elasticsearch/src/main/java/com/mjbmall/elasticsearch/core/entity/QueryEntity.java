package com.mjbmall.elasticsearch.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryEntity<T> {
	@JsonProperty("took")
	private int took;
	@JsonProperty("timed_out")
	private boolean timedOut;
	@JsonProperty("_shards")
	private ShareEntity shards;
	@JsonProperty("hits")
	private ResultEntity<T> hits;

	public int getTook() {
		return took;
	}

	public void setTook(int took) {
		this.took = took;
	}

	public ResultEntity<T> getHits() {
		return hits;
	}

	public void setHits(ResultEntity<T> hits) {
		this.hits = hits;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	public ShareEntity getShards() {
		return shards;
	}

	public void setShards(ShareEntity shards) {
		this.shards = shards;
	}
}
