package com.tweetPseudoClassifier.driver;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tweetPseudoClassifier.service.TweetClassificationService;



public class App 
{
	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		TweetClassificationService tweetClassificationService = (TweetClassificationService) context
				.getBean("tweetClassificationService");
		tweetClassificationService.classifyTweets();
	}
}
