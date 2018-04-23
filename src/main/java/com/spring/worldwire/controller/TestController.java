package com.spring.worldwire.controller;

import com.spring.worldwire.service.TestService;
import com.spring.worldwire.utils.RedisUtils;
import com.spring.worldwire.utils.pay.alipay.AlipayBean;
import com.spring.worldwire.utils.pay.alipay.AlipayCore;
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

@Controller
@RequestMapping("/test")
public class TestController {
	
    @Autowired
    private MessageSource messageSource;
	@Autowired
	private TestService testService;
	@Autowired
	private RedisUtils redisUtils;

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
		return "index";
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

	@RequestMapping("/pay")
	@ResponseBody
	public String payTest(){
			AlipayBean alipayBean = new AlipayBean();
			alipayBean.setBody("test");
			alipayBean.setOut_trade_no("123"+System.currentTimeMillis()/1000);
			alipayBean.setTotal_amount("0.01");
		AlipayCore alipayCore = new AlipayCore();
        return alipayCore.pagePay(alipayBean);


		//return null;

	}

}
