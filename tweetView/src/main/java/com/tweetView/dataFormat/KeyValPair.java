package com.tweetView.dataFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KeyValPair {

	@JsonProperty("key")
	private String key;

	@JsonProperty("value")
	private Integer value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@JsonIgnoreProperties
	public String getAsJson(KeyValPair keyValPair) {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(keyValPair);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
