package com.wzl.cloud.userserivce.controller;

import com.alibaba.fastjson.JSON;
import com.wzl.cloud.userserivce.entity.User;
import com.wzl.cloud.userserivce.service.UserService;
import domain.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/6 17:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        userService.create(user);
        return new CommonResult("操作成功", 200);
    }

    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id) {
            User user = userService.getUser(id);
        LOGGER.info("根据id获取用户信息，用户名称为：{}", user.getUsername());
        return new CommonResult<>(user);
    }

    @GetMapping("/getUserByIds")
    public CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList = userService.getUserByIds(ids);
        LOGGER.info("根据ids获取用户信息，用户列表为：{}", userList);
        userList.forEach(a-> System.out.println(a.getId()+"ha"));
//        CommonResult a = new CommonResult<>("gouride",500, JSON.toJSONString(userList));
        CommonResult a = new CommonResult<>("gouride",500, userList);
        return a;
    }

    @GetMapping("/getByUsername")
    public CommonResult<User> getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        System.out.println(user.getUsername() + "==============================");
        return new CommonResult<>(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        userService.update(user);
        return new CommonResult("操作成功", 200);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        userService.delete(id);
        return new CommonResult("操作成功", 200);
    }
}