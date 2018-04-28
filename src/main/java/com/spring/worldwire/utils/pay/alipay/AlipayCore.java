package com.spring.worldwire.utils.pay.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.spring.worldwire.config.AlipayConfig;
import com.spring.worldwire.config.BaseConfig;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class AlipayCore {

  private static Logger logger = LoggerFactory.getLogger(AlipayCore.class);

  public String pagePay(AlipayBean alipayBean) throws Exception{
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GET_WAY_URL,AlipayConfig.ALIPAY_PARTNER,
        AlipayConfig.ALIPAY_PRIVATEKEY,"json",AlipayConfig.ALIPAY_INPUT_CHARSET,
        AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.ALIPAY_SIGN_TYPE);

    //设置请求参数
    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    alipayRequest.setReturnUrl(BaseConfig.DOMAIN+AlipayConfig.return_url);
    alipayRequest.setNotifyUrl(BaseConfig.DOMAIN+AlipayConfig.notify_url);

    //商户订单号，商户网站订单系统中唯一订单号，必填
    String out_trade_no = alipayBean.getOut_trade_no();
    //付款金额，必填
    String total_amount =  alipayBean.getTotal_amount();
    //订单名称，必填
    String subject =  alipayBean.getSubject();
    //商品描述，可空
    String body =  alipayBean.getBody();

    if(payCheck(alipayBean)){
      String content = "{\"out_trade_no\":\""+ out_trade_no +"\","
          + "\"total_amount\":\""+ total_amount +"\","
          + "\"subject\":\""+ subject +"\","
          + "\"body\":\""+ body +"\","
          + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
      alipayRequest.setBizContent(content);

      logger.info("[支付宝支付]  content = "+ content);

      return alipayClient.pageExecute(alipayRequest).getBody();

    }
    return null;

  }




  /**
   * 返回回调
   * @param request 回调请求
   * @return 返回AlipayNotifyVO结果 如果校验失败返回空
   */
  public AlipayNotifyVO getNotifyParams(HttpServletRequest request){
    //获取支付宝POST过来反馈信息
    Map<String,String> params = new HashMap<>();
    Map<String,String[]> requestParams = request.getParameterMap();
    for (String name : requestParams.keySet()) {
      String[] values = requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i]
            : valueStr + values[i] + ",";
      }
      //乱码解决，这段代码在出现乱码时使用
/*      try {
        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException e) {
        logger.error("[支付宝回调] 数据存解析异常",e);
      }*/
      logger.info("支付回调   name="+name +"   valueStr = "+valueStr);
      params.put(name, valueStr);
    }
    if(params.size()>0){
      boolean checkSign = checkSign(params);
      if(checkSign){
        AlipayNotifyVO alipayNotifyVO = new AlipayNotifyVO();
        alipayNotifyVO.setOut_trade_no(params.get("out_trade_no"));
        alipayNotifyVO.setTrade_no(params.get("trade_no"));
        alipayNotifyVO.setTrade_status(TradeStatusEnum.getTradeStausByName(params.get("trade_status")));
        return alipayNotifyVO;
      }
      logger.info("[支付宝回调] 数据校验失败 order_id="+params.get("out_trade_no"));
    }else{
      logger.info("[支付宝回调] 参数为空");
    }
    return null;
  }

  /**
   * 支付参数校验
   * @param alipayBean 需要支付的参数
   * @return 返回加盐结果
   */
  private boolean payCheck(AlipayBean alipayBean){
    return(StringUtils.isBlank(alipayBean.getOut_trade_no())
        ||StringUtils.isBlank(alipayBean.getTotal_amount())
        ||StringUtils.isBlank(alipayBean.getSubject()));
  }

  /**
   * 签名验证
   * @param params 请求参数
   * @return 返回结果集
   */
  private boolean checkSign(Map<String,String> params){
    try {
      return AlipaySignature
          .rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.ALIPAY_INPUT_CHARSET, AlipayConfig.ALIPAY_SIGN_TYPE); //调用SDK验证签名
    } catch (Exception e) {
      logger.error("[支付宝回调] 数据校验异常 order_id="+params.get("out_trade_no"),e);
    }
    return false;
  }

}
