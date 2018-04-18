package com.spring.worldwire.service.impl;

import org.springframework.stereotype.Service;

import com.spring.worldwire.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Override
	public String greeting(String name) {

		return "greeting " + name;
	}

}
