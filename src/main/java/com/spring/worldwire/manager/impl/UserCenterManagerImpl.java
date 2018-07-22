package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.UserCenterManager;
import com.spring.worldwire.model.ThirdPayOrder;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.ThirdPayOrderQuery;
import com.spring.worldwire.service.ThirdPayOrderService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 10:34
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service
public class UserCenterManagerImpl implements UserCenterManager {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ThirdPayOrderService thirdPayOrderService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Map<String, Object> getUserDetailInfo(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        UserAccount userAccount = userAccountService.selectByUserId(userId);
        map.put("account", userAccount);

        ThirdPayOrderQuery query = new ThirdPayOrderQuery();
        query.setPageNo(1);
        query.setPageSize(10);
        query.setUserId(userId);
        List<ThirdPayOrder> list = thirdPayOrderService.selectByUserId(query);
        map.put("payOrderList", list);

        return map;
    }

    @Override
    public UserAccount signUp(Long userId) {
        if (redisUtils.isCacheExists(Constants.CACHE_SIGN_KEY + userId)) {
            // 当天已经签到
            return null;
        }
        UserAccount account = userAccountService.selectByUserId(userId);
        account.setSignNum(account.getSignNum() + 1);
        if (account.getSignNum() % 7 == 0 && account.getFreeTranslate() == 0) {
            //连续签到7天增加一次免费翻译次数,但只有一次
            account.setFreeTranslate(account.getFreeTranslate() + 1);
            account.setAddFreeTranslate(true);
            //todo 这里没有增加交易记录，以后如果有需要可以加上
        }
        userAccountService.updateUserAccount(account);
        redisUtils.set(Constants.CACHE_SIGN_KEY + userId, true, DateUtil.getTimeInterval(new Date()));//存入redis
        return account;
    }
}
