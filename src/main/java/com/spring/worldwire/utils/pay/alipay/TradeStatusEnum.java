package com.spring.worldwire.utils.pay.alipay;

public enum TradeStatusEnum {

  TRADE_FINISHED("TRADE_FINISHED"),TRADE_SUCCESS("TRADE_SUCCESS"),TRADE_CLOSED("TRADE_CLOSED");

  private String name;

  TradeStatusEnum(String name) {
    this.name = name;
  }

  public static TradeStatusEnum getTradeStausByName(String name){
    return TradeStatusEnum.valueOf(name);
  }
}
