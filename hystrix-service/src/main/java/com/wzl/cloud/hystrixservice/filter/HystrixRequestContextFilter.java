package com.wzl.cloud.hystrixservice.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/13 9:18
 */
@WebFilter
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (HystrixRequestContext ignored = HystrixRequestContext.initializeContext()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
