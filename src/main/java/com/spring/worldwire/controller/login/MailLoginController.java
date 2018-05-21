package com.spring.worldwire.controller.login;

import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String, Object> registerByEamil(LoginInfo loginInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer id = loginService.register(loginInfo);
            if (id > 0) {
                map.put("data", id);
                map.put("msg", StatusCodeEnum.SUCCCESS.getMsg());
                map.put("status", StatusCodeEnum.SUCCCESS.getCode());
                return map;
            }
            map.put("data", id);
            map.put("msg", StatusCodeEnum.FAIL.getMsg());
            map.put("status", StatusCodeEnum.FAIL.getCode());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", StatusCodeEnum.ERROR.getMsg());
            map.put("status", StatusCodeEnum.ERROR.getCode());
        }
        return map;
    }

    @RequestMapping("")
    public Map<String, Object> loginByMail(LoginInfo loginInfo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LoginInfo info = loginService.login(loginInfo, response);
            if (Objects.isNull(info)) {
                map.put("data", null);
                map.put("msg", StatusCodeEnum.EMPTY.getMsg());
                map.put("status", StatusCodeEnum.EMPTY.getCode());
                return map;
            }
            map.put("data", info);
            map.put("msg", StatusCodeEnum.SUCCCESS.getMsg());
            map.put("status", StatusCodeEnum.SUCCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", StatusCodeEnum.ERROR.getMsg());
            map.put("status", StatusCodeEnum.ERROR.getCode());
        }
        return map;
    }

}
