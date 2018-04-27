package com.spring.worldwire.controller;

import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luxun on 2018/4/27.
 */
@Controller
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping("/init")
    public void initUserAccount(Long userId){
        Map<String,Object> map = new HashMap<String,Object>();
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
