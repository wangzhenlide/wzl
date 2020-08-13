package com.wzl.cloud.nacosclient.service;


import com.wzl.cloud.nacosclient.entity.User;

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