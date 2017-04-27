package com.tweetPreProcess.dataformat;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {
	
	@JsonProperty("hashtags")
	private List<Hashtags> hashTags;
	
	@JsonProperty("urls")
	private List<Urls> urls;
	
	@JsonProperty("user_mentions")
	private List<UserMentions> userMentions;

	public List<Hashtags> getHashTags() {
		return hashTags;
	}

	public void setHashTags(List<Hashtags> hashTags) {
		this.hashTags = hashTags;
	}

	public List<Urls> getUrls() {
		return urls;
	}

	public void setUrls(List<Urls> urls) {
		this.urls = urls;
	}

	public List<UserMentions> getUserMentions() {
		return userMentions;
	}

	public void setUserMentions(List<UserMentions> userMentions) {
		//if(userMentions!=null){
		this.userMentions = userMentions;
		/*} else {
			this.userMentions = new ArrayList<String>();
		}*/
	}
	
}
