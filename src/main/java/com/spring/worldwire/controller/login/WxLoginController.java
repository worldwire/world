package com.spring.worldwire.controller.login;

import com.spring.worldwire.config.WXPayConfigImpl;
import com.spring.worldwire.utils.CheckoutUtil;
import com.spring.worldwire.utils.HttpReqUtil;
import com.spring.worldwire.utils.pay.weixin.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author luxun.
 * @Date 2018/5/8 15:55
 * @Package com.spring.worldwire.controller.login
 */
@RequestMapping("/login/wx")
@Controller
public class WxLoginController {

    /**
     * 微信消息接收和token验证
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/ownerCheck")
    public void ownerCheck(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            Map<String,String> signMap = new HashMap<String,String>();
            signMap.put("signature",signature);
            signMap.put("timestamp",timestamp);
            signMap.put("nonce",nonce);
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 第一步：用户同意授权，获取code(引导关注者打开如下页面：)
     *  获取 code、state
     */
    @RequestMapping("/code")
    public String getStartURLToGetCode() throws Exception {
        //https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String takenUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        takenUrl= takenUrl.replace("APPID", "wxc137a44afa99aead");
        takenUrl= takenUrl.replace("SCOPE", "snsapi_base ");
        takenUrl= takenUrl.replace("STATE", "123456");
        takenUrl= takenUrl.replace("REDIRECT_URI", URLEncoder.encode("http://heatstone-party.com/login/wx/accesstoken","UTF-8"));
        //FIXME ： snsapi_userinfo
        System.out.println(takenUrl);
        return "redirect:"+ takenUrl;
    }
    /**
     * 获取access_token、openid
     * 第二步：通过code获取access_token
     * @param code url = "https://api.weixin.qq.com/sns/oauth2/access_token
     *                      ?appid=APPID
     *                      &secret=SECRET
     *                      &code=CODE
     *                      &grant_type=authorization_code"
     * */
    @RequestMapping("/accesstoken")
    public  String getAccess_token(String code) throws  Exception{
        String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        authUrl= authUrl.replace("APPID", "wxc137a44afa99aead");
        authUrl = authUrl.replace("SECRET","038ae35ba6639748fd318fb5b3dbca3c");
        authUrl = authUrl.replace("CODE", code);
        String jsonString = HttpReqUtil.sendHttpGetRequest(authUrl,null);
        System.out.println("jsonString: " + jsonString);
        return jsonString;
    }
}
