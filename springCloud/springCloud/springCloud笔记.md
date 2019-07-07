springCloud笔记
**一、zipkin 服务链路追踪**
1. 需要启动链路追踪服务器，这个启动办法是下载右上角的 zipkin-server-2.10.1-exec.jar， 然后用如下命令启动：

    java -jar zipkin-server-2.10.1-exec.jar

2. 挨个启动 eureka-server, 改造后的 product-data-service 和 product-view-service-feign 。 ( product-view-service-ribbon 后续不再使用，所以既没有被改造，也不用再启动了)
3. 访问一次 http://127.0.0.1:8012/products 通过 视图微服务去访问数据微服务，这样链路追踪服务器才知道有这事儿发生~
4. 然后打开链路追踪服务器 http://localhost:9411/zipkin/dependency/ 就可以看到视图微服务调用数据微服务的图形了。





