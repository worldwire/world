package com.spring.worldwire.service;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.query.LoginInfoQuery;

import java.util.List;

public interface LoginInfoService {
	
	public int registerByMail(LoginInfo loginInfo);

	List<LoginInfo> selectByQuery(LoginInfoQuery loginInfoQuery);

}
