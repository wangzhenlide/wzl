package com.wzl.cloud.mapper;


import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountMapper {

    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
