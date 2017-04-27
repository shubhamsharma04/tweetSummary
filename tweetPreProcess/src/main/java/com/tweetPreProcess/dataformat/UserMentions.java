package com.tweetPreProcess.dataformat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMentions {

	@JsonProperty("screen_name")
	private String userMentions;

	public String getUserMentions() {
		return userMentions;
	}

	public void setUserMentions(String userMentions) {
		this.userMentions = userMentions;
	}
	
}
