package com.spring.worldwire.config;

@SuppressWarnings("unused")
//todo 需要修改配置
public class AlipayConfig extends BaseConfig {

    public static final String ALIPAY_PARTNER = "2016091600522471";
    public static final String ALIPAY_INPUT_CHARSET = "UTF-8";
    public static final String ALIPAY_PRIVATEKEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxpKxk+5LqCNibHXYwBqvZNEh/OJvHMOaTr0uk/DkxmZsgdLVGukYy3N8PyWPZ0ioJP64IDUZwI6wEqjoviWYETTns0Emz186t7kavnNrVW8tOevXmKE2CD3hFkaKzQnzRLbfk2/5D1wDFnRGsiHB5eB3ErR+u6i2C88Nlnd+t3D7LiqNWFCH9XyPRqj/T9+BHKOEfWSWq9Sxa0+ZfpDH5iaNfkBYmNodTZ9xG3vd6Q417M2NdI31EI5XoF1WGiUtxugSnxKONQnlGzuihd3ri8iMe8S6bvQWmmEg0PmfShGd6if6sQ3jnDsZH+xvIxSql8Y1BtkThfECHx8xZ8+ILAgMBAAECggEAdfYy4C/KZAyRwZczxzp23RoB1jFCCGiphIJQCfPPCufEgi3dvmStftL6cKI74wXTEicuKnyePJMDhGmBj9V+X/bkMyQw/PKedRMX7z+KdUmqXKf8J+S1a1tYc2H9F2pcNg9U4O1RD71y7saZWOeVQKQJqHOJ6X3p0N6L7yuBwBG3zFESOEVvv7YR/Pu/haL+pTr3Da7PZFkGwAZfFXoAP7QTQlm9bHALkk5YjrsYfB4CeTFVn7icJDlSXbKFIsKKkoDtRPjzm0A79C4ynzjZNGGAgd7S6HV7N8a8IFYqPWW1pUpEI1Ut0o+BEyVEE6cLfLXu83EfueeH6X93o6FAAQKBgQDlYD+NV/hSkf6ATdpofSJjoThq4kpl1KwIQk2gg+QjJ0H3D+7kw94uASo2UdaoPk65L7X0pLkjIvIk+qVDAQo8uTa3jbv3K4WPNcIpAz6GlyCKWnISbN4Z7h8rF3CIdEq2Rst0xm51rSemUDJM8tW2uJhv+5E2cjurwFVJ4izCCwKBgQDGQzj5+OkY4QvEmQIOl1UWku3sws17k2M5Lyjbu7s5XwXBD90N72wMn5ENDZ4PCZBmZCODjMidJlrwSITZqOGiYHd9j+3tnIvOXQ/6qfwUbMq0AFTMdMCgvCllEpQmcV+HMf3ofxYwjWKbOEisniOlt8wedcZabKUSon9GGelgAQKBgBsSdA3DnzxnnMkZ5zhD5KYrphP/Z1aDdlZ3NOBIEMoRXRjC2ahIcdWuL4Q/vn0W9YWn2urn8i3ydu/kYVL6MXIfThT19oRir53Kc4MRrfSGq8GIkUQZc1FOKIGWf6KrTxIaYvMCZJEhAbgAaR9p3c12Fg/JekcZzm8kASz6M9wnAoGBAIpRd7ydtTHQP7+G5/J6DbjUBP264lSmfQD2L9Es5e+epYsNoDEKqh0cbbvnli/zgjSNBGHQpF1O1v7v+Q9JTTFVj8ef1GSDZN4x+uXsFZvCUqDF4mNJyOHapB6JmaZATl8pt3SuxTJWUUbtO3aCVWhWFCfkDuAjD/8yMw0bIsABAoGAe0/OSPERHhr/I9G6QvswyVWt+Xfr44y6FZqBpJzKgXe6Gu/Om5r7W+hEkzGaVCl/RNWCTf2AB6lSXoGvpO1ZTSC8gnFtIqVzZu7zyJPmZskW3iGB6+9U3wAkbWINJaT68UMSRNI4CrZTOLl5Wgfi9L2fNPGa0d6hNZ0zHFIea2E=";
    public static final String ALIPAY_LOG_PATH = "logs/pay.out";
    public static final String ALIPAY_SIGN_TYPE = "RSA2";
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApN1eq3bpERL/lOkdqElMxGmJFpTtkbeN4XRvc6VxotB270fk26UCC5c0BYEq5MxjG0W9lTWGloQMRgoeC3U4g4juFQ4faTSTRUjyUrvDgEOhsARaUmYmLZvxpXmRC/ysLfl07/+C6b78UlltdYkvckKLTBwIytiw5baQskzaxHsMyjNbMWchdvm9Fose9jzZzROgJAhrpXMwRGqE4ycItytEBzVZdsftmNUmEIjrEq0SGVNqvVraqHiVlICGyO42GQ6QK/E05LswPjaLbL4zd8Ighygar7PtV3y6ssC1mYdXIO1bZZwzB2oTTXfk7Qg0tkBnlwwKOa8zNTuWH8X+twIDAQAB";

    //public static final String ALIPAY_SELLER_EMAIL = "yqmghh9976@sandbox.com";
    //public static final String ALLINPAY_MERCHANTID = "2088102175846354";
    //public static final String GET_WAY_URL = "https://openapi.alipay.com/gateway.do";
    public static final String GET_WAY_URL = "https://openapi.alipaydev.com/gateway.do";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String notify_url = "/alipay/result/webCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String return_url = "/alipay/result/webNotify";
}
