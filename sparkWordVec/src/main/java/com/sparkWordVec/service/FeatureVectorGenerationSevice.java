package com.sparkWordVec.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.feature.Word2Vec;
import org.apache.spark.ml.feature.Word2VecModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeatureVectorGenerationSevice {

	@Value("${spark.appname}")
	private String sparkAppName;

	@Value("${spark.master}")
	private String sparkMasterMode;

	@Value("${spark.masterUrl}")
	private String masterUrl;

	@Value("${spark.word2VecOutput}")
	private String word2VecOutputLoc;

	@Value("${spark.tweetOutput}")
	private String tweetOutputLoc;

	@Value("${format.tweet.location}")
	private String inputFileLoc;

	@Value("${format.vector.dimensions}")
	private int vecDimensions;

	public void generateFeatureVectors() throws IOException {
		SparkConf conf = new SparkConf().setAppName(sparkAppName).setMaster(sparkMasterMode);
		SparkContext sc = new SparkContext(conf);
		SparkSession spark = SparkSession.builder().master(masterUrl).appName(sparkAppName).getOrCreate();
		File word2VecOutput = new File(word2VecOutputLoc);
		File tweetOutput = new File(tweetOutputLoc);
		File inputFile = new File(inputFileLoc);
		if (word2VecOutput.exists()) {
			FileUtils.forceDelete(word2VecOutput);
		}
		if (tweetOutput.exists()) {
			FileUtils.forceDelete(tweetOutput);
		}
		List<String> rawInput = FileUtils.readLines(inputFile, "UTF-8");
		List<String> input = new ArrayList<String>();
		int count =1;
		for (String str : rawInput) {
			if (!StringUtils.isBlank(str)) {
				input.add(str);
			} else {
				System.out.println("Line is empty : "+count);
			}
			count++;
		}
		FileUtils.write(inputFile, "",StandardCharsets.UTF_8,false);
		for (String str : input) {
			FileUtils.write(inputFile, str + "\n", StandardCharsets.UTF_8, true);
		}
		Row[] rows = new Row[input.size()];

		for (int i = 0; i < input.size(); i++) {
			rows[i] = RowFactory.create(Arrays.asList(input.get(i).split(" ")));
		}
		List<Row> data = Arrays.asList(rows);

		StructType schema = new StructType(new StructField[] {
				new StructField("text", new ArrayType(DataTypes.StringType, true), false, Metadata.empty()) });
		Dataset<Row> documentDF = spark.createDataFrame(data, schema);

		// Learn a mapping from words to Vectors.
		Word2Vec word2Vec = new Word2Vec().setInputCol("text").setOutputCol("result").setVectorSize(vecDimensions)
				.setMinCount(0);
		Word2VecModel model = word2Vec.fit(documentDF);
		Dataset<Row> result = model.transform(documentDF);
		for (Row r : result.select("result", "text").takeAsList(data.size())) {
			FileUtils.write(word2VecOutput, r.get(0) + "\n", "UTF-8", true);
			FileUtils.write(tweetOutput, r.get(1) + "\n", "UTF-8", true);
		}
		spark.stop();
	}

}
