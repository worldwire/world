package com.spring.worldwire.controller.pc;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.manager.RegisterManager;
import com.spring.worldwire.manager.RegisterUserInfoManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private MessageSource messageSource;

    private Locale locale = LocaleContextHolder.getLocale();

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private RegisterManager registerManager;
    @Autowired
    private RegisterUserInfoManager registerUserInfoManager;
    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping("/")
    public String toRegister() {

        return "pc/register";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResponseResult save(String userName, String email, String password) {

        try {
            LoginInfo name = registerManager.selectByUserName(userName);
            if (name != null) {
                return new ResponseResult(null, StatusCodeEnum.EXISTS.getCode(), "用户名已存在");
            }
            LoginInfo mailInfo = registerManager.selectByEmail(email);
            if (mailInfo != null) {
                return new ResponseResult(null, StatusCodeEnum.EXISTS.getCode(), "邮箱已经被注册");
            }
            LoginInfo info = registerManager.register(userName, email, password);
            return new ResponseResult(info, StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseResult(null, StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMsg());
        }

    }

    @RequestMapping("toSendMail")
    public String sendMail(Long id, Model model) {

        LoginInfo loginInfo = loginInfoService.selectByPrimaryKey(id);
        if (!Objects.isNull(loginInfo) && loginInfo.getStatus() == 0) {
            model.addAttribute("mail", loginInfo.getEmail());
            return "pc/sendmail";
        }
        model.addAttribute("msg", "用户状态异常");
        return "pc/error";
    }

    @RequestMapping("/active/{loginId}/{timestamp}")
    public String active(@PathVariable Long loginId, @PathVariable Long timestamp, Model model) {

        LoginInfo loginInfo = loginInfoService.selectByPrimaryKey(loginId);
        if (Objects.isNull(loginInfo)) {
            model.addAttribute("msg", "用户不存在");
            return "pc/error";
        }
        if (loginInfo.getStatus() != 0) {
            model.addAttribute("msg", messageSource.getMessage("notactive", null, locale));
            return "pc/error";
        }
//        //redis缓存
//        if (!redisUtils.isCacheExists(CACHE_MAIL_VALID_PREFIX + loginInfo.getId() + "_" + timestamp)) {
//            model.addAttribute("msg", "邮箱激活已过期，请重新注册激活！");
//            return "pc/error";
//        }
        loginInfo.setStatus((byte) 1);
        loginInfoService.update(loginInfo);
        model.addAttribute("msg", messageSource.getMessage("activesuccess", null, locale));
        return "pc/success";
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
