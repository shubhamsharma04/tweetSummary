package com.sparkWordVec.driver;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sparkWordVec.service.FeatureVectorGenerationSevice;
import com.sparkWordVec.service.KMeansClusteringMLLib;
import com.sparkWordVec.service.TweetClassificationService;

public class AppDriver 
{
    public static void main( String[] args ) throws IOException
	{

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		FeatureVectorGenerationSevice featureVectorGenerationSevice = (FeatureVectorGenerationSevice) context
				.getBean("featureVectorGenerationSevice");
		featureVectorGenerationSevice.generateFeatureVectors();
	
		//KMeansClusteringMLLib kMeansClusteringMLLib = (KMeansClusteringMLLib) context.getBean("kMeansClusteringMLLib");
		//KMeansClusteringMLLib kMeansClusteringMLLib = new KMeansClusteringMLLib();
		//kMeansClusteringMLLib.doKmeansClusteringViaMLib();
    	//String master = "spark://opensam:7077";
		//SparkSession spark = SparkSession.builder().master(master).appName("GenerateWordToVecOffASingleDoc").getOrCreate();
    	//KMeansClustering kmeansClustering = new KMeansClustering();	
    	//kmeansClustering.doKmeansClusteringViaML(spark);
		KMeansClusteringMLLib clusteringMLLib = new KMeansClusteringMLLib();
		clusteringMLLib.doKmeansClusteringViaMLib();
		
		TweetClassificationService tweetClassificationService = (TweetClassificationService) context.getBean("tweetClassificationService");
		tweetClassificationService.classifyTweets();
	}
}
