package com.spring.worldwire.controller.pay;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.worldwire.utils.pay.paypal.PayPalPaymentIntentEnum;
import com.spring.worldwire.utils.pay.paypal.PaypalCore;
import com.spring.worldwire.utils.pay.paypal.PaypalPaymentMethodEnum;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("unused")
@RequestMapping("/paypal/result")
@Controller
public class PaypalController extends BaseResultController {

  private static Logger logger = LoggerFactory.getLogger(PaypalController.class);

  @RequestMapping("/webCallBack")
  @ResponseBody
  public String webCallBack(HttpServletRequest request){
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
      logger.info("[paypal回调]  参数 name="+name +"   valueStr = "+valueStr);
      params.put(name, valueStr);
    }

    try {
      Payment payment = PaypalCore.executePayment(params.get("paymentId"), params.get("PayerID"));
      logger.info("[paypal回调] payment"+payment.toJSON());
      if(payment.getState().equals("approved")){
        return "success";
      }
    } catch (PayPalRESTException e) {
      logger.error(e.getMessage());
    }

    return "fail";
  }

  @RequestMapping("/cancel")
  public String webNotify(){
    return super.cancel();
  }


  /**
   * 支付账户
   * pengstraw-buyer@163.com
   * pws 11111111
   * @return
   */
  @RequestMapping("/paytest")
  @ResponseBody
  public String testPay(){
    try{
      Payment payment = PaypalCore.createPayment(
          1.00,
          "USD",
          PaypalPaymentMethodEnum.paypal,
          PayPalPaymentIntentEnum.sale,
          "payment description");
      String paymentId = payment.getId();

      logger.info("[paypal调用成功] payment"+payment.toJSON());
      for(Links links : payment.getLinks()){
        if(links.getRel().equals("approval_url")){
          return "redirect:" + links.getHref();
        }
      }

    }catch (Exception e){

    }

    return "ok";
  }

}
