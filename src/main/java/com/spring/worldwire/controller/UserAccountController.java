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

    @RequestMapping("/sign")
    @ResponseBody
    public Map<String,Object> sign(Long userId){
        Map<String,Object> map = new HashMap<String,Object>();
        String cacheKey = Constants.CACHE_SIGN_KEY + userId;
        if(Objects.nonNull(redisUtils.getValueByKey(cacheKey))){
            map.put("msg",StatusCodeEnum.EXISTS.getMsg());
            map.put("code", StatusCodeEnum.EXISTS.getCode());
            return map;
        }
        long interval = DateUtil.getTimeInterval(new Date());
        calculateSignDays(userId);
        redisUtils.set(cacheKey,userId,interval);
        map.put("msg",StatusCodeEnum.SUCCCESS.getMsg());
        map.put("code", StatusCodeEnum.SUCCCESS.getCode());
        return map;
    }

    private void calculateSignDays(Long userId) {
        UserAccount account = userAccountService.selectByUserId(userId);
        Date last = account.getLastSignTime();
        Date now = new Date();
        try {
            if(DateUtil.dateInterval(last,now) == 1){//时间相差一天即为连续签到，否则连续签到天数为1
                account.setSignNum(account.getSignNum() + 1);
                if(account.getSignNum() % 7 == 0){//每7天增加一次免费翻译次数
                    account.setFreeTranslate(account.getFreeTranslate() + 1);
                }
                userAccountService.updateUserAccount(account);
            }else{
                account.setSignNum(1);//时间不连续，则连续签到天数置为1
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
