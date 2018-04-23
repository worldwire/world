package com.spring.worldwire.utils.pay.alipay;

@SuppressWarnings("unused")
public class AlipayNotifyVO {

  private String out_trade_no;
  private String total_amount;
  private String trade_no;
  private TradeStatusEnum trade_status;


  public String getOut_trade_no() {
    return out_trade_no;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public String getTotal_amount() {
    return total_amount;
  }

  public void setTotal_amount(String total_amount) {
    this.total_amount = total_amount;
  }

  public String getTrade_no() {
    return trade_no;
  }

  public void setTrade_no(String trade_no) {
    this.trade_no = trade_no;
  }

  public TradeStatusEnum getTrade_status() {
    return trade_status;
  }

  public void setTrade_status(TradeStatusEnum trade_status) {
    this.trade_status = trade_status;
  }

  @Override
  public String toString() {
    return "AlipayNotifyVO{" +
        "out_trade_no='" + out_trade_no + '\'' +
        ", total_amount='" + total_amount + '\'' +
        ", trade_no='" + trade_no + '\'' +
        ", trade_status=" + trade_status +
        '}';
  }
}
