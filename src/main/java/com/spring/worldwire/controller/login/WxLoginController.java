package com.spring.worldwire.controller.login;

import com.spring.worldwire.utils.HttpReqUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

/**
 * @Author luxun.
 * @Date 2018/5/8 15:55
 * @Package com.spring.worldwire.controller.login
 */
@RequestMapping("/login/wechat")
@Controller
public class WxLoginController {

    @Value("${wx.appid}")
    private String appId;

    @RequestMapping("/auth")
    public String auth(Model model) throws Exception {

        String redirectURI = URLEncoder.encode("http://www.theworldwire.cn/login/wechat/callback", "UTF-8");
//        return "/pc/wxCodeScan";
        return "redirect:https://open.weixin.qq.com/connect/qrconnect?appid=" + appId + "&redirect_uri=" + redirectURI + "&response_type=code&scope=snsapi_login&state=worldwire#wechat_redirect";
    }

    @RequestMapping("/callback")
    public String callback() throws Exception {
        //https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String takenUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        takenUrl = takenUrl.replace("APPID", "wxc137a44afa99aead");
        takenUrl = takenUrl.replace("SCOPE", "snsapi_base ");
        takenUrl = takenUrl.replace("STATE", "123456");
        takenUrl = takenUrl.replace("REDIRECT_URI", URLEncoder.encode("http://heatstone-party.com/login/wx/accesstoken", "UTF-8"));
        //FIXME ： snsapi_userinfo
        System.out.println(takenUrl);
        return "redirect:" + takenUrl;
    }

    /**
     * 获取access_token、openid
     * 第二步：通过code获取access_token
     *
     * @param code url = "https://api.weixin.qq.com/sns/oauth2/access_token
     *             ?appid=APPID
     *             &secret=SECRET
     *             &code=CODE
     *             &grant_type=authorization_code"
     */
    @RequestMapping("/accesstoken")
    public String getAccess_token(String code) throws Exception {
        String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        authUrl = authUrl.replace("APPID", "wxc137a44afa99aead");
        authUrl = authUrl.replace("SECRET", "038ae35ba6639748fd318fb5b3dbca3c");
        authUrl = authUrl.replace("CODE", code);
        String jsonString = HttpReqUtil.sendHttpGetRequest(authUrl, null);
        System.out.println("jsonString: " + jsonString);
        return jsonString;
    }
}
