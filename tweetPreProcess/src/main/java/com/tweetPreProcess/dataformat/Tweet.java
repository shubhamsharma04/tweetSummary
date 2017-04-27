package com.tweetPreProcess.dataformat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

	@JsonProperty("text")
	private String text;
	
	@JsonProperty("text_en")
	private String text_en;

	/*@JsonProperty("coordinates")
	private String tweet_loc;*/
	
	@JsonProperty("entities")
	private Entities entities;
	
	public Entities getEntities() {
		return entities;
	}

	public void setEntities(Entities entities) {
		this.entities = entities;
	}

	public String getText_en() {
		return text_en;
	}

	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	
	/*public String getTweet_loc() {
		return tweet_loc;
	}

	public void setTweet_loc(String tweet_loc) {
		this.tweet_loc = tweet_loc;
	}*/
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonIgnoreProperties
	public String getAsJson(Tweet tweet) {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(tweet);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
