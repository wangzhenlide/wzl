package com.wzl.cloud.userserivce.service;

import com.wzl.cloud.userserivce.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/6 17:27
 */
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}