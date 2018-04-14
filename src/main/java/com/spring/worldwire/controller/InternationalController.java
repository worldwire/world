package com.spring.worldwire.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class InternationalController {
	
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 国际化语言切换
	 * @param request
	 * @param response
	 * @param lang
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
