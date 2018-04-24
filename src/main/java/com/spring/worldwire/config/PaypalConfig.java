package com.spring.worldwire.config;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
//todo 需要修改配置
public class PaypalConfig extends BaseConfig {


    public static final String PAYPAL_MODE = "sandbox";//production

    public static final String PAYPAL_CLIENT_ID = "AWx0sioFaaocojFMpb0dcpGLiaccGeuXMOwIZgGIwXhriOPzt9LUDCrdyNZiEKF0yhszY_UX9qbXjL95";

    public static final String PAYPAL_CLIENT_SECRET = "EGhMMdsuybd5-6bPO2EILPes-gdAwyvgnfOwg-JJ4d21abpDlHjBLRJs_hsmeDkAa-kUr4UuBcUms6ii";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String NOTIFY_URL = "/paypal/result/webCallBack";

    // 取消
    public static final String CANAEL_URL = "/paypal/result/cancel";

    public static Map<String,String> paypalSdkConfig(){
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", PAYPAL_MODE);
        return sdkConfig;

    }
}
