package com.wzl.cloud.hystrixservice.controller;

import com.wzl.cloud.hystrixservice.entity.User;
import com.wzl.cloud.hystrixservice.service.UserServiceImpl;
import domain.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/9 13:50
 */
@RestController
public class UserHystrixController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/testFallback/{id}")

    public CommonResult testFallback(@PathVariable Long id) {
        return new CommonResult<>(userService.getUser(id));
    }

    @GetMapping("/testException/{id}")
    public CommonResult testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    @GetMapping("/testCache/{id}")
    public CommonResult testCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }

    @GetMapping("/testCollapser")
    public CommonResult testCollapser(Long id) throws ExecutionException, InterruptedException {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 6L);
//        map.put("username", "wzl");
//        map.put("password", "fsq");
//        User user = MyBeanUtils.mapToBean(map, User.class);

        Future<User> future1 = userService.getUserFuture(id);
        Future<User> future2 = userService.getUserFuture(2L);
//        Future<User> future3 = userService.getUserFuture(2L);
//        Future<User> future4 = userService.getUserFuture(2L);
//        Future<User> future5 = userService.getUserFuture(2L);
//        Future<User> future6 = userService.getUserFuture(2L);
        future1.get();
        future2.get();
//        future3.get();
//        future4.get();
//        future5.get();
//        future6.get();
//        Thread.sleep(200);
//        Future<User> future3 = userService.getUserFuture(3L);
//        future3.get();
        return new CommonResult("操作成功", 200);
    }

    @GetMapping("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable Long id) {
        System.out.println(id + "==============");
        return userService.getUserCommand(id);
    }
}
