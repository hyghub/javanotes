package com.touch.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.touch.utils.RabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 四个路由："usa.news", "usa.weather", "europe.news", "europe.weather"
 * 上发布 "美国新闻", "美国天气", "欧洲新闻", "欧洲天气"
 * 于是就能在消费者端看到 不同的主题收到对应的消息了。
 * 例如一个消费者是美国主题：能收到"美国新闻", "美国天气";
 * 一个消费者是天气主题：能收到"美国天气","欧洲天气"
 */
public class TestProducer {
    public final static String EXCHANGE_NAME="topics_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMQUtil.checkServer();
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String[] routing_keys = new String[]{"usa.news","usa.weather","europe.news","europe.weather"};
        String[] messages = new String[] { "美国新闻", "美国天气", "欧洲新闻", "欧洲天气" };
        for (int d = 0; d < routing_keys.length; d++) {
            String routingkey=routing_keys[d];
            String message = messages[d];
            channel.basicPublish(EXCHANGE_NAME,routingkey,null,message.getBytes());
            System.out.printf("发送到消息的路由: %s,内容是: %s%n ",routingkey,message);
        }
        channel.close();
        connection.close();
    }
}




















