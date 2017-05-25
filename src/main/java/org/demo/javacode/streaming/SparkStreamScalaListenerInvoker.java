package org.demo.javacode.streaming;

import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.demo.javacode.producer.CreateTopicInKafka;
import scala.Tuple2;

import java.util.*;

/**
 * Created by kantipud on 15-05-2017.
 */
public class SparkStreamScalaListenerInvoker {

    public static void main(String[] args) {
        // Prepare the spark configuration by setting application name and master node "local" i.e. embedded mode
        /*final SparkConf sparkConf = new SparkConf().setAppName("Word Count Demo").setMaster("local");

        // Create the Java Spark Context by passing spark config
        try (final JavaSparkContext jSC = new JavaSparkContext(sparkConf)) {
            // Create list of sentences
            final List<String> sentences = Arrays.asList(
                    "All Programming Tutorials",
                    "Getting Started With Apache Spark",
                    "Developing Java Applications In Apache Spark",
                    "Getting Started With RDDs In Apache Spark"
            );
            // Split the sentences into words, convert words to key, value with key as word and value 1,
            // and finally count the occurrences of a word
            final Map<Object, Long> wordsCount = jSC.parallelize(sentences)
                    .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                    .mapToPair((x) -> new Tuple2<Object, Object>(x, 1))
                    .countByKey();
            System.out.println("#################### OUTPUT #####################################");
            System.out.println(wordsCount);
            System.out.println("#################### OUTPUT #####################################");
        }*/
        startKafkaListener(CreateTopicInKafka.KAKFA_HOSTS,CreateTopicInKafka.TOPIC_NAME);
    }

    public static void startKafkaListener(String brokers, String topics){
        final SparkConf sparkConf = new SparkConf().setAppName("EmployeeReadApp").setMaster("local[2]").set("spark.executor.memory","1g");
        JavaStreamingContext streamingContext = new JavaStreamingContext (sparkConf, Duration.apply(2000));

        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", brokers);
        /*kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "EmployeeReadAppGroup1");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);*/

        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));

        JavaPairInputDStream<String, String> directKafkaStream =
                KafkaUtils.createDirectStream(streamingContext, String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);

        directKafkaStream.print();

        // Start the computation
        streamingContext.start();
        try {
            streamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
