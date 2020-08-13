package com.wzl.cloud.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 17:44
 */
@Data
public class Account {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用额度
     */
    private BigDecimal used;

    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
