package com.spring.worldwire.constants;

/**
 * Created by luxun on 2018/4/24.
 */
public class Constants {

    //---------------------------邮件相关常量--------------------------------------------------
    //发送验证码邮箱的账号
    public static final String MAIL_SENDT_ACCOUNT = "luxun_helloworld@163.com";
    //发送验证码邮箱的密码
    public static final String MAIL_SEND_PASSWORD = "code1234";
    //发送验证码的邮件的标题
    public static final String MAIL_SEND_TITLE = "找回密码";
    //邮箱的host地址
    public static final String MAIL_SMTP_HOST  = "smtp.163.com";
    // 发送邮件的模板页面
    public static final String MAIL_SEND_TEMPLATE = "templates/mail/mail_send_template.html";
    //发送邮件的模板页面
    public static final String MAIL_TEMPLATE = "mail/mail_send_template";
    //找回密码邮件里面url的模板
    public static final String MAIL_ADDRESS_PREFIX = "http://www.baidu.com";

    //---------------------------------缓存相关常量----------------------------------------

    //邮件有效期
    public static final String CACHE_MAIL_VALID_PREFIX = "mail_valid_";

    public static final String CACHE_SIGN_KEY = "cache_sign_key_"
;


}
