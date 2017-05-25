package org.demo.javacode.producer;

/*import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.Properties;*/

/**
 * Created by kantipud on 28-02-2017.
 */
public class CreateTopicInKafka {

    public static String TOPIC_NAME = "demo3Topic";
    //public static String ZOOKEEPER_HOSTS = "localhost:2111,localhost:2113";
    public static String ZOOKEEPER_HOSTS = "10.71.11.111:2181,10.71.11.113:2181";
    //public static String KAKFA_HOSTS = "localhost:9091,localhost:9092";
    public static String KAKFA_HOSTS = "10.71.11.155:9091,10.71.11.155:9092";

    public static void main(String[] args) {
        createTopic(TOPIC_NAME);
    }

    private static void createTopic(String topicName) {
        /*ZkClient zkClient = null;
        ZkUtils zkUtils = null;
        try {
            String zookeeperHosts = ZOOKEEPER_HOSTS; // If multiple zookeeper then -> String zookeeperHosts = "192.168.20.1:2181,192.168.20.2:2181";
            int sessionTimeOutInMs = 15 * 1000; // 15 secs
            int connectionTimeOutInMs = 10 * 1000; // 10 secs
            zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);
            int noOfPartitions = 2;
            int noOfReplication = 2;
            Properties topicConfiguration = new Properties();
            if (AdminUtils.topicExists(zkUtils, topicName)) {
                System.out.println("No need to create topic {} as it already exists " + topicName);
            } else {
                AdminUtils.createTopic(zkUtils, topicName, noOfPartitions, noOfReplication, topicConfiguration, new RackAwareMode.Disabled$());
                System.out.println("Created topic " + topicName + " Successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (zkClient != null) {
                zkClient.close();
            }
        }*/
    }
}
