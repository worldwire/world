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
    public static final String MAIL_SEND_TEMPLATE = "static/mail/mail_send_template.html";
    // 找回密码的模板页面
    public static final String MAIL_PASSWORD_TEMPLATE = "static/mail/mail_reset_password_template.html";
    //激活账号
    public static final String MAIL_ACTIVE_USER_PREFIX = "http://www.theworldwire.cn/register/active";
    //找回密码邮件里面url的模板
    public static final String MAIL_MODIFY_ADDRESS_PREFIX = "http://www.theworldwire.cn/usercenter/toModify";
    //邮件有效期
    public static final String CACHE_MAIL_VALID_PREFIX = "mail_valid_";
    //邮件链接失效时间,10分钟有效
    public static final int MAIL_CODE_INVALIDATE_TIME = 10 * 60 * 1000;

    //----------------------------------------签到相关常量--------------------------------------
    //签到缓存的key前缀
    public static final String CACHE_SIGN_KEY = "cache_sign_key_";

    public static final String CACHE_FREE_LOOK_UP = "cache_free_lookup_";

    public static final String CACHE_SHARE_LOOK_UP = "cache_share_lookup_";

    //----------------------------------------业务相关字段--------------------------------------
    public static final String USER_COOKIES_NAME = "userInfo";

    public static final String USER_SESSION = "userInfo";
    public static final String USER_ID_SESSION = "userId";
    public static final String ADMIN_USER_SESSION = "adminUser";

    //----------------------------------------第三方登录相关字段--------------------------------------
    public static final String WECHAT_LOGIN_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";

    public static final String WECHAT_STATE = "worldwire";
}
