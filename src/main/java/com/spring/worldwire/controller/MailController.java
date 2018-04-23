package com.spring.worldwire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luxun on 2018/4/23.
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @RequestMapping("/send")
    public String sendMail(Integer userId){


        return "";
    }

}
