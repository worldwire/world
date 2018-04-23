package com.spring.worldwire.config;

@SuppressWarnings("unused")
//todo 需要修改配置
public class AlipayConfig extends BaseConfig {

    public static final String ALIPAY_PARTNER = "2016091600522471";
    public static final String ALIPAY_INPUT_CHARSET = "UTF-8";
    public static final String ALIPAY_PRIVATEKEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMJOAN0+a7/glU5HAoYxp0ycXArmlemCBvw2Klxn65iWqSSavJrTTMBS9mEfZBKXRCzOt1CXrT4JJ9b5pqbnQ44IohJW8P96nzqEVsymtNaNNLyJz2dpxlP1dY2wBcUM6MErK3pa46bsl7WO79T/TuPEO9QecADn2Oz5Engal2jOJvU9wtEIltct8qaWOV8QPT7Pu7xtH1/6lQovytv/udbEBbLEsjfwo6uLpvidqDaxGd+eaPA0HXysJJUzRo6uduLVkvVE0GSral8MNY0EFJ4oSeTmqLZWzfHGEMpVLcOicr8cpNC9O2edPvU9p4jPN5LK/yMBl2eW9oPf9mrUwvAgMBAAECggEAUoKPa3S73yjU46dPJWgbtnDpPeUx4mCckFCvrrxr9d6i4RgAzU9ty0SuQGEI5Bp8+ateYVzAdSg99x4TAuCfCrGggZ1hgzwkUnFa1o9il4PFC1REnZL9x1qAma7uymtFABEuKZOhPYPmmfmlU3yUzwKqOy4my9C8Oa2ZX3ImjMXIUDRaJOEU10YJnCCXkhjC3ABeqCjjp8nMpPRPH33Lmgw4oPwkwnBjdlH66ZzXfAgTBgkBCXRIHwTAOFxO2lphbo0AVAG2r9RjunWp3GJUgYKqwBaKqk89nJmjbdwC52SB9UbAX8N8bTdF+hMUFb9bRB6937O4z19thlUVMUlNoQKBgQDmxw3iLJKEk3Mo6k/xjLxxiKbvWAqIEXzokdzcEhZSYTBlgMkTRpTEPmI9d6a6Q5PKqoF7inRTmbsuJNjfv2OmDFkJlfrmb1pKv6CfP49kdsMBHEMycU9xCqkUiQxJ+yaw2FrTKQepPwCOOPIEOly4//74i4WDiLxXP2c+fUKh3wKBgQCbdfpWsZ4PTjTdAKnl8IxxFsPbnvrSasLNLHPX4Ti0Y6bZJzm/EwbbONSDRiN2SEvlG74rDmU9sOUnoZW+b1V07XeQstwAEQswVZS/k3GD/mS7WOZsurZwGPO4UWqnLjcJAFbiFCdUG6opgzwhfP1TrpuYAK/gcvLZcy07weq/sQKBgD1DnqXqW0MiubGk8nAXgndJZbeoUbNpoRVEk7NAgfiAoI5xr++UDy5BuWbD+tj706I7vYAEc3k2GIDZe1F6x4NvR7vJuv+tk/TABm4AO5raBE6pKBWeCw7hW519/GsvZ0gPR51DCSue9BDNgUIqFC9wlkl5EIqopActZ8UpD6VVAoGAFAmFxazJAZID8ERIobF6eI0Fm4YHMB3Ak+9Z0wZdZ6q8c5rBr6YFKTZO9ciRg4pX9eS4gxYs6GGnd481HMF+0z5UPds/zeZkecteC782T1v3ocoYkQUTxKkCqMwdogSr5UYc7LDKTOXv7b/TfUF96U5FEbds89H9CRf2SZB58FECgYAolOLy7TEIuDWzC/ykUmTFEDMChq0o7qfx0QMPGix4LSlcXuVVO1RRDR4mVI0NaG/vlxrPGWrWbQkl9pNk9dtpAtLCQmt+j3MefboJ2jw2nivYIIbvWtSPOrGspr19vPovMIDJvJ88p5u7U0KhvIWsARz43r533OUwKAym+DitrQ==";
    public static final String ALIPAY_LOG_PATH = "logs/pay.out";
    public static final String ALIPAY_SIGN_TYPE = "RSA2";
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjCTgDdPmu/4JVORwKGMadMnFwK5pXpggb8NipcZ+uYlqkkmrya00zAUvZhH2QSl0QszrdQl60+CSfW+aam50OOCKISVvD/ep86hFbMprTWjTS8ic9nacZT9XWNsAXFDOjBKyt6WuOm7Je1ju/U/07jxDvUHnAA59js+RJ4Gpdozib1PcLRCJbXLfKmljlfED0+z7u8bR9f+pUKL8rb/7nWxAWyxLI38KOri6b4nag2sRnfnmjwNB18rCSVM0aOrnbi1ZL1RNBkq2pfDDWNBBSeKEnk5qi2Vs3xxhDKVS3DonK/HKTQvTtnnT71PaeIzzeSyv8jAZdnlvaD3/Zq1MLwIDAQAB";

    //public static final String ALIPAY_SELLER_EMAIL = "yqmghh9976@sandbox.com";
    //public static final String ALLINPAY_MERCHANTID = "2088102175846354";
    //public static final String GET_WAY_URL = "https://openapi.alipay.com/gateway.do";
    public static final String GET_WAY_URL = "https://openapi.alipaydev.com/gateway.do";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String notify_url = "/alipay/result/webCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String return_url = "/alipay/result/webNotify";
}
