package com.spring.worldwire.controller.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.worldwire.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class I18NController {
	
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 国际化语言切换
	 * @param request 请求体
	 * @param response 响应体
	 * @param lang 语言
	 * @return
	 */
	@RequestMapping("/lan")
	public ModelAndView  language(HttpServletRequest request, HttpServletResponse response, String lang){
//		System.out.println(request.getHeader("Referer"));
		String oriUrl = request.getHeader("Referer");
	    Locale locale= request.getLocale();
	    logger.error(locale.toString());
	    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	    lang=lang.toLowerCase();
	    logger.info("language:"+lang);
	    if(lang!= null && !lang.equals("")){
	        if(lang.equals("zh_cn")){
	            localeResolver.setLocale(request, response, Locale.CHINA);
	        }else if(lang.equals("en")){
	            localeResolver.setLocale(request, response, Locale.US);
	        }else{
	            localeResolver.setLocale(request, response, Locale.CHINA );
	        }
	    
	    }
	    return new ModelAndView("redirect:"+oriUrl);
	}

}
