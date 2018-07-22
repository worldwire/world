package com.spring.worldwire.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.manager.UserCenterManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 10:26
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Controller
@RequestMapping("/usercenter")
public class UserCenterController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserCenterManager userCenterManager;
    @Autowired
    private TradeOrderService tradeOrderService;

    @RequestMapping("/")
    public String toUserCenter(HttpServletRequest request, Model model) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId) || !NumberUtils.isNumber(userId.toString())) {
            return "redirect:/login/";
        }
        Map<String, Object> map = userCenterManager.getUserDetailInfo((Long) userId);
        model.addAllAttributes(map);
        return "pc/userCenter";
    }

    /**
     * 签到，如果已经签到，则签到失败，否则签到天数加1
     * 签到满7天增加一次免费翻译次数,仅增加一次
     *
     * @return
     */
    @RequestMapping("/sign")
    @ResponseBody
    public UserAccount signUp(HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId)) {
            return null;
        }
        return userCenterManager.signUp(Long.parseLong(userId.toString()));
    }

    @RequestMapping("/passwd/edit")
    public String editPasswd() {
        return "/pc/modifyPasswd";
    }

    /**
     * 修改密码操作
     *
     * @param oldPass
     * @param newPass
     * @param request
     */
    @RequestMapping("/modify")
    public ResponseResult modifyPassword(String oldPass, String newPass, HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId)) {
            return null;
        }

        if (StringUtils.isEmpty(HttpUtils.getCookieByKey("loginKey" + userId, request))) {
            return new ResponseResult(null, StatusCodeEnum.OUT_OF_DATE, "登录超时");
        }
        LoginInfo info = loginInfoService.selectByPrimaryKey(Long.parseLong(userId.toString()));
        if (oldPass.equals(info.getPassword())) {
            info.setPassword(newPass);
            loginInfoService.update(info);
            return new ResponseResult(null, StatusCodeEnum.SUCCESS, "修改成功");
        }
        return new ResponseResult(null, StatusCodeEnum.FAIL, "修改失败");
    }


}
