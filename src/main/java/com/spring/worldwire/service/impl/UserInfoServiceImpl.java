package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.UserInfoDao;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2018/4/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public int insert(UserInfo record) {

        return userInfoDao.insertSelective(record);
    }
}
