package com.touch.theme;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * 生产者
 */
public class TestProducer {
	//服务地址，端口默认61616
	private static final  String url="tcp://127.0.0.1:61616";
	private static final  String topicName="queue_style";
	public static void main(String[] args) throws JMSException {
		ActiveMQUtil.checkServer();
		//1.创建ConnectionFactory,绑定地址
		ConnectionFactory factory = new ActiveMQConnectionFactory(url);
		//2.创建Connection
		Connection connection = factory.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		/*5.创建一个目标 (主题类型) 和队列模式的
		  TestProducer几乎一模一样，只有一个地方有区别：
		  Destination destination=session.createTopic(topicName);
		  这里是 createTopic 而 队列模式是 createQueue*/
		Destination destination=session.createTopic(topicName);
		//6.创建一个生产者
		MessageProducer producer=session.createProducer(destination);
		for (int i = 0; i <100 ; i++) {
			//7.创建消息
			TextMessage textMessage = session.createTextMessage("主题消息-"+i);
			//8.发送消息
			producer.send(textMessage);
			System.out.println(new Date()+":"+ "发送 = " +textMessage.getText());
		}
		//9.关闭连接
		connection.close();
	}
}
