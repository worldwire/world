package com.spring.worldwire.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.spring.worldwire.service.TestService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/test")
public class TestController {
	
    @Autowired
    private MessageSource messageSource;
	@Autowired
	private TestService testService;
	
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	public TestController(MessageSource messageSource, TestService testService) {
		this.messageSource = messageSource;
		this.testService = testService;
	}

	@RequestMapping("/greet")
	@ResponseBody
	public String greeting(String name, ModelAndView model){
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

	@RequestMapping("/pay")
	@ResponseBody
	public String payTest(){
		/**
		 * 支付宝支付
		 */
        /*Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.ALIPAY_PARTNER);
        sParaTemp.put("seller_id", AlipayConfig.ALIPAY_PARTNER);
        sParaTemp.put("_input_charset", AlipayConfig.ALIPAY_INPUT_CHARSET);
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", "www.baidu.com");
        sParaTemp.put("out_trade_no", "123"+String.valueOf(System.currentTimeMillis() / 1000));
        sParaTemp.put("subject", "test");
        sParaTemp.put("total_fee","1.00");
        String buildRequest = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        return buildRequest;*/


		return null;

	}

}
