package com.wzl.cloud.seataorderservice.controller;

import com.wzl.cloud.seataorderservice.domain.Order;
import com.wzl.cloud.seataorderservice.service.OrderService;
import domain.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 16:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public CommonResult create(Order order) {
        order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setMoney(BigDecimal.ONE);
        order.setProductId(1L);
        order.setCount(1);
        orderService.create(order);
        return new CommonResult("订单创建成功!", 200);
    }
}
