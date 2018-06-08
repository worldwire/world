package com.spring.worldwire.controller;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.utils.MailUtils;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

/**
 * Created by luxun on 2018/4/23.
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LoginInfoService loginInfoService;

    @RequestMapping("/send")
    public String send(Long userId){
        LoginInfo info = loginInfoService.selectByPrimaryKey(userId);
        try {
            Date date = new Date();
            String url = Constants.MAIL_ADDRESS_PREFIX + "?timestamps=" + date.getTime() + "&userId=" + userId;
            MailUtils.sendSimpleMail(info.getEmail(), date, url);
            redisUtils.set(Constants.CACHE_MAIL_VALID_PREFIX + userId + "_" + date.getTime() ,"" ,10 * 60 * 1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("/register")
    public String register(Long userId){
        LoginInfo info = loginInfoService.selectByPrimaryKey(userId);
        try {
            Date date = new Date();
            String url = wrapRegisterUrl(userId,date);
            MailUtils.sendSimpleMail(info.getEmail(), date, url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String wrapRegisterUrl(Long userId,Date date){
        String url = Constants.MAIL_ADDRESS_PREFIX + "?timestamps=" + date.getTime() + "&userId=" + userId;
        redisUtils.set(Constants.CACHE_MAIL_VALID_PREFIX + userId + "_" + date.getTime() ,"" ,Constants.MAIL_CODE_INVALIDATE_TIME);
        return url;
    }

}
