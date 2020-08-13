package com.wzl.cloud.seataorderservice.mapper;

import com.wzl.cloud.seataorderservice.domain.Order;

public interface OrderMapper {

    void create(Order order);

    void update(Long userId, int status);

}
