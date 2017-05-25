package org.demo.scalacode.streaming

import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.demo.javacode.producer.CreateTopicInKafka

class SparkStreamingListener{

  def startStreaming(): Unit ={
    printf("Hello World");
  }

}

object EmployeeCount extends App{

  def readEmployeeList(brokers: String, topics: String) = {

    val sparkConf = new SparkConf().setAppName("EmployeeReadApp").setMaster("local[2]").set("spark.executor.memory","1g");
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("bootstrap.servers" -> brokers)

    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    val lines = messages.map(_._2)
    val words = lines.flatMap(_.split("##"))
    words.print()

    ssc.start()
    ssc.awaitTermination()
  }

  readEmployeeList(CreateTopicInKafka.KAKFA_HOSTS,CreateTopicInKafka.TOPIC_NAME);
}