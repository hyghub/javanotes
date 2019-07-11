package com.touch;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {
    //断路器监控启动类
    public static void main(String[] args) {
        //打开监控地址 http://localhost:8020/hystrix
        //视图微服务的短路信息http://localhost:8012/actuator/hystrix.stream
        int port = 8020;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(HystrixDashboardApplication.class)
                .properties("server.port=" + port).run(args);
    }

}
