package com.spring.worldwire.controller.pay;

/**
 * 处理公共的业务逻辑区域
 */
public class BaseResultController {

  void complateOrder(){

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
