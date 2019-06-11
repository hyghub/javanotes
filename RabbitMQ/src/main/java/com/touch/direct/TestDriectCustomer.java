package com.touch.direct;

import cn.hutool.core.util.RandomUtil;
import com.rabbitmq.client.*;
import com.touch.utils.RabbitMQUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class TestDriectCustomer {
    private final static String QUEUE_NAME = "direct_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //为当前消费者取随机名
        final String name = "consumer-"+RandomUtil.randomString(5);
        RabbitMQUtil.checkServer();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);
        System.out.println(name +" 等待接受消息");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer=new DefaultConsumer(channel){
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                      byte[] body) throws UnsupportedEncodingException {
               String message = new String (body,"UTF-8");
               System.out.println(name + " 接收到消息 '" + message + "'");
           }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
