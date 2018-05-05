package com.spring.worldwire.controller.pay;

import com.spring.worldwire.enums.PayStatusEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.service.TradeOrderservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 处理公共的业务逻辑区域
 */
public class BaseResultController {
  private static Logger logger = LoggerFactory.getLogger(BaseResultController.class);
  @Autowired
  TradeOrderservice tradeOrderservice;

  void complateOrder(TradeOrder tradeOrder){

    tradeOrder.setStatus(PayStatusEnum.SUCCESS);
    tradeOrder.setSuccessTime(new Date());
    logger.info("[支付完成] 查找数据修改状态 orderNum={}",tradeOrder.getOrderNum());
    int i = tradeOrderservice.updateByPrimaryKeySelective(tradeOrder);
    if(i>0){
      logger.info("[支付完成] 支付完成更新数据库 orderNum = {}" ,tradeOrder.getOrderNum());
      complateOrder(tradeOrder.getOrderNum(),null,tradeOrder.getThirdType());
    }
  }

  //todo 执行支付之后的逻辑
  void complateOrder(String thirdOrderNum,String orderNum,ThirdPayEnum thirdPayEnum){

  }


  String notifyOrder(){


    return "index";
  }

  String cancel(){
    return "cancel";
  }


  String saveThirdPay(){
    return "";
  }

}
