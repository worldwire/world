package com.spring.worldwire.controller.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther pg
 * @Date create in 17:36 2018/7/29
 */
@SuppressWarnings("unused")
@Controller("platformIndexController")
@RequestMapping("/platform")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "platform/index";
    }


    @RequestMapping("/console")
    public String console(){
        return "platform/console";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "platform/login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "platform/register";
    }

    @RequestMapping("/toForgot")
    public String toForget(){
        return "platform/forgot";
    }
}
