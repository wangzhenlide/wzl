package com.wzl.cloud.seataorderservice.service;

import com.wzl.cloud.seataorderservice.domain.Order;

public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
