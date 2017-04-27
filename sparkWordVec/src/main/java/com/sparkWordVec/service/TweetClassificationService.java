package com.sparkWordVec.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TweetClassificationService {

	@Value("${format.tweet.location}")
	private String formattedTweetInputLocation;

	@Value("${format.vector.location}")
	private String vectorInputLocation;

	@Value("${format.labels.location}")
	private String classificationLabelsLocation;

	@Value("${format.clustered.location}")
	private String clusteredTweetLocation;

	@Value("${format.vector.dimensions}")
	private int vecDimensions;

	@Value("${format.cluster.num}")
	private int numOfClusters;

	public void classifyTweets() throws IOException {
		File tweetFile = new File(formattedTweetInputLocation);
		File vectorFile = new File(vectorInputLocation);
		File classificationLabels = new File(classificationLabelsLocation);
		Map<String, String> tweetToVectorMapping = new LinkedHashMap<String, String>();
		List<String> inputTweets = FileUtils.readLines(tweetFile, StandardCharsets.UTF_8);
		List<String> inputVectorsAsSpace = FileUtils.readLines(vectorFile, StandardCharsets.UTF_8);
		if (inputTweets.size() != inputVectorsAsSpace.size()) {
			System.err.println("Tweet File : "+tweetFile.getAbsolutePath()+" size : "+inputTweets.size());
			System.err.println("VectorFile File : "+vectorFile.getAbsolutePath()+" size : "+inputVectorsAsSpace.size());
			System.err.println("Some shit has happened. rectify");
			return;
		}
		List<String> inputVectors = new ArrayList<String>();
		int size = inputTweets.size();
		for (int i = 0; i < size; i++) {
			String[] arr = inputVectorsAsSpace.get(i).split(" ");
			StringBuilder str = new StringBuilder();
			for (String inp : arr) {
				str.append(inp);
				str.append(",");
			}
			str.deleteCharAt(str.length() - 1);
			inputVectors.add(str.toString());
		}
		for (int i = 0; i < size; i++) {
			tweetToVectorMapping.put(inputVectors.get(i), inputTweets.get(i));
		}

		double[][] clusterClasses = new double[numOfClusters][vecDimensions];
		List<String> classes = FileUtils.readLines(classificationLabels, StandardCharsets.UTF_8);
		for (int i = 0; i < classes.size(); i++) {
			String inp = classes.get(i).substring(1, classes.get(i).length()-2);
			System.out.println("inp : "+inp);
			String[] str = inp.split(",");
			for (int j = 0; j < vecDimensions; j++) {
				clusterClasses[i][j] = Double.valueOf(str[j]);
			}
		}
		FileUtils.deleteDirectory(new File(clusteredTweetLocation));
		new File(clusteredTweetLocation).mkdirs();
		String clusters = clusteredTweetLocation + "cluster_";
		for (Entry<String, String> entry : tweetToVectorMapping.entrySet()) {
			String[] vec = entry.getKey().split(",");
			double mse = Double.MAX_VALUE;
			int label = 0;
			for (int i = 0; i < numOfClusters; i++) {
				double currMse = 0d;
				for (int j = 0; j < vecDimensions; j++) {
					currMse += Math.pow(clusterClasses[i][j] - Double.valueOf(vec[j]), 2);
				}
				currMse = Math.sqrt(currMse);
				if (currMse < mse) {
					label = i;
					mse = currMse;
				}
			}
			FileUtils.write(new File(clusters + label), entry.getValue() + "\n", StandardCharsets.UTF_8, true);

		}
	}

}
