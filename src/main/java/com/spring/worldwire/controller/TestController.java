package com.spring.worldwire.controller;

import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.service.PayService;
import com.spring.worldwire.service.TestService;
import com.spring.worldwire.utils.RedisUtils;

import java.math.BigDecimal;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {
	
    @Autowired
    private MessageSource messageSource;
	@Autowired
	private TestService testService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private PayService payService;

	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/greet")
	@ResponseBody
	public String greeting(String name){
		return testService.greeting(name);
	}
	
	@RequestMapping("/greets")
	public String greetings(Model model, String name){
		
		Locale locale = LocaleContextHolder.getLocale();
		model.addAttribute("greet", testService.greeting(name));
		model.addAttribute("hello",  messageSource.getMessage("hello", null, locale));
		logger.info("======================");
		return "test";
	}
	@RequestMapping("/redis")
	@ResponseBody
	public String redisTest(){
		System.out.println(redisUtils.isCacheExists("hello"));
		redisUtils.set("hello","12345");
		System.out.println(redisUtils.getValueByKey("hello"));
		redisUtils.deleteKey("hello");
		System.out.println(redisUtils.getValueByKey("hello"));
		return "haha";
	}

	@RequestMapping("/login")
	public String login(){

		return "";
	}

	@RequestMapping("/pay")
	@ResponseBody
	public String payTest(String pay,int payCode,int currencyCode){

		TradeOrder tradeOrder = new TradeOrder("123"+System.currentTimeMillis()/1000,10L,new BigDecimal(pay),"test",ThirdPayEnum.getThirdPayByCode(payCode),CurrencyEnum.getCurrencyByCode(currencyCode));
		String s = payService.doPayWay(tradeOrder);
		System.out.println(s);
		return s;

		//return null;

	}

}
