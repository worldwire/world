package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.UserAccountService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/23 17:45
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Controller
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping("/lc/share")
    @ResponseBody
    public String thirdShare(HttpServletRequest request) {
        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId) || !NumberUtils.isNumber(userId.toString())) {
            return "";
        }
        UserAccount account = userAccountService.selectByUserId((Long) userId);
        userAccountService.updateViewCount(account);
        return "";
    }
}
