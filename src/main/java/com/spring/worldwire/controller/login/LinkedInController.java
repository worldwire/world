package com.spring.worldwire.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.utils.login.linkedin.LinkedInConfig;
import com.spring.worldwire.utils.login.linkedin.LinkedInHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Author luxun.
 * @Date 2018/5/8 17:14
 * @Package com.spring.worldwire.controller.login
 */
@Controller
@RequestMapping("/login/linkedin")
public class LinkedInController {

    @Autowired
    private LinkedInHelper linkedInHelper;
    @Autowired
    private LinkedInConfig linkedInConfig;

    private static Logger log = LoggerFactory.getLogger(LinkedInController.class);

    @RequestMapping(value = "/auth")
    public void linkedinAuthorize(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String result = linkedInHelper.authorize();
        if(!StringUtils.isEmpty(result)){
            response.sendRedirect(result);
        }
    }

    @RequestMapping(value = "/callback")
    public String LoginProcess(HttpServletRequest request) {

        String code = request.getParameter("code");
        String state = request.getParameter("state");

        String tempState = linkedInConfig.getState();
        /*防止跨站攻击*/
        if (null == tempState || null == state || !tempState.trim().equalsIgnoreCase(state.trim())) {
            String ip = getIpAddress(request);
            log.error("state not equal origin state {},get state {},request ip={},check the ip to ensure safety",tempState,state,ip);
            return null;
        }
		/*判断第一步是否得到Code*/
        if (StringUtils.isEmpty(code)) {
            //登录失败处理
            log.error("login callback fail ,get empty code info");
            return null;
        } else {
            JSONObject tokenInfo = linkedInHelper.getAccessToken(code);
            if (null == tokenInfo || !tokenInfo.containsKey("access_token")) {
				/*获取Token失败处理*/
                //登录失败处理
                log.error("get invalide accessToken info  --> {}",tokenInfo);
                return null;
            } else {
				/*用Token获取用户信息*/
                String accessToken = tokenInfo.getString("access_token");
                if (StringUtils.isEmpty(accessToken)) {
					/*获取Token失败处理*/
                    //登录失败处理
                    return null;
                }
//                JSONObject result = this.getUserInfo("https://api.linkedin.com/v1/people/~",accessToken);
                JSONObject userInfo = linkedInHelper.getUserInfo(accessToken);
                if (!userInfo.containsKey("id")) {
					/*获取信息失败*/
                    return null;
                } else {
					/*获取信息成功*/
                    //保存用户
                    return userInfo.toJSONString();
                }
            }
        }
    }
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String doPostForLinkedIn(String strURL, String jsonParams) {
        String result = "";
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            // connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            //坑3 application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            System.out.println("helper===" + jsonParams + "===");
            try {
                out.print(jsonParams);
                out.flush();
            } finally {
                if (out != null) out.close();
            }
            // 处理响应数据
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } finally {
                if (in != null) in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
