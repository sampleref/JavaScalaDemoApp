package org.demo.javacode.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.demo.javacode.producer.CreateTopicInKafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by kantipud on 28-02-2017.
 */
public class SimpleConsumer {

    private static Scanner in;
    private static boolean stop = false;

    public static void main(String[] args) {
        readMessageFromTopic(CreateTopicInKafka.TOPIC_NAME);
    }

    private static void readMessageFromTopic(String topicName) {
        KafkaConsumer<String, String> kafkaConsumer;
        kafkaConsumer = new KafkaConsumer<String, String>(getKafkaConsumerProperties());
        kafkaConsumer.subscribe(Arrays.asList(topicName));
        System.out.println("Reading from topic " + CreateTopicInKafka.TOPIC_NAME);
        try {
            while (true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(3);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        } catch (WakeupException ex) {
            System.out.println("Exception caught " + ex.getMessage());
        } finally {
            kafkaConsumer.close();
            System.out.println("After closing KafkaConsumer");
        }
    }

    private static Properties getKafkaConsumerProperties() {
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CreateTopicInKafka.KAKFA_HOSTS);
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer1");
        return configProperties;
    }

}
