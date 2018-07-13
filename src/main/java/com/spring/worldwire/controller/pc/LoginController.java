package com.spring.worldwire.controller.pc;

import com.alibaba.fastjson.JSON;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/11 13:54
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginInfoService loginInfoService;

    @RequestMapping("/")
    public String toLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("userid") != null) {
            return "redirect:" + request.getHeader("Referer");
        }
        return "pc/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String email, String password){
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        query.setPassword(password);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if(CollectionUtils.isEmpty(list)){
            return "";
        }
        return JSON.toJSONString(list.get(0));
    }

}
