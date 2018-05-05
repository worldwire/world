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


@SuppressWarnings("unused")
@RequestMapping("/alipay/result")
@Controller
public class AlipayController extends BaseResultController {

  private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

  @RequestMapping("/webCallBack")
  @ResponseBody
  public String webCallBack(HttpServletRequest request){
    String orderNum = "";
    try {
      AlipayCore alipayCore = new AlipayCore();
      AlipayNotifyVO notifyParams = alipayCore.getNotifyParams(request);
      if(notifyParams!=null){
        orderNum = notifyParams.getOut_trade_no();
        if (TradeStatusEnum.TRADE_SUCCESS.equals(notifyParams.getTrade_status())) {//处理成功

          TradeOrder tradeOrder = tradeOrderservice.getByTradeNum(notifyParams.getOut_trade_no(),ThirdPayEnum.ALIPAY);
          logger.info("[支付宝支付回调] orderNum = {} thirdNum = {}",notifyParams.getOut_trade_no(),notifyParams.getTrade_no() );
          if(tradeOrder.getId()!=null){
            tradeOrder.setThirdOrderNum(notifyParams.getTrade_no());
            super.complateOrder(tradeOrder);
          }else{
            logger.info("[支付宝支付回调] 未查的数据 orderNum = {} ", notifyParams.getOut_trade_no());
          }

        }
        return "success";
      }else{
        //数据校验失败 返回为空
        logger.info("[支付宝处理] 校验失败" );
        return "fail";
      }

    }catch (Exception e){
      logger.error("[支付宝回调] 支付发生异常 orderNum = {}",orderNum,e);
      return "fail";
    }


  }




  @RequestMapping("/webNotify")
  public String webNotify(String orderNUm){

    return super.notifyOrder();
  }

}
