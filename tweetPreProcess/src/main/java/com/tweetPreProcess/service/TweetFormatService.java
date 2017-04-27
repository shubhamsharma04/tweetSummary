package com.tweetPreProcess.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetPreProcess.dataformat.Entities;
import com.tweetPreProcess.dataformat.Hashtags;
import com.tweetPreProcess.dataformat.Tweet;
import com.tweetPreProcess.dataformat.UserMentions;
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
	
	final static Logger logger = Logger.getLogger(TweetFormatService.class);

	public void formatTweets() throws IOException {
		File inputdir = new File(tweetInputLocation);
		File outputDir = new File(formattedTweetOutputLocation);
		File repo = new File(tweetRepo);
		FileUtils.deleteQuietly(inputdir);
		FileUtils.deleteQuietly(outputDir);
		FileUtils.forceMkdir(inputdir);
		FileUtils.forceMkdir(outputDir);
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

		Collection<File> allFiles = FileUtils.listFiles(new File(tweetRepo), null, false);
		for (File tweetFile : allFiles) {
			File outputFile = new File(formattedTweetOutputLocation +File.separator+ tweetFile.getName());
			FileUtils.write(outputFile, "", StandardCharsets.UTF_8, false);
			List<String> allTweets = FileUtils.readLines(tweetFile, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			for (String tweetedString : allTweets) {
				Tweet tweet = mapper.readValue(tweetedString, Tweet.class);
				String input = tweet.getText();
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
				Entities tweetEntity = tweet.getEntities();
				/*List<String> urls = new ArrayList<String>();
				List<Urls> urlObjects = tweetEntity.getUrls();
				if (urlObjects != null && urlObjects.size() > 0){
					for(Urls urlObject : urlObjects){
						urls.add(urlObject.getUrl());
					}
				}
				
				for (String url : urls) {
					input = input.replaceAll(url, "");
				}*/
				
				List<String> hashTags = new ArrayList<String>();
				List<Hashtags> hashTagsObjects = tweetEntity.getHashTags();
				if (hashTagsObjects != null && hashTagsObjects.size() > 0){
					for(Hashtags hashtagObject : hashTagsObjects){
						hashTags.add(hashtagObject.getText());
					}
				}
				
				for (String hashtag : hashTags) {
					input = input.replaceAll("#" + hashtag, hashtag);
				}
				
				
				List<String> userMentions = new ArrayList<String>();
				List<UserMentions> userMentionObjects = tweetEntity.getUserMentions();
				if (userMentionObjects != null && userMentionObjects.size() > 0){
					for(UserMentions userMention : userMentionObjects){
						userMentions.add(userMention.getUserMentions());
					}
				}
				// Remove all the mentions
				if (userMentions != null && userMentions.size() > 0) {
					for (String mention : userMentions) {
						input = input.replaceAll("@" + mention, "");
					}
				}
				
				String mentionRemoval = "(@)[^\\s]{1,}[\\s]{1}|[\\n]{1}";
				input = input.replaceAll(mentionRemoval, "");
				input = input.replaceAll(
						"((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)",
						"");
				tweet.setText_en(input);

				
				/*if (tweet.getTweet_lang().equals("en")) {
					tweet.setText_en(input);
				} else if (tweet.getTweet_lang().equals("es")) {
					tweet.setText_es(input);
				} else if (tweet.getTweet_lang().equals("tr")) {
					tweet.setText_tr(input);
				} else if (tweet.getTweet_lang().equals("ko")) {
					tweet.setText_ko(input);
				}*/
				
				if (!StringUtils.isBlank(tweet.getText_en())) {
					FileUtils.write(outputFile, tweet.getText_en() + "\n", StandardCharsets.UTF_8, true);
				}
			}
			
			// Double check the presence of sum empty string
			
		}
	}

}
