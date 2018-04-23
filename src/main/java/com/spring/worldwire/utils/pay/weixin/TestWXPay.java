package com.spring.worldwire.utils.pay.weixin;

import com.spring.worldwire.config.WXPayConfigImpl;

import java.util.HashMap;
import java.util.Map;
//TODO 测试类 需要以后修改
public class TestWXPay {

    private WXPay wxpay;
    private WXPayConfigImpl config;
    private String out_trade_no;
    private String total_fee;

    public TestWXPay() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
        total_fee = "1";
        // out_trade_no = "201701017496748980290321";
        out_trade_no = "201613091059590100003433-asd002";
    }

    /**
     * 扫码支付  下单
     */
    public void doUnifiedOrder() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "NATIVE");
        data.put("product_id", "12");
        // data.put("time_expire", "20170112104120");

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doOrderClose() {
        System.out.println("关闭订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.closeOrder(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOrderQuery() {
        System.out.println("查询订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.orderQuery(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOrderReverse() {
        System.out.println("撤销");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.reverse(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 长链接转短链接
     * 测试成功
     */
    public void doShortUrl() {
        String long_url = "weixin://wxpay/bizpayurl?pr=etxB4DY";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("long_url", long_url);
        try {
            Map<String, String> r = wxpay.shortUrl(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退款
     * 已测试
     */
    public void doRefund() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());

        try {
            Map<String, String> r = wxpay.refund(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询退款
     * 已经测试
     */
    public void doRefundQuery() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_refund_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.refundQuery(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对账单下载
     * 已测试
     */
    public void doDownloadBill() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("bill_date", "20161102");
        data.put("bill_type", "ALL");
        try {
            Map<String, String> r = wxpay.downloadBill(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGetSandboxSignKey() throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(data, config.getKey());
        data.put("sign", sign);
        WXPay wxPay = new WXPay(config);
        String result = wxPay.requestWithoutCert("https://api.mch.weixin.qq.com/sandbox/pay/getsignkey", data, 10000, 10000);
        System.out.println(result);
    }


//    public void doReport() {
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("interface_url", "20160822");
//        data.put("bill_type", "ALL");
//    }

    /**
     * 小测试
     */
    public void test001() {
        String xmlStr="<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx273fe72f2db863ed]]></appid>\n" +
                "<mch_id><![CDATA[1228845802]]></mch_id>\n" +
                "<nonce_str><![CDATA[lCXjx3wNx45HfTV2]]></nonce_str>\n" +
                "<sign><![CDATA[68D7573E006F0661FD2A77BA59124E87]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<openid><![CDATA[oZyc_uPx_oed7b4q1yKmj_3M2fTU]]></openid>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<total_fee>1</total_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<transaction_id><![CDATA[4008852001201608221983528929]]></transaction_id>\n" +
                "<out_trade_no><![CDATA[20160822162018]]></out_trade_no>\n" +
                "<attach><![CDATA[]]></attach>\n" +
                "<time_end><![CDATA[20160822202556]]></time_end>\n" +
                "<trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "<cash_fee>1</cash_fee>\n" +
                "</xml>";
        try {
            System.out.println(xmlStr);
            System.out.println("+++++++++++++++++");
            System.out.println(WXPayUtil.isSignatureValid(xmlStr, config.getKey()));
            Map<String, String> hm = WXPayUtil.xmlToMap(xmlStr);
            System.out.println("+++++++++++++++++");
            System.out.println(hm);
            System.out.println(hm.get("attach").length());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testUnifiedOrderSpeed() throws Exception {
        TestWXPay dodo = new TestWXPay();

        for (int i=0; i<100; ++i) {
            long startTs = System.currentTimeMillis();
            out_trade_no = out_trade_no+i;
            dodo.doUnifiedOrder();
            long endTs = System.currentTimeMillis();
            System.out.println(endTs-startTs);
            Thread.sleep(1000);
        }

    }


    public String createWebSign(String appsecret,String partnerkey,String prepay_id,String nonce_str,String appid) throws Exception {
        Map<String, String> finalpackage = new HashMap<String, String>();
        finalpackage.put("appId", appid);
        finalpackage.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", "prepay_id="+prepay_id);
        finalpackage.put("signType", "MD5");
        WXPayUtil.generateSignedMap(finalpackage, partnerkey);
        return finalpackage.get(WXPayConstants.FIELD_SIGN);
    }


    /**
     * 页面js回调 直接返回页面
     * @param orderId
     * @return
     */
    public String getCallPay(String orderId,String appid,String timestamp,String nonce_str,String prepay_id,String webSign) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append("function callpay(){\n");

        sb.append("WeixinJSBridge.invoke('getBrandWCPayRequest',{\n");
        sb.append("\"appId\" : \""+appid+"\",\"timeStamp\" : \""+timestamp+"\", \"nonceStr\" : \""+nonce_str+"\", \"package\" : \"prepay_id="+prepay_id+"\",\"signType\" : \"MD5\", \"paySign\" : \""+webSign+"\"\n");
        sb.append("},function(res){\n");
        sb.append("WeixinJSBridge.log(res.err_msg);\n");
        //sb.append("alert(res.err_code +\"--\"+ res.err_desc +\"---\"+ res.err_msg);\n");
        sb.append("if(res.err_msg == \"get_brand_wcpay_request:ok\"){  \n");
        /*sb.append("alert(\"微信支付成功!\");\n ");*/
        sb.append("window.location.href=\"../../../success.jsp?orderId="+orderId+"\"\n");
        sb.append("}else if(res.err_msg == \"get_brand_wcpay_request:cancel\"){  \n");
        sb.append("alert(\"用户取消支付!\");  \n");
        sb.append("}else{  \n");
        sb.append("alert(\"支付失败!\"); \n");
        sb.append("}  \n");
        sb.append("});\n");
        sb.append("}\n");
        sb.append("</script>\n");


        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--------------->");
        TestWXPay dodo = new TestWXPay();
        // dodo.doGetSandboxSignKey();

        // dodo.doUnifiedOrder();
        // dodo.doOrderQuery();
        // dodo.doDownloadBill();
        // dodo.doShortUrl();
        // dodo.test001();
        // dodo.doOrderQuery();
        // dodo.doOrderClose();
        // dodo.doRefund();
        // dodo.doRefundQuery();
        // dodo.doOrderReverse();
        // dodo.test001();
         dodo.testUnifiedOrderSpeed();
        //{nonce_str=R5dzKmkVr4UBq37S, code_url=weixin://wxpay/bizpayurl?pr=utHaQq0, appid=wxab8acb865bb1637e, sign=1B5077999E140BD76AAD7B0B10224A630C72F9AE77B07C63B27C7A74F66C2B0A, trade_type=NATIVE, return_msg=OK, result_code=SUCCESS, mch_id=11473623, return_code=SUCCESS, prepay_id=wx21123526130170f153264a580228770485}
        //dodo.doOrderQuery();
        //dodo.doOrderReverse();
        //dodo.doOrderQuery();
        //dodo.doOrderReverse();
        //dodo.doOrderQuery();



        System.out.println("<---------------"); // wx2016112510573077
    }
}
