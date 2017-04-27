package com.tweetView.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetView.dataFormat.KeyValPair;
import com.tweetView.dataFormat.KeyValueWrapper;

@Controller
public class DashboardController {
	
	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	
	@RequestMapping(value="/graph0",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage0(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_0_hashtagStat.json";
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_0_hashtagStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph1",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage1(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_0_retweetStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_0_retweetStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph2",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage2(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_1_hashtagStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_1_hashtagStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph3",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage3(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_1_retweetStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_1_retweetStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	
	
	
	@RequestMapping(value="/graph4",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage4(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_2_hashtagStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_2_hashtagStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph5",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage5(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_2_retweetStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_2_retweetStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	
	
	@RequestMapping(value="/graph6",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage6(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_3_hashtagStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_3_hashtagStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph7",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage7(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_3_retweetStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_3_retweetStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	
	@RequestMapping(value="/graph8",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage8(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_4_hashtagStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_4_hashtagStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph9",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage9(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topFiveTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Cluster_stats/cluster_4_retweetStat.json";
		String topFiveTweets1 = "D:/filezillaStuff/input/Cluster_stats/cluster_4_retweetStat.json";
		File input = new File(topFiveTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/graph10",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage10(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		//String topTenTweets1 = "/home/ubuntu/Project-Installs/Project4/apache-tomcat-7.0.73/data/Christmas_stats/hashtag_stats.json";
		String topTenTweets1 = "D:/filezillaStuff/input/Christmas_stats/hashtag_stats.json";
		File input = new File(topTenTweets1);
		List<String> inputTweets = FileUtils.readLines(input, StandardCharsets.UTF_8);
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 10;
		if(inputTweets.size()<size){
			size = inputTweets.size();
		}
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = (KeyValPair) mapper.readValue(inputTweets.get(i), KeyValPair.class);
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}
	
	@RequestMapping(value="/graph11",method = RequestMethod.GET,headers = {"Accept=text/json, application/json"})
	@ResponseBody 
	public  String showMessage11(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws IOException {
		System.out.println("in controller");
		/*mv.addObject("message", message);
		mv.addObject("name", name);*/
		int [] sizeList = new int[5];
		int sum = 0;
		for(int i=0;i<5;i++){
			String inputPath = "D:/filezillaStuff/input/Cluster_stats/cluster_"+i+".json";
			List<String> inp = FileUtils.readLines(new File(inputPath), StandardCharsets.UTF_8);
			sum+=inp.size();
			sizeList[i] = inp.size();
			sizeList[i]=sizeList[i]*100;
		}
		
		KeyValueWrapper keyValuewrapper = new KeyValueWrapper();
		List<KeyValPair> list = new ArrayList<KeyValPair>();
		ObjectMapper mapper = new ObjectMapper();
		int size = 5;
		List<String> topics = new ArrayList<String>();
		topics.add("Festival/Holiday");
		topics.add("Simple Promotion");
		topics.add("Aggresive Promotion");
		topics.add("Charity");
		topics.add("Gifts");
		for (int i = 0; i < size; i++) {
			KeyValPair keyPair = new KeyValPair();
			keyPair.setKey(topics.get(i));
			keyPair.setValue((int)((float)sizeList[i]/sum));
			list.add(keyPair);
		}
		keyValuewrapper.setJsonList(list);
		return keyValuewrapper.getAsJson(keyValuewrapper);
	}

}
