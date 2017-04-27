package com.tweetPreProcess.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetPreProcess.dataformat.KeyValPair;
import com.tweetPreProcess.dataformat.Tweet;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

@Service
public class TweetFormatService {

	@Value("${format.emoticons.file.location}")
	private String emoticonsFile;

	@Value("${format.kaomojis.file.location}")
	private String kaomojisFile;

	@Value("${format.tweet.repository.location}")
	private String tweetRepo;
	
	@Value("${format.tweet.input.location}")
	private String tweetInputLocation;

	@Value("${format.tweet.output.location}")
	private String formattedTweetOutputLocation;
	
	@Value("${format.tweet.stats.repository.location}")
	private String tweetInputLocationForStats;

	final static Logger logger = Logger.getLogger(TweetFormatService.class);

	public void formatTweets() throws IOException {
		//love me
		long time = System.currentTimeMillis();
		File inputdir = new File(tweetInputLocation);
		File outputDir = new File(formattedTweetOutputLocation);
		File repo = new File(tweetRepo);
		File inputDirStats = new File(tweetInputLocationForStats);
		FileUtils.deleteQuietly(inputdir);
		FileUtils.deleteQuietly(outputDir);
		FileUtils.deleteQuietly(inputDirStats);
		FileUtils.forceMkdir(inputdir);
		FileUtils.forceMkdir(outputDir);
		FileUtils.forceMkdir(inputDirStats);
		FileUtils.copyDirectory(repo, inputdir);
		List<Emoji> allEmojis = new ArrayList<Emoji>();
		allEmojis.addAll(EmojiManager.getAll());
		List<String> allEmojisAsUnicode = new ArrayList<String>();
		String [] stupidFuckingSymbols = new String[]{"&amp;"};
		for (Emoji em : allEmojis) {
			allEmojisAsUnicode.add(em.getUnicode());
		}

		List<String> allEmoticons = FileUtils.readLines(new File(emoticonsFile), StandardCharsets.UTF_8);
		List<String> allKaomojis = FileUtils.readLines(new File(kaomojisFile), StandardCharsets.UTF_8);
		FileUtils.write(new File(formattedTweetOutputLocation +File.separator+ "Christmas_output.json"), "", StandardCharsets.UTF_8, false);
		FileUtils.write(new File(tweetInputLocationForStats +File.separator+ "Christmas_output_stat.json"), "", StandardCharsets.UTF_8, false);
		Set<String> uniqueTweets = new HashSet<String>();
		Collection<File> allFiles = FileUtils.listFiles(new File(tweetRepo), null, false);
		String stopHashtag = "Christmas";
		Map<String,Integer> hashtagFreqMap = new HashMap<String,Integer>();
		Map<String,Integer> rtCounter = new HashMap<String,Integer>();
		for (File tweetFile : allFiles) {
			File outputFile = new File(formattedTweetOutputLocation +File.separator+ "Christmas_output.json");
			File outputFileForStats = new File(tweetInputLocationForStats +File.separator+ "Christmas_output_stat.json");
			//FileUtils.write(outputFile, "", StandardCharsets.UTF_8, false);
			List<String> allTweets = FileUtils.readLines(tweetFile, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			for (String tweetedString : allTweets) {
				Tweet tweet = mapper.readValue(tweetedString, Tweet.class);
				String input = tweet.getTweet_text();
				String modifiedInputString = EmojiParser.parseToUnicode(input);
				List<String> allEmoticonsEtcInTweet = new ArrayList<String>();
				for (String uniEm : allEmojisAsUnicode) {
					if (modifiedInputString.contains(uniEm)) {
						allEmoticonsEtcInTweet.add(uniEm);
						input = input.replaceAll(uniEm, "");
					}
				}
				input = EmojiParser.removeAllEmojis(input);

				for (String emoticon : allEmoticons) {
					if (input.contains(emoticon)) {

						if (emoticon.contains(")")) {
							input = input.replaceAll(emoticon.replaceAll("\\)", "\\\\)"), "");
						} else if (emoticon.contains("}")) {
							input = input.replaceAll(emoticon.replaceAll("\\}", "\\\\}"), "");
						}

						else if (emoticon.contains("(")) {
							input = input.replaceAll(emoticon.replaceAll("\\(", "\\\\("), "");
						} else if (emoticon.contains("{")) {
							input = input.replaceAll(emoticon.replaceAll("\\{", "\\\\{"), "");
						} else if (emoticon.contains("[")) {
							input = input.replaceAll(emoticon.replaceAll("\\[", "\\\\["), "");
						} else if (emoticon.contains("]")) {
							input = input.replaceAll(emoticon.replaceAll("\\]", "\\\\]"), "");
						}

						else {
							input = input.replaceAll(emoticon, "");
						}

						allEmoticonsEtcInTweet.add(emoticon.trim());
					}
				}

				for (String kaomoji : allKaomojis) {
					if (input.contains(kaomoji)) {
						if (kaomoji.contains("(") && kaomoji.contains("")) {
							String repKaomoji = kaomoji.replaceAll("\\)", "\\\\)");
							repKaomoji = repKaomoji.replaceAll("\\(", "\\\\(");
							input = input.replaceAll(repKaomoji, "");
						} else if (kaomoji.contains(")")) {
							try {
								input = input.replaceAll(kaomoji.replaceAll("\\)", "\\\\)"), "");
							} catch (Exception e) {
								logger.error(e);
								logger.debug(input);
								logger.debug("Kaomoji : " + kaomoji);
								logger.debug("Replaced Kaomoji : " + kaomoji.replaceAll("\\)", "\\\\)"));
							}
						} else if (kaomoji.contains("}")) {
							input = input.replaceAll(kaomoji.replaceAll("\\}", "\\\\}"), "");
						} else if (kaomoji.contains("(")) {
							input = input.replaceAll(kaomoji.replaceAll("\\(", "\\\\("), "");
						} else if (kaomoji.contains("{")) {
							input = input.replaceAll(kaomoji.replaceAll("\\{", "\\\\{"), "");
						} else if (kaomoji.contains("[")) {
							input = input.replaceAll(kaomoji.replaceAll("\\[", "\\\\["), "");
						} else if (kaomoji.contains("]")) {
							input = input.replaceAll(kaomoji.replaceAll("\\]", "\\\\]"), "");
						} else {
							input = input.replaceAll(kaomoji, "");
						}
						allEmoticonsEtcInTweet.add(kaomoji.trim());
					}
				}
				for(String stupidFuckingSymbol : stupidFuckingSymbols){
					input = StringUtils.remove(input, stupidFuckingSymbol);
				}
				// Remove all the #s from hashtags
				List<String> hashTags = tweet.getHashtags();
				if (hashTags != null && hashTags.size() > 0) {
					for (String hashtag : hashTags) {
						input = input.replaceAll("#" + hashtag, hashtag);
						hashtag = hashtag.toLowerCase();
						if(!hashtag.equals(stopHashtag)){
							if(!hashtagFreqMap.containsKey(hashtag)){
								hashtagFreqMap.put(hashtag, 1);
							} else {
								hashtagFreqMap.put(hashtag, hashtagFreqMap.get(hashtag) + 1);
							}
						}
					}
				}
				
				// Remove all the mentions
				List<String> userMentions = tweet.getMentions();
				if (userMentions != null && userMentions.size() > 0){
					for (String userMention : userMentions) {
						input = input.replaceAll("@" + userMention, "");
					}
				}
				
				if (userMentions != null && userMentions.size() > 0) {
					for (String mention : userMentions) {
						input = input.replaceAll("@" + mention, "");
					}
				}
				
				//String mentionRemoval = "(@)[^\\s]{1,}[\\s]{1}|[\\n]{1}";
				//input = input.replaceAll(mentionRemoval, "");
				input = input.replaceAll(
						"((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)",
						"");
				//tweet.setText_en(input);
				
				if(tweet.isRetweet()){
					input = input.replaceAll("^(RT : )", "");
				}
				input = input.replaceAll("\\n", "@@");
				tweet.setText_en(input);
				if (!uniqueTweets.contains(input)) {
					rtCounter.put(input, 1);
					uniqueTweets.add(input);
					if (!StringUtils.isBlank(input)) {
						FileUtils.write(outputFile, input + "\n", StandardCharsets.UTF_8, true);
					}
				} else{
					rtCounter.put(input, rtCounter.get(input)+1);
					
				}
				FileUtils.write(outputFileForStats, tweet.getAsJson(tweet)+"\n",StandardCharsets.UTF_8,true);
			}
		}
		File hashtagStats = new File(tweetInputLocationForStats+File.separator+"hashtag_stats.json");
		File tweetStats = new File(tweetInputLocationForStats+File.separator+"tweet_stats.json");
		FileUtils.write(hashtagStats, "", StandardCharsets.UTF_8, false);
		FileUtils.write(tweetStats, "",StandardCharsets.UTF_8,false);
		List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hashtagFreqMap.entrySet());

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
                new LinkedList<Map.Entry<String, Integer>>(rtCounter.entrySet());

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
        	FileUtils.write(tweetStats, kp.getAsJson(kp)+"\n", StandardCharsets.UTF_8, true);
        }
        
        
		logger.info("time taken : "+(System.currentTimeMillis() - time));
	}

}
