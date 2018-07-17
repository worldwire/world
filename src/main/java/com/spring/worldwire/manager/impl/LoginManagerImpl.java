package com.spring.worldwire.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.LoginManager;
import com.spring.worldwire.model.RequestViews;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.RequestViewsService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/16 17:07
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service
public class LoginManagerImpl implements LoginManager {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RequestViewsService requestViewsService;

    @Override
    public String viewRequestContract(Long userId, Long productRequestId) {

        RequestViews views = requestViewsService.selectByUserId(userId);
        //已经查看过该需求，直接显示即可
        if (Objects.nonNull(views)) {
            return JSONObject.toJSONString(userInfoService.selectById(userId));
        }

        // 未查看过需求且当天免费查看尚未使用
        boolean noFreeView = redisUtils.isCacheExists(Constants.CACHE_FREE_LOOK_UP + userId);
        if (!noFreeView) {
            processFreeViews(userId, productRequestId);
            return JSONObject.toJSONString(userInfoService.selectById(userId));
        }

        // 未查看过需求且免费查看已经被使用
        UserAccount userAccount = userAccountService.selectByUserId(userId);
        if (userAccount.getViewingTimes() <= 0) {//没有付费查看次数
            return "false";
        }

        // 有付费查看次数，次数减一，并增加查看记录
        processChargeViews(userId, productRequestId, userAccount);
        return JSONObject.toJSONString(userInfoService.selectById(userId));

    }

    @Override
    public void login(String email, String password) {

    }

    /**
     * 处理付费查看次数
     *
     * @param userId
     * @param productRequestId
     */
    private void processChargeViews(Long userId, Long productRequestId, UserAccount userAccount) {

        userAccount.setViewingTimes(userAccount.getViewingTimes() - 1);
        userAccountService.updateUserAccount(userAccount);

        // 增加一条查看记录
        RequestViews requestViews = new RequestViews();
        requestViews.setUserId(userId);
        requestViews.setRequestId(productRequestId);
        requestViews.setCreateTime(new Date());
        requestViews.setUpdateTime(new Date());
        requestViewsService.insertRequestViews(requestViews);

    }

    /**
     * 处理免费次数的使用情况
     *
     * @param userId
     * @param productRequestId
     */
    private void processFreeViews(Long userId, Long productRequestId) {
        // 缓存中增加已使用的记录
        long remains = DateUtil.getTimeInterval(new Date());
        redisUtils.set(Constants.CACHE_FREE_LOOK_UP + userId, productRequestId, remains);
        // 增加一条查看记录
        RequestViews requestViews = new RequestViews();
        requestViews.setUserId(userId);
        requestViews.setRequestId(productRequestId);
        requestViews.setCreateTime(new Date());
        requestViews.setUpdateTime(new Date());
        requestViewsService.insertRequestViews(requestViews);
    }
}
