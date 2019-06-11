package com.touch.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.touch.utils.RabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 启动生产者，生产100条信息。
 * 两个消费者分食 这100条信息 与ActiveMQ队列模式类似
 */
public class TestDriectProducer {
    public static  final  String QUEUE_NAME="direct_queue";
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
        for(int i=0;i<100;i++){
            String message = "direct 消息 " +i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("发送消息:"+message);
        }
        channel.close();
        connection.close();
    }
}
