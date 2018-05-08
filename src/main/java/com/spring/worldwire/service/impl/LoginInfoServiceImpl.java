package com.spring.worldwire.service.impl;

import java.util.Date;
import java.util.List;

import com.spring.worldwire.dao.UserAccountDao;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.LoginInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.worldwire.dao.LoginInfoDao;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;
import org.springframework.util.CollectionUtils;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {

	@Autowired
	private LoginInfoDao loginInfoDao;
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Override
	public int registerByMail(LoginInfo loginInfo) {
		LoginInfoQuery loginInfoQuery = new LoginInfoQuery();
		loginInfoQuery.setEmail(loginInfo.getEmail());
		List<LoginInfo> info = loginInfoDao.selectByQuery(loginInfoQuery);
		if(!CollectionUtils.isEmpty(info)){
			return -2;//代表邮箱已经被注册
		}
		Date date = new Date();
		LoginInfo one = new LoginInfo();
		one.setEmail(loginInfo.getEmail());
		one.setUserName(loginInfo.getUserName());
		one.setPassword(loginInfo.getPassword());
		one.setCreateTime(date);
		one.setUpdateTime(date);

		int status = loginInfoDao.insertSelective(one);

		return Integer.parseInt(one.getId().toString());
	}

	@Override
	public List<LoginInfo> selectByQuery(LoginInfoQuery loginInfoQuery) {
		return loginInfoDao.selectByQuery(loginInfoQuery);
	}

	@Override
	public LoginInfo selectByPrimaryKey(Long id) {
		return loginInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public int update(LoginInfo info) {
		return loginInfoDao.updateByPrimaryKeySelective(info);
	}

}
