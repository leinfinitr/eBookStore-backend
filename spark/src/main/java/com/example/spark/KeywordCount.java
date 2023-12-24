package com.example.spark;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class KeywordCount {
    public static void main(String[] args) {
        String directoryPath = "D:\\course\\ApplicationSystemArchitecture\\eBookStore\\eBookStore-backend\\WordCountInput"; // 指定目录路径
        String[] keywords = {"Java", "C++", "历史", "文化", "命运", "生活", "文学", "经典"}; // 关键词数组

        SparkSession spark = SparkSession.builder().appName("Keyword Counter").getOrCreate();

        Dataset<String> logData = spark.read().textFile(directoryPath + "/*.txt").cache();

        for (String keyword : keywords) {
            long count = logData.filter((FilterFunction<String>) s -> s.contains(keyword)).count();
            System.out.println(keyword + ": " + count);
        }

        spark.stop();
    }
}
