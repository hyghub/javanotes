**一、zipkin 服务链路追踪**

1. 需要启动链路追踪服务器，下载 zipkin-server-2.10.1-exec.jar， 然后用如下命令启动：

java -jar zipkin-server-2.10.1-exec.jar

2. 挨个启动 eureka-server, 改造后的 product-data-service 和 product-view-service-feign 。 ( product-view-service-ribbon 后续不再使用，所以既没有被改造，也不用再启动了)

3. 访问一次 http://127.0.0.1:8012/products 通过 视图微服务去访问数据微服务，这样链路追踪服务器才知道有这事儿发生~

4. 然后打开链路追踪服务器 http://localhost:9411/zipkin/dependency/ 就可以看到视图微服务调用数据微服务的图形了。

**二、ConfigServer 配置服务器**


1. 我们先在 git 里保存 version 信息， 然后通过 ConfigServer 去获取 version 信息， 接着不同的视图微服务实例再去 ConfigServer 里获取 version。

2. https://github.com/hyghub/springcloudConfig/blob/master/respo/product-view-service-feign-dev.properties

3. 先启动 EurekaServerApplication， 再启动 ConfigServerApplication， 然后访问
  http://localhost:8030/version/dev

**三、BUS 消息总线**

1.	在视图微服务中(product-view-service-feign)导入以下两个包:
  
    `spring-boot-starter-actuator 用于访问路径：/actuator/bus-refresh`
  
    `spring-cloud-starter-bus-amqp 用于支持 rabbitmq`
2. 首先挨个启动 EurekaServerApplication, ConfigServerApplication, ProductDataServiceApplication
   启动RabbitMQ用于消息分发，然后启动两个视图微服务 ProductViewServiceFeignApplication 端口号是 8012
3. 运行 FreshConfigUtil， 使用 post 的方式访问 http://localhost:8012/actuator/bus-refresh 地址，
FreshConfigUtil类就是为了可以使用 post 访问，因为它不支持 get 方式访问，直接把这个地址放在浏览器里，是会抛出 405错误的。
这个地址的作用就是让 config-server 去 git 获取最新的配置信息，并把此信息广播给集群里的两个视图微服务 
再次访问 http://127.0.0.1:8012/products可以看到版本号是修改之后的值了。
在Zipkin 里看不到视图服务的资料了。为了解决这个问题，在启动 Zipkin 的时候 带一个参数就好了：
java -jar zipkin-server-2.10.1-exec.jar --zipkin.collector.rabbitmq.addresses=localhost

**四、HYSTRIX 断路器** 

1. 增加 jar spring-cloud-starter-netflix-hystrix 以支持断路器
2. @FeignClient(value = "PRODUCT-DATA-SERVICE",fallback = ProductClientFeignHystrix.class)
3. feign.hystrix.enabled: true

**五、HYSTRIX 断路器监控**

1. 断路器监控启动类，添加@EnableHystrixDashboard 视图微服务增加@EnableCircuitBreaker把信息共享给监控中心
2. 首先挨个运行 EurekaServerApplication, ConfigServerApplication, ProductDataServiceApplication， ProductViewServiceFeignApplication，ProductServiceHystrixDashboardApplication
3. 运行视图微服务里的 AccessViewService 来周期性地访问 http://127.0.0.1:8012/products。 因为只有访问了，监控里才能看到数据。
3. 打开监控地址 http://localhost:8020/hystrix
4. 在最上面输入 http://localhost:8012/actuator/hystrix.stream
5. 聚合监控地址：http://localhost:8021/turbine.stream

**六、zuul 网关**

1. http://localhost:8040/api-data/products
2. http://localhost:8040/api-view/products
启动类，添加 @EnableZuulProxy








