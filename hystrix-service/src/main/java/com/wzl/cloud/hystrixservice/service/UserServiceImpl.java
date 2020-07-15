package com.wzl.cloud.hystrixservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.wzl.cloud.hystrixservice.entity.User;
import domain.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import utils.MyBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/6 17:49
 */
@Service
public class UserServiceImpl {
    //    复制代码
//    在UserService中添加调用方法与服务降级方法，方法上需要添加@HystrixCommand注解：

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @HystrixCommand(fallbackMethod = "getDefaultUser",
            commandKey = "getUser",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserTheadPool")
    public CommonResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    public CommonResult getDefaultUser(@PathVariable Long id) {
//        User defaultUser = new User(-1L, "defaultUser", "123456");
//        return new CommonResult<>(defaultUser);
        return new CommonResult<>("服务降级", 200);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser2", ignoreExceptions = {NullPointerException.class})
    public CommonResult getUserException(Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    public CommonResult getDefaultUser2(@PathVariable Long id, Throwable e) {
        logger.error("getDefaultUser2 id:{},throwable class:{}", id, e.getClass());
        User defaultUser = new User(-2L, "defaultUser2", "123456");
        return new CommonResult<>(defaultUser);
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public CommonResult getUserCache(Long id) {
        logger.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public CommonResult removeCache(Long id) {
        logger.info("removeCache id:{}", id);
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, CommonResult.class, id);
    }

    /**
     * 为缓存生成key的方法
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }


    @HystrixCollapser(batchMethod = "getUserByIds",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "2000")
            })
    public Future<User> getUserFuture(Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                CommonResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
                User user = (User) commonResult.getData();
//                User user = MyBeanUtils.mapToBean(data,User.class);
                logger.info("getUserById username:{}", user.getUsername());

                return (User) commonResult.getData();
            }
        };
    }

    @HystrixCommand
    public List<User> getUserByIds(List<Long> ids) {
        logger.info("getUserByIds:{}", ids);
//        CommonResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/getUserByIds?ids={1}", CommonResult.class, StringUtils.join(ids, ","));
//        return (List<User>) commonResult.getData();
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "macro", "123456"));
        userList.add(new User(2L, "andy", "123456"));
//        userList.add(new User(2L, "andy", "123456"));
//        userList.add(new User(2L, "andy", "123456"));
//        userList.add(new User(2L, "andy", "123456"));
//        userList.add(new User(2L, "andy", "123456"));
//        return (User) commonResult.getData();
        return userList;
    }
}