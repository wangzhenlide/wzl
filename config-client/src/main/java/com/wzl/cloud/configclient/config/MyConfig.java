package com.wzl.cloud.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/24 17:40
 */
@Component
public class MyConfig {

    @Value("${config.info}")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
