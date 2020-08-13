package com.wzl.cloud.mapper;


import org.apache.ibatis.annotations.Param;

public interface StorageMapper {

    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}
