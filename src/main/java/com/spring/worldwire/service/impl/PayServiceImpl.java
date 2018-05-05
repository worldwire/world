package com.spring.worldwire.service.impl;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.spring.worldwire.dao.TradeOrderDao;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.service.PayService;
import com.spring.worldwire.utils.JsUtil;
import com.spring.worldwire.utils.pay.alipay.AlipayBean;
import com.spring.worldwire.utils.pay.alipay.AlipayCore;
import com.spring.worldwire.utils.pay.paypal.PayPalPaymentIntentEnum;
import com.spring.worldwire.utils.pay.paypal.PaypalCore;
import com.spring.worldwire.utils.pay.paypal.PaypalPaymentMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
	private static Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

	@Autowired
	private TradeOrderDao tradeOrderDao;

	private static String errorUrl = "";


	@Override
	public String doPayWay(TradeOrder tradeOrder) {
		String toWeb = null;
		int insert = tradeOrderDao.insert(tradeOrder);
		if(insert==0){
		  logger.info("[支付]保存订单出现异常 orderNum={}", tradeOrder.getOrderNum());
		  return JsUtil.webOpenNewWindow(errorUrl);
		}
		switch (tradeOrder.getThirdType()){
			case PAY_PAL:
				toWeb = payPayPal(tradeOrder);
				break;
			case ALIPAY:
				toWeb = payAlipay(tradeOrder);
				break;
			default:
				logger.info("[支付]出现异常问题 orderNum={}", tradeOrder.getOrderNum());
		}
		if(StringUtils.isNotBlank(tradeOrder.getThirdOrderNum())){
			int update = tradeOrderDao.updateByPrimaryKeySelective(tradeOrder);
			if(update==0){
				logger.info("[支付]更新订单出现异常 orderNum={}", tradeOrder.getOrderNum());
				return JsUtil.webOpenNewWindow(errorUrl);
			}
		}
		if(StringUtils.isBlank(toWeb)){
		  return JsUtil.webOpenNewWindow(errorUrl);
		}

		return toWeb;
	}



	private String payAlipay(TradeOrder tradeOrder){
		AlipayBean alipayBean = new AlipayBean();
		alipayBean.setBody(tradeOrder.getTradeDetail());
		alipayBean.setOut_trade_no(tradeOrder.getOrderNum());
		alipayBean.setTotal_amount(tradeOrder.getAmount().toString());
		alipayBean.setSubject(tradeOrder.getTradeName());
		AlipayCore alipayCore = new AlipayCore();
		try {
			String payString = alipayCore.pagePay(alipayBean);
			if(StringUtils.isNotBlank(payString)){
				logger.info("[支付-支付宝]调用成功 orderNum={}", tradeOrder.getOrderNum());
				return payString;
			}else{
				logger.info("[支付-支付宝]调用校验 orderNum={} 返回值为空", tradeOrder.getOrderNum());
			}
		} catch (Exception e) {
			logger.error("[支付-支付宝]调用失败异常 orderNum={} e={}", tradeOrder.getOrderNum(),e);
		}

		return null;
	}

	private String payPayPal(TradeOrder tradeOrder){
		try{
			Payment payment = PaypalCore.createPayment(
					tradeOrder.getAmount().doubleValue(),
					tradeOrder.getCurrency().getAb(),
					PaypalPaymentMethodEnum.paypal,
					PayPalPaymentIntentEnum.sale,
					tradeOrder.getTradeDetail());
			String paymentId = payment.getId();

			tradeOrder.setThirdOrderNum(paymentId);

			logger.info("[支付-paypal调用]成功 payment={}",payment.toJSON());
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
				  if(StringUtils.isNotBlank(links.getHref()))
					  return JsUtil.webOpenNewWindow(links.getHref());
				}
			}

		}catch (Exception e){
			logger.error("[支付-paypal调用]失败 orderNum={} e={}", tradeOrder.getOrderNum(),e);
		}
		return null;
	}


}
