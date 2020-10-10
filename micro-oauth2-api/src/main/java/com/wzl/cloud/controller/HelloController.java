package com.wzl.cloud.controller;

import com.wzl.cloud.component.LoginUserHolder;
import com.wzl.cloud.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/21 15:04
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }


}
