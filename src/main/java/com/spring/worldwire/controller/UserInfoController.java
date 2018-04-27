package com.spring.worldwire.controller;

import com.spring.worldwire.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luxun on 2018/4/27.
 */
@Controller
@RequestMapping("/info")
public class UserInfoController {

    @RequestMapping("/fullfillInfo")
    public Map<String,Object> fullFillUserInfo(UserInfo userInfo){
        Map<String,Object> map = new HashMap<String,Object>();
        try{

        }catch (Exception e){
            e.printStackTrace();;
        }
        return map;
    }
}
