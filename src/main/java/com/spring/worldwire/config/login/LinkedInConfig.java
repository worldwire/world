package com.spring.worldwire.config.login;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author luxun.
 * @Date 2018/5/11 11:01
 */
@Component
@PropertySource({"classpath:linkedinconfig.properties"})
public class LinkedInConfig {

    @Value("${client_ID}")
    private String clientId;
    @Value("${client_SECRET}")
    private String clientSecret;
    @Value("${redirectUrl}")
    private String redirectUri;
    @Value("${code}")
    private String code;
    @Value("${state}")
    private String state;
    @Value("${authURL}")
    private String baseUrl;
    @Value("${accessTokenURL}")
    private String accessTokenUrl;
    @Value("${userInfoURL}")
    private String userInfoUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
