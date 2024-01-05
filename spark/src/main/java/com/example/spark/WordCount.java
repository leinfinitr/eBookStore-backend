package com.example.spark;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {
        String directoryPath = "D:\\course\\ApplicationSystemArchitecture\\eBookStore\\eBookStore-backend\\WordCountInput"; // 输入目录路径
        String keyword = args[0]; // 关键词
        String outputFilePath = "D:\\course\\ApplicationSystemArchitecture\\eBookStore\\eBookStore-backend\\WordCountOutput\\" + keyword + ".txt";

        SparkSession spark = SparkSession.builder().appName("Word Counter").getOrCreate();

        Dataset<String> logData = spark.read().textFile(directoryPath + "/*.txt").cache();

        long count = logData.filter((FilterFunction<String>) s -> s.contains(keyword)).count();
        System.out.println(keyword + ": " + count);

        // 将结果写入文件
        StringBuilder result = new StringBuilder();
        result.append(count).append("\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        spark.stop();
    }
}