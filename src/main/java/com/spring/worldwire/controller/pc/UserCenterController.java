package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.manager.UserCenterManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.Base64;
import com.spring.worldwire.utils.MailUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 10:26
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Controller
@RequestMapping("/usercenter")
public class UserCenterController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserCenterManager userCenterManager;
    @Autowired
    private TradeOrderService tradeOrderService;

    @RequestMapping("/")
    public String toUserCenter(HttpServletRequest request, Model model) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId) || !NumberUtils.isNumber(userId.toString())) {
            return "redirect:/login/";
        }
        Map<String, Object> map = userCenterManager.getUserDetailInfo((Long) userId);
        model.addAllAttributes(map);
        return "pc/userCenter";
    }

    /**
     * 签到，如果已经签到，则签到失败，否则签到天数加1
     * 签到满7天增加一次免费翻译次数,仅增加一次
     *
     * @return
     */
    @RequestMapping("/sign")
    @ResponseBody
    public UserAccount signUp(HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId)) {
            return null;
        }
        return userCenterManager.signUp(Long.parseLong(userId.toString()));
    }

    @RequestMapping("/passwd/edit")
    public String editPasswd() {
        return "pc/modifyPasswd";
    }


    /**
     * 修改密码操作
     *
     * @param oldPass
     * @param newPass
     * @param request
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modifyPassword(String oldPass, String newPass, HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);
        if (Objects.isNull(userId)) {
            return null;
        }
        LoginInfo info = loginInfoService.selectByPrimaryKey(Long.parseLong(userId.toString()));
        if (Objects.isNull(info)) {
            return new ResponseResult(null, StatusCodeEnum.EMPTY.getCode(), "用户不存在");
        }

        LoginInfoQuery query = new LoginInfoQuery();
        query.setPassword(oldPass);
        query.setEmail(info.getEmail());
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (CollectionUtils.isEmpty(list) || !list.get(0).getId().equals(info.getId())) {
            return new ResponseResult(null, StatusCodeEnum.FAIL.getCode(), "账号错误");
        }
        info.setPassword(newPass);
        loginInfoService.update(info);
        return new ResponseResult(null, StatusCodeEnum.SUCCESS.getCode(), "修改成功");
    }


    @RequestMapping("/passwd/forget")
    public String getPass() {
        return "pc/resetPasswd";
    }

    @RequestMapping("/toSendMail")
    public String tosendMail(String mail, Model model) {
        model.addAttribute("mail", mail);
        return "pc/sendmail";
    }

    @ResponseBody
    @RequestMapping("/checkmail")
    public ResponseResult String(String email) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseResult(null, StatusCodeEnum.EMPTY.getCode(), "邮箱不存在");
        }
        LoginInfo info = list.get(0);
        Date date = new Date();
        String url = Constants.MAIL_MODIFY_ADDRESS_PREFIX + "/" + Base64.encode(info.getId().toString().getBytes()) + "/" + date.getTime();
        try {
            MailUtils.sendResetPasswordMail(info.getEmail(), date, url);
        } catch (Exception e) {
            new ResponseResult(null, StatusCodeEnum.ERROR.getCode(), "系统异常");
        }

        return new ResponseResult(null, StatusCodeEnum.SUCCESS.getCode(), "验证成功");
    }

    @RequestMapping("/toModify/{data}/{time}")
    public String String(@PathVariable String data, @PathVariable String time, Model model) {

        model.addAttribute("data", data);
        return "pc/getPasswd";

    }

    @RequestMapping("/reset")
    @ResponseBody
    public ResponseResult passwdreset(String email, String password, String data, HttpServletRequest request) throws UnsupportedEncodingException {

        if (Objects.isNull(data) || Objects.isNull(email) || Objects.isNull(password)) {
            return new ResponseResult(null, StatusCodeEnum.FAIL.getCode(), "非法请求");
        }
        String userId = new String(Base64.decode(data), "utf8");

        LoginInfo info = loginInfoService.selectByPrimaryKey(Long.parseLong(userId.toString()));
        if (Objects.isNull(info)) {
            return new ResponseResult(null, StatusCodeEnum.EMPTY.getCode(), "用户不存在");
        }
        if (!email.equalsIgnoreCase(info.getEmail())) {
            return new ResponseResult(null, StatusCodeEnum.FAIL.getCode(), "非法请求");
        }
        info.setPassword(password);
        loginInfoService.update(info);
        return new ResponseResult(null, StatusCodeEnum.SUCCESS.getCode(), "修改成功");
    }


}
