package com.wzl.cloud.StorageController;

import com.wzl.cloud.service.AccountService;
import domain.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description
 * @Author wzl
 * @Date 2020/8/13 17:36
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 扣减账户余额
     */
    @GetMapping("/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) throws Exception {
        accountService.decrease(userId, money);
        return new CommonResult("扣减账户余额成功！", 200);
    }
}
