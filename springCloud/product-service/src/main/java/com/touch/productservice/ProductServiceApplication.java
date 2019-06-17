package com.touch.productservice;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        int port=8080;
        if(!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用了，无法启动%n",port);
        }
        //SpringApplication.run(ProductServiceApplication.class, args);
        new SpringApplicationBuilder(ProductServiceApplication.class).properties("serve.port="+port).run(args);
    }

}
