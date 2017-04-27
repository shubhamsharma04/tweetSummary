package com.tweetPreProcess.driver;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tweetPreProcess.service.TweetToHashMap;

public class App 
{
	final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args ) throws IOException
    {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		//TweetFormatService tweetFormatService = (TweetFormatService) context.getBean("tweetFormatService");
		//tweetFormatService.formatTweets();
		TweetToHashMap tweetToHashMap = (TweetToHashMap) context.getBean("tweetToHashMap");
		tweetToHashMap.generateClusterStats();
    }
}
