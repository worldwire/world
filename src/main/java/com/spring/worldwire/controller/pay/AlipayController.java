package com.spring.worldwire.controller.pay;

import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.utils.pay.alipay.AlipayCore;
import com.spring.worldwire.utils.pay.alipay.AlipayNotifyVO;
import com.spring.worldwire.utils.pay.alipay.TradeStatusEnum;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@SuppressWarnings("unused")
@RequestMapping("/alipay/result")
@Controller
public class AlipayController extends BaseResultController {

  private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

  @RequestMapping("/webCallBack")
  @ResponseBody
  public String webCallBack(HttpServletRequest request){
    try {
      AlipayCore alipayCore = new AlipayCore();
      AlipayNotifyVO notifyParams = alipayCore.getNotifyParams(request);
      if(notifyParams!=null){
        if (TradeStatusEnum.TRADE_SUCCESS.equals(notifyParams.getTrade_status())) {//处理成功

          logger.info("[处理成功] orderid = "+ notifyParams.getOut_trade_no() );
          TradeOrder tradeOrder = tradeOrderservice.getByTradeNum(notifyParams.getTrade_no(),ThirdPayEnum.ALIPAY);
          tradeOrder.setThirdOrderNum(notifyParams.getTrade_no());

          super.complateOrder(tradeOrder);
        }
        return "success";
      }else{
        //数据校验失败 返回为空
        logger.info("[支付宝处理] 校验失败" );
        return "fail";
      }

    }catch (Exception e){
      return "fail";
    }


  }

  @RequestMapping("/webNotify")
  public String webNotify(){
    return super.notifyOrder();
  }

}
