package com.spring.worldwire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.worldwire.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/greet")
	@ResponseBody
	public String greeting(String name, ModelAndView model){
		return testService.greeting(name);
	}
	
	@RequestMapping("/greets")
	public String greetings(Model model, String name){
		model.addAttribute("greet", testService.greeting(name));
		return "index";
	}

}
