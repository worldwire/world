package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserInfoService;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.worldwire.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;

    public RegisterController(LoginInfoService loginInfoService, UserInfoService userInfoService) {
        this.loginInfoService = loginInfoService;
        this.userInfoService = userInfoService;
    }


    @RequestMapping("/")
    public String toRegister() {

        return "pc/register";
    }

    @RequestMapping("/save")
    public String save(LoginInfo loginInfo){

        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte)1);
        loginInfoService.insert(loginInfo);
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(loginInfo.getId());

        userInfoService.insert(userInfo);

        return "pc/login";
    }

    @RequestMapping("/company")
    public String company(HttpServletRequest request,HttpServletResponse response){
        saveUser(request,response,0);
        return "pc/blank";
    }



    @RequestMapping("/person")
    public String person(HttpServletRequest request,HttpServletResponse response){
        saveUser(request,response,1);
        return "pc/blank";
    }

    private void saveUser(HttpServletRequest request,HttpServletResponse response, int i) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(userIdStr));
        userInfo.setType(i);
        userInfoService.update(userInfo);

        String encruptedCookie = Base64.encode(userInfo.cookiesValue().getBytes());
        Cookie userIdCookie = new Cookie(Constants.USER_COOKIES_NAME,encruptedCookie);
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(3600);
        response.addCookie(userIdCookie);
    }
}
