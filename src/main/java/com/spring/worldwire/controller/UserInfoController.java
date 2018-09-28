package com.spring.worldwire.controller;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.CountryEnum;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.LevelEnum;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luxun on 2018/4/27.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/insert")
    public ResponseResult insertUserInfo(UserInfo userInfo) {
        try {
            int status = userInfoService.insert(userInfo);
            if (status == 1) {
                return new ResponseResult<>(null, StatusCodeEnum.SUCCESS.getCode(), "添加成功");
            }
            return new ResponseResult<>(null, StatusCodeEnum.FAIL.getCode(), "添加返回码错误");
        } catch (Exception e) {
            return new ResponseResult<>(null, StatusCodeEnum.ERROR.getCode(), "添加操作系统异常:");
        }
    }


    @RequestMapping("detail")
    public String detail() {
        return "";
    }

    @RequestMapping("toModify")
    public String toModify(HttpServletRequest request) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();
        UserInfo userInfo = userInfoService.selectById(Long.parseLong(userIdStr));

        request.setAttribute("userInfo", userInfo);
        request.setAttribute("languageValues", LanguageEnum.values());
        request.setAttribute("countryList",  CountryEnum.values());
        request.setAttribute("levelValues", LevelEnum.values());

        return "pc/personData";
    }

    @RequestMapping("modify")
    public String modify(HttpServletRequest request, UserInfo userInfo) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();

        userInfo.setId(Long.parseLong(userIdStr));
        userInfoService.updateByPrimaryKeySelective(userInfo);

        return "redirect:/usercenter/";

    }

}
