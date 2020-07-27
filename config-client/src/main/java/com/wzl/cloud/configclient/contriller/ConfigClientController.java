package com.wzl.cloud.configclient.contriller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/24 15:59
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return info;
    }

}