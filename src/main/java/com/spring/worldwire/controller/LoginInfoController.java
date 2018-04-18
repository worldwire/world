package com.spring.worldwire.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.worldwire.service.LoginInfoService;

@Controller
@RequestMapping("/login")
public class LoginInfoController {
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	@RequestMapping("/registerByMail")
	@ResponseBody
	public Map<String,Object> registerByEamil(String userName, String password,String email){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Integer result = loginInfoService.register(userName, password, email);
			map.put("data", result);
			map.put("msg", "success");
			map.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "error");
			map.put("status", -1);
		}
		return map;
	}

}
