package com.touch;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableTurbine
public class turbineApplication {
    public static void main(String[] args) {
        //http://localhost:8021/turbine.stream
        int port = 8021;
        if (!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }

        new SpringApplicationBuilder(turbineApplication.class).properties("server.port=" + port).run(args);
    }
}
