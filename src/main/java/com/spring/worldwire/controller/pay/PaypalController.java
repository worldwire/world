package com.spring.worldwire.controller.pay;

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
      return "fail";
  }

  @RequestMapping("/webNotify")
  public String webNotify(){
    return super.notifyOrder();
  }

}
