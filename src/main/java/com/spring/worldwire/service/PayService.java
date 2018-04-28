package com.spring.worldwire.service;

import com.spring.worldwire.model.TradeOrder;

public interface PayService {

	/**
	 * 调用支付的接口，会返回支付所需要的一个页面。
	 * @param tradeOrder
	 * @return
	 */
	String doPayWay(TradeOrder tradeOrder);

}
