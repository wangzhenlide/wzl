package com.wzl.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 因为加上了spring-boot-starter-web依赖导致启动不起来，原因：web需要再配置其他的bean
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MicroOauth2GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroOauth2GatewayApplication.class, args);
    }

}
