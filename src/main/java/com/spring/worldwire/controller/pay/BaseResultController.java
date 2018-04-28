package com.spring.worldwire.controller.pay;

import com.spring.worldwire.dao.TradeOrderDao;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理公共的业务逻辑区域
 */
public class BaseResultController {

  @Autowired
  private TradeOrderDao tradeOrderDao;

  void complateOrder(String thirdOrderNum,ThirdPayEnum thirdPayEnum){
    TradeOrder tradeOrder = tradeOrderDao.getByThirdTradeNum(thirdOrderNum,thirdPayEnum);

    complateOrder(thirdOrderNum,null,thirdPayEnum);
  }

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
