package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.manager.RegisterManager;
import com.spring.worldwire.manager.RegisterUserInfoManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private RegisterManager registerManager;
    @Autowired
    private RegisterUserInfoManager registerUserInfoManager;


    @RequestMapping("/")
    public String toRegister() {

        return "pc/register";
    }

    @RequestMapping("/save")
    public ResponseResult save(String userName, String email, String password) {

        try {
            LoginInfo name = registerManager.selectByUserName(userName);
            if (name != null){
                return new ResponseResult(null, StatusCodeEnum.EXISTS,"用户名已存在");
            }
            registerManager.register(userName, email, password);
            return new ResponseResult(null, StatusCodeEnum.SUCCESS,StatusCodeEnum.SUCCESS.getMsg());
        }catch (Exception e){
            return new ResponseResult(null, StatusCodeEnum.ERROR,StatusCodeEnum.ERROR.getMsg());
        }

    }

    @RequestMapping("fillType")
    public String fillType() {
        return "pc/fillType";
    }

    @RequestMapping("fillForeign")
    public String fillForeign() {
        return "pc/fillForeign";
    }

    @RequestMapping("/completeType/{type}")
    public String company(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") int type) {
        UserTypeEnum nameByCode = UserTypeEnum.getNameByCode(type);
        registerUserInfoManager.registerUserType(request, response, nameByCode);
        return "pc/fillForeign";
    }

    @RequestMapping("/completeForeign/{type}")
    public String completeForeign(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") int type) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();
        registerUserInfoManager.registerUserForeign(Long.parseLong(userIdStr), type);
        return "pc/blank";
    }

}
