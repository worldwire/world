package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.UserInfoDao;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2018/4/27.
 */
@SuppressWarnings("unused")
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public int insert(UserInfo record) {

        return userInfoDao.insertSelective(record);
    }

    @Override
    public UserInfo selectByLoginId(Long id) {
        return userInfoDao.selectByLoginId(id);
    }

    @Override
    public UserInfo selectById(Long id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(UserInfo userInfo) {
        return userInfoDao.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserInfo selectSimpleById(long id) {
        return userInfoDao.selectByPrimaryKey(id);
    }
}
