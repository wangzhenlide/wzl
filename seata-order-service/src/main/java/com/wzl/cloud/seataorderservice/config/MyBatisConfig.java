package com.wzl.cloud.seataorderservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 17:25
 */
@Configuration
@MapperScan({"com.wzl.cloud.seataorderservice.mapper"})
public class MyBatisConfig {

}
