package com.wzl.cloud.domain;

import lombok.Data;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 17:33
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
