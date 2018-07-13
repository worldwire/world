package com.spring.worldwire.config.login;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.utils.HttpReqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luxun.
 * @date 2018/5/11 11:34
 */
@Component
public class LinkedInHelper {

    @Autowired
    private LinkedInConfig linkedInConfig;

    private static final String AUTHORIZATION_CODE = "authorization_code";

    private static Logger log = LoggerFactory.getLogger(LinkedInHelper.class);

    public String authorize() {
        try {
            String baseUrl = linkedInConfig.getBaseUrl();
            String redirectUriCode = URLEncoder.encode(linkedInConfig.getRedirectUri(), "utf-8");//格式化url
            baseUrl += "?client_id=" + linkedInConfig.getClientId() + "&redirect_uri=" + redirectUriCode + "&response_type=" + linkedInConfig.getCode() + "&scope=" + "" + "&state=" + linkedInConfig.getState() + "&format=json";
            return baseUrl;
        } catch (IOException e) {
            log.error("encode URI {} error, error message:{} ",linkedInConfig.getRedirectUri(),e);
            return null;
        }
    }

    public JSONObject getAccessToken(String code) {
        try {
            String baseUrl = linkedInConfig.getAccessTokenUrl();
            String redirectUriCode = URLEncoder.encode(linkedInConfig.getRedirectUri(), "utf-8");//对应坑1
            baseUrl += "?client_id=" + linkedInConfig.getClientId() + "&client_secret=" + linkedInConfig.getClientSecret() + "&code=" + code + "&grant_type=" + AUTHORIZATION_CODE + "&redirect_uri=" + redirectUriCode + "&format=json";
            String resultJson = HttpReqUtil.reqHttpJson(baseUrl);
            return JSONObject.parseObject(resultJson);
        } catch (Exception e) {
            log.error("get access token error ,cause by {}",e);
            return null;
        }
    }
    public JSONObject getUserInfo(String accessToken) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("Accept","application/json");
        map.put("Content-Type","application/json");
        map.put("Authorization","Bearer "+ accessToken);
        String baseUrl = linkedInConfig.getUserInfoUrl();
        try {
            String resultJson = HttpReqUtil.sendHttpGetRequest(baseUrl+"?"+"format=json",map);
            return JSONObject.parseObject(resultJson);
        }catch (Exception e){
            log.error("http request error ,request uri ={},error message is {}",baseUrl,e);
        }
        return null;
    }
}
