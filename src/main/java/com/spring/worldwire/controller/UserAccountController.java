package com.spring.worldwire.controller;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * Created by luxun on 2018/4/27.
 */
@Controller
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 注册完成之后初始化用户账号信息
     * @param userId
     */
    @RequestMapping("/init")
    public void initUserAccount(Long userId){
        try {
            UserAccount account = new UserAccount();
            account.setFreeTranslate(0);
            account.setSignNum(0);
            account.setViewingTimes(0);
            account.setCreateTime(new Date());
            account.setLastSignTime(new Date());
            account.setUpdateTime(new Date());
            userAccountService.insert(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
