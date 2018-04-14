package com.spring.worldwire.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.worldwire.dao.LoginInfoDao;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;

public class LoginInfoServiceImpl implements LoginInfoService {

	@Autowired
	private LoginInfoDao loginInfoDao;
	
	@Override
	public int register(String userName, String password, String email) {
		LoginInfo info = loginInfoDao.selectByEmail(email);
		if(info != null){
			return -2;//代表邮箱已经被注册
		}
		Date date = new Date();
		LoginInfo one = new LoginInfo();
		one.setEmail(email);
		one.setUserName(userName);
		one.setPassword(password);
		one.setCreateTime(date);
		one.setUpdateTime(date);
		
		return loginInfoDao.insert(one);
	}

}
