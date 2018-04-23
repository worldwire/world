package com.spring.worldwire.controller.pay;

import com.spring.worldwire.utils.pay.alipay.AlipayCore;
import com.spring.worldwire.utils.pay.alipay.AlipayNotifyVO;
import com.spring.worldwire.utils.pay.alipay.TradeStatusEnum;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("unused")
@RequestMapping("/alipay/result")
public class AlipayController extends BaseResultController {

  private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

  @RequestMapping("/webCallBack")
  @ResponseBody
  public String webCallBack(HttpServletRequest request){
    try {
      AlipayCore alipayCore = new AlipayCore();
      AlipayNotifyVO notifyParams = alipayCore.getNotifyParams(request);
      if(notifyParams!=null){
        //todo 转换成你所需要的类型 然后进行处理
        if (TradeStatusEnum.TRADE_SUCCESS.equals(notifyParams.getTrade_status())) {//处理成功
          super.complateOrder();
          logger.info("[处理成功] orderid = "+ notifyParams.getOut_trade_no() );
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

}
