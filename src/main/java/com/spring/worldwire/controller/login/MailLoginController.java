package com.spring.worldwire.controller.login;

import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/login/mail")
public class MailLoginController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    @Qualifier("mailService")
    private LoginService loginService;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult registerByEamil(LoginInfo loginInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer id = loginService.register(loginInfo);
            if (id > 0) {
                return new ResponseResult(id, StatusCodeEnum.SUCCESS, "注册成功");
            }
            return new ResponseResult(id, StatusCodeEnum.FAIL, "注册失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(null, StatusCodeEnum.ERROR, "服务端异常");
        }
    }

    @RequestMapping("")
    public ResponseResult loginByMail(LoginInfo loginInfo, HttpServletResponse response, HttpServletRequest request) {
        try {
            LoginInfo info = loginService.login(loginInfo, response,request);
            if (Objects.isNull(info)) {
                return new ResponseResult(null, StatusCodeEnum.EMPTY, "没找到相关用户");
            }
            return new ResponseResult(info, StatusCodeEnum.SUCCESS, "登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(null, StatusCodeEnum.ERROR, "登陆异常");
        }
    }

}
