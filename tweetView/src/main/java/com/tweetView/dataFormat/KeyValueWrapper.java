package com.tweetView.dataFormat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KeyValueWrapper {
	@JsonProperty("jsonList")
	private List<KeyValPair> jsonList;

	public List<KeyValPair> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<KeyValPair> jsonList) {
		this.jsonList = jsonList;
	}
	
	@JsonIgnoreProperties
	public String getAsJson(KeyValueWrapper keyValueWrapper) {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(keyValueWrapper);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
