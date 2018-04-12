package com.spring.worldwire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.worldwire.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/greet")
	public ModelAndView greeting(String name, ModelAndView model){
		model.getModelMap().put("greet", testService.greeting(name));
		model.setViewName("index");
		return model;
	}

}
