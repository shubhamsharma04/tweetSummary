package com.sparkWordVec.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

//@Service
//@Qualifier("kMeansClusteringMLLib")
public class KMeansClusteringMLLib implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//@Value("${spark.appname}")
	private String sparkAppName = "theMightyShubham";

	//@Value("${spark.master}")
	private String sparkMasterMode = "local[2]";
	
	//@Value("${spark.word2VecOutput}")
	private String word2VecOutputLoc = "/home/andi/irdata/output/word2VecOutput";
	
	//@Value("${spark.cluster.centroids}")
	private String clusterCentroidsOutput = "/home/andi/irdata/output/classificationOutput";
	
	//@Value("${spark.clusterModel.output}")
	private String clusterModelDir = "/home/andi/irdata/output/KMeansModel";

	public void doKmeansClusteringViaMLib() throws IOException {
		SparkConf conf = new SparkConf().setAppName(sparkAppName).setMaster(sparkMasterMode);
		File classificationOutput = new File(clusterCentroidsOutput);
		FileUtils.write(classificationOutput, "", StandardCharsets.UTF_8, false);
		File input = new File(word2VecOutputLoc);
		List<String> unFormatInputVecs = FileUtils.readLines(input, StandardCharsets.UTF_8);
		FileUtils.write(input, "", StandardCharsets.UTF_8, false);
		for (String str : unFormatInputVecs) {
			str = str.replaceAll(",", " ");
			StringBuilder strBldr = new StringBuilder(str);
			strBldr.deleteCharAt(0);
			strBldr.deleteCharAt(strBldr.length() - 1);
			FileUtils.write(input, strBldr.toString() + "\n", StandardCharsets.UTF_8, true);
		}
		JavaSparkContext jsc = new JavaSparkContext(conf);
	    JavaRDD<String> data = jsc.textFile(word2VecOutputLoc);
	    JavaRDD<Vector> parsedData = data.map(
	      new Function<String, Vector>() {
	        public Vector call(String s) {
	          String[] sarray = s.split(" ");
	          double[] values = new double[sarray.length];
	          for (int i = 0; i < sarray.length; i++) {
	            values[i] = Double.parseDouble(sarray[i]);
	          }
	          return Vectors.dense(values);
	        }
	      }
	    );
	    parsedData.cache();

	    // Cluster the data into two classes using KMeans
	    int numClusters = 5;
	    int numIterations = 100;
	    KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters, numIterations);

	    System.out.println("Cluster centers:");
	    for (Vector center: clusters.clusterCenters()) {
	    	 FileUtils.write(classificationOutput, center+"\n", "UTF-8", true);
	    }
	    double cost = clusters.computeCost(parsedData.rdd());
	    System.out.println("Cost: " + cost);

	    // Evaluate clustering by computing Within Set Sum of Squared Errors
	    double WSSSE = clusters.computeCost(parsedData.rdd());
	    System.out.println("Within Set Sum of Squared Errors = " + WSSSE);
	   /* File clusterModel = new File(clusterModelDir);
	    if(clusterModel.exists()){
	    	FileUtils.deleteDirectory(clusterModel);
	    }
	    // Save and load model
	    clusters.save(jsc.sc(), clusterModelDir);
	    KMeansModel sameModel = KMeansModel.load(jsc.sc(),
	    		clusterModelDir);*/
	    // $example off$
	    jsc.stop();
	}
	
}
