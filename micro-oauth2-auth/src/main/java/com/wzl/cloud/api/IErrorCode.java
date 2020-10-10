package com.wzl.cloud.api;

/**
 * 功能描述: 封装API的错误码
 * @Author: wzl
 * @Date: 2020/8/21 14:32
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
