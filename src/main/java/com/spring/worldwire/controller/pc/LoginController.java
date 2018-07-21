package com.spring.worldwire.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.manager.LoginManager;
import com.spring.worldwire.manager.ProductReuestManager;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/11 13:54
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private ProductReuestManager productReuestManager;

    public LoginController(LoginInfoService loginInfoService, UserInfoService userInfoService) {
        this.loginInfoService = loginInfoService;
        this.userInfoService = userInfoService;
    }

    @RequestMapping("/")
    public String toLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("userid") != null) {
            return "redirect:" + request.getHeader("Referer");
        }
        return "pc/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request, String email, String password) {

        UserInfo userInfo = loginManager.login(email, password, response);
        if (Objects.isNull(userInfo)) {
            return "pc/blank";
        }
        if (userInfo.getType() == null) {
            return "pc/registerAfter";
        }
        return "pc/blank";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {

        loginManager.logout(response);

        return "redirect:/";
    }

    /**
     * 异步登陆
     *
     * @param email
     * @param password
     * @param response
     * @return
     */
    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public ResponseResult ajaxLogin(String email, String password, HttpServletResponse response) {
        try {
            UserInfo userInfo = loginManager.login(email, password, response);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(null, StatusCodeEnum.EMPTY, "用户名密码不正确");
            }
            return new ResponseResult(userInfo, StatusCodeEnum.SUCCESS, "登陆成功");
        } catch (Exception e) {
            logger.error("登陆出错", e);
            return new ResponseResult(null, StatusCodeEnum.ERROR, "登陆异常");
        }
    }

    /**
     * 查看联系方式
     *
     * @param productRequestId
     * @return
     */
    @RequestMapping("/showContracts")
    @ResponseBody
    public String showContracts(Long productRequestId,HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId)) {
            return "login";
        }

        if (Objects.isNull(userId) || Objects.isNull(productRequestId)) {
            return "error";
        }
        UserInfo userInfo = productReuestManager.viewRequestContract(Long.parseLong(userId.toString()), productRequestId);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        return JSONObject.toJSONString(userInfo);
    }

}
