package com.tweetPreProcess.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetPreProcess.dataformat.KeyValPair;
import com.tweetPreProcess.dataformat.Tweet;

@Service
public class TweetToHashMap {
	
	final static Logger logger = Logger.getLogger(TweetToHashMap.class);
	
	public void generateClusterStats() throws JsonParseException, JsonMappingException, IOException {

		File tweetsFile = new File("/home/andi/ir/input/Christmas_stats/tweet_stats.json");
		File hashtagFile = new File("/home/andi/ir/input/Christmas_stats/hashtag_stats.json");
		File tweetData = new File("/home/andi/ir/input/Christmas_stats/Christmas_output_stat.json");
		List<String> tweetFiles = FileUtils.readLines(tweetData, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		Map<String,List<Tweet>> tweetValueToTweetmap = new HashMap<String,List<Tweet>>();
		for (String tweetJson : tweetFiles) {
			Tweet tweet = mapper.readValue(tweetJson, Tweet.class);
			String input = tweet.getText_en();
			if (tweetValueToTweetmap.containsKey(input)) {
				List<Tweet> tweets = tweetValueToTweetmap.get(input);
				tweets.add(tweet);
			} else {
				List<Tweet> tweets = new ArrayList<Tweet>();
				tweets.add(tweet);
				tweetValueToTweetmap.put(input, tweets);
			}
		}
		
		List<String> retweetStuff = FileUtils.readLines(tweetsFile, StandardCharsets.UTF_8);
		Map<String,Integer> retweetedMap = new HashMap<String,Integer>();
		for(String retweeted : retweetStuff){
			mapper = new ObjectMapper();
			KeyValPair kp = mapper.readValue(retweeted, KeyValPair.class);
			retweetedMap.put(kp.getKey(), kp.getValue());
		}
		
		String clusterStats = "/home/andi/ir/input/Cluster_stats";
		FileUtils.deleteQuietly(new File(clusterStats));
		int count = 0;
		Collection<File> clusterFiles = FileUtils.listFiles(new File("/home/andi/irdata/output/clusters/"), null, false);
		for (File clusterFile : clusterFiles) {
			Map<String,Integer> hashtagCounter = new HashMap<String,Integer>();
			Map<String,Integer> retweetCounter = new HashMap<String,Integer>();
			File outputFile = new File(clusterStats + File.separator + clusterFile.getName() + ".json");
			File hashtagStats = new File(clusterStats + File.separator + clusterFile.getName() + "_hashtagStat.json");
			File retweetStats = new File(clusterStats + File.separator + clusterFile.getName() + "_retweetStat.json");
			FileUtils.write(outputFile, "", StandardCharsets.UTF_8, false);
			FileUtils.write(hashtagStats, "", StandardCharsets.UTF_8, false);
			FileUtils.write(retweetStats, "", StandardCharsets.UTF_8, false);
			List<String> clusterTweets = FileUtils.readLines(clusterFile, StandardCharsets.UTF_8);
			for (String clusterTweet : clusterTweets) {
				List<Tweet> tweets = tweetValueToTweetmap.get(clusterTweet);
				if(tweets==null){
					logger.error(clusterTweet);
					count++;
					continue;
					
				}
				Integer retweetCount = retweetedMap.get(clusterTweet);
				if(retweetCount!=null){
					retweetCounter.put(clusterTweet, retweetCount);
				}
				for (Tweet tweet : tweets) {
					List<String> hashtags = tweet.getHashtags();
					if (hashtags != null && hashtags.size() > 0) {
						for (String hashtag : hashtags) {
							hashtag = hashtag.toLowerCase();
							if (!hashtag.equals("christmas")) {
								if (!hashtagCounter.containsKey(hashtag)) {
									hashtagCounter.put(hashtag, 1);
								} else {
									hashtagCounter.put(hashtag, hashtagCounter.get(hashtag)+1);
								}
							}
						}
					}
					FileUtils.write(outputFile, tweet.getAsJson(tweet) + "\n", StandardCharsets.UTF_8, true);
				}
			}
			
			
			List<Map.Entry<String, Integer>> list =
	                new LinkedList<Map.Entry<String, Integer>>(hashtagCounter.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2) {
	                return -(o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        
	        
	        for(Map.Entry<String, Integer> entry : sortedMap.entrySet()){
	        	KeyValPair kp = new KeyValPair();
	        	kp.setKey(entry.getKey());
	        	kp.setValue(entry.getValue());
	        	FileUtils.write(hashtagStats, kp.getAsJson(kp)+"\n", StandardCharsets.UTF_8, true);
	        }
	        
	       list =
	                new LinkedList<Map.Entry<String, Integer>>(retweetCounter.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2) {
	                return -(o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        
	        
	        for(Map.Entry<String, Integer> entry : sortedMap.entrySet()){
	        	KeyValPair kp = new KeyValPair();
	        	kp.setKey(entry.getKey());
	        	kp.setValue(entry.getValue());
	        	FileUtils.write(retweetStats, kp.getAsJson(kp)+"\n", StandardCharsets.UTF_8, true);
	        }
	        
			
			
		}
		logger.error("Count : "+count);
		
	}
}
