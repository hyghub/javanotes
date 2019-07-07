**一、zipkin 服务链路追踪**
1. 需要启动链路追踪服务器，下载 zipkin-server-2.10.1-exec.jar， 然后用如下命令启动：

    `java -jar zipkin-server-2.10.1-exec.jar --zipkin.collector.rabbitmq.addresses=localhost`

2. 挨个启动 eureka-server, 改造后的 product-data-service 和 product-view-service-feign 。 ( product-view-service-ribbon 后续不再使用，所以既没有被改造，也不用再启动了)

3. 访问一次 http://127.0.0.1:8012/products 通过 视图微服务去访问数据微服务，这样链路追踪服务器才知道有这事儿发生~

4. 然后打开链路追踪服务器 http://localhost:9411/zipkin/dependency/ 就可以看到视图微服务调用数据微服务的图形了。

**二、ConfigServer 配置服务器**

1.我们先在 git 里保存 version 信息， 然后通过 ConfigServer 去获取 version 信息， 接着不同的视图微服务实例再去 ConfigServer 里获取 version。

2.https://github.com/hyghub/springcloudConfig/blob/master/respo/product-view-service-feign-dev.properties

3.先启动 EurekaServerApplication， 再启动 ConfigServerApplication， 然后访问
  http://localhost:8030/version/dev

**三、BUS 消息总线**

1.	在视图微服务中(product-view-service-feign)导入以下两个包:
  
    `spring-boot-starter-actuator 用于访问路径：/actuator/bus-refresh`
  
    `spring-cloud-starter-bus-amqp 用于支持 rabbitmq`

2.