package com.wzl.cloud.userserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserSerivceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSerivceApplication.class, args);
    }

}
