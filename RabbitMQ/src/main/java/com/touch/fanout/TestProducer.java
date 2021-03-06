package com.touch.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.touch.utils.RabbitMQUtil;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 启动生产者，生产100条信息。
 * 两个消费者都能收到这100条信息。和ActiveMQ主题模式类似
 */
public class TestProducer {
	public final static String EXCHANGE_NAME="fanout_exchange";
	public static void main(String[] args) throws IOException, TimeoutException {
		RabbitMQUtil.checkServer();
		//创建连接工厂
		ConnectionFactory factory= new ConnectionFactory();
		//设置RabbitMQ相关信息
		factory.setHost("localhost");
		//创建一个新的连接
		Connection connection =factory.newConnection();
		//创建一个通道
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		for (int i = 0; i <100 ; i++) {
			String message="direct 消息 " +i;
			channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
			System.out.println(new Date()+" "+"发送消息：" + message);
		}
		//关闭通道和连接
		channel.close();
		connection.close();
	}
}
