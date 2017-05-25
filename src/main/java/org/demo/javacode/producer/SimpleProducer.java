package org.demo.javacode.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * Created by kantipud on 28-02-2017.
 */
public class SimpleProducer {

    public static void main(String[] args) throws InterruptedException {

        while (true){
            Thread.sleep(3000);
            double num = Math.random();
            num = (((num * num) / num) * 2) + 7 ;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(num) +"IDHGGTDT89 ## ");
            stringBuilder.append(new Date().getTime() + " ## ");
            stringBuilder.append("Name:Employee" + String.valueOf(num));
            postMessageToTopic(CreateTopicInKafka.TOPIC_NAME, stringBuilder.toString());
        }
    }

    private static void postMessageToTopic(String topicName, String message){
        Properties properties = getKafkaProducerProperties();

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        kafkaProducer.send(new ProducerRecord(topicName,message));
        kafkaProducer.flush();
        System.out.println("Posted to topic " + topicName + " message [" + message +"] Successfully");
    }

    private static Properties getKafkaProducerProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CreateTopicInKafka.KAKFA_HOSTS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
