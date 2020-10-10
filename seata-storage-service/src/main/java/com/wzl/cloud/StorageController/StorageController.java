package com.wzl.cloud.StorageController;

import com.wzl.cloud.service.StorageService;
import domain.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 17:36
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @GetMapping("/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult("扣减库存成功！",200);
    }
}
