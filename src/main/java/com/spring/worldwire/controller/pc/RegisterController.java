package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public String company(HttpServletRequest request){
        saveUser(request,0);
        return "redirect:/";
    }



    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        saveUser(request,1);
        return "redirect:/";
    }

    private void saveUser(HttpServletRequest request, int i) {
        HttpSession session = request.getSession();
        String userIdStr = session.getAttribute(Constants.USER_SESSION).toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(userIdStr));
        userInfo.setType(i);
        userInfoService.update(userInfo);

    }
}
