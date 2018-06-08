package com.spring.worldwire.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.HttpUtils;
import com.spring.worldwire.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by luxun on 2018/4/28.
 */
@Controller
@RequestMapping("/center")
public class UserCenterController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TradeOrderService tradeOrderservice;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/detail")
    public String userCenter(TradeOrderQuery query) {
        JSONObject json = new JSONObject();
        UserAccount account = userAccountService.selectByUserId(query.getUserId());
        List<TradeOrder> tradeOrder = tradeOrderservice.selectByPage(query);
        json.put("account", account);
        json.put("tradeOrder", tradeOrder);
        return json.toJSONString();
    }

    /**
     * 签到，如果已经签到，则签到失败，否则签到天数加1
     * 签到满7天增加一次免费翻译次数
     *
     * @param userId
     * @return
     */
    @RequestMapping("/sign")
    public ResponseResult signUp(Long userId) {
        try {
            if (redisUtils.isCacheExists(Constants.CACHE_SIGN_KEY + userId)) {
                return new ResponseResult(null, StatusCodeEnum.EXISTS, "已经签到");
            }
            UserAccount account = userAccountService.selectByUserId(userId);
            account.setSignNum(account.getSignNum() + 1);
            if (account.getSignNum() % 7 == 0) {//每7天增加一次免费翻译次数
                account.setFreeTranslate(account.getFreeTranslate() + 1);
                userAccountService.updateUserAccount(account);
                //todo 这里没有增加交易记录，以后如果有需要可以加上
            }
            redisUtils.set(Constants.CACHE_SIGN_KEY + userId, true, DateUtil.getTimeInterval(new Date()));//存入redis
            return new ResponseResult(null, StatusCodeEnum.SUCCESS, "签到成功");
        } catch (Exception e) {
            return new ResponseResult(null, StatusCodeEnum.ERROR, "签到系统异常");
        }
    }

    /**
     * 修改密码操作
     *
     * @param oldPass
     * @param newPass
     * @param request
     */
    public ResponseResult modifyPassword(Long userId, String oldPass, String newPass, HttpServletRequest request) {
        if (StringUtils.isEmpty(HttpUtils.getCookieByKey("loginKey" + userId, request))) {
            return new ResponseResult(null, StatusCodeEnum.OUT_OF_DATE, "登录超时");
        }
        LoginInfo info = loginInfoService.selectByPrimaryKey(userId);
        if (oldPass.equals(info.getPassword())) {
            info.setPassword(newPass);
            loginInfoService.update(info);
            return new ResponseResult(null, StatusCodeEnum.SUCCESS, "修改成功");
        }
        return new ResponseResult(null, StatusCodeEnum.FAIL, "修改失败");
    }
}
