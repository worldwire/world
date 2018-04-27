package com.spring.worldwire.service;

import com.spring.worldwire.model.PayModel;

public interface PayService {

	/**
	 * 调用支付的接口，会返回支付所需要的一个页面。
	 * @param payModel
	 * @return
	 */
	String doPayWay(PayModel payModel);

}
