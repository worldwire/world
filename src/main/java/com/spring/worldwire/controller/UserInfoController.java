package com.spring.worldwire.controller;

import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/fullfillInfo")
    public Map<String,Object> fullFillUserInfo(UserInfo userInfo){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            int status = userInfoService.insert(userInfo);
            if(status == 1){
                map.put("msg", StatusCodeEnum.SUCCCESS.getMsg());
                map.put("status", StatusCodeEnum.SUCCCESS.getCode());
                return map;
            }
            map.put("msg", StatusCodeEnum.FAIL.getMsg());
            map.put("status", StatusCodeEnum.FAIL.getCode());
        }catch (Exception e){
            map.put("msg", StatusCodeEnum.ERROR.getMsg());
            map.put("status", StatusCodeEnum.ERROR.getCode());
        }
        return map;
    }
}
