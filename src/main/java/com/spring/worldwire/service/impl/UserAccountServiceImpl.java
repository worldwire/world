package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.UserAccountDao;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2018/4/27.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public int insert(UserAccount record) {
        return userAccountDao.insert(record);
    }

    @Override
    public UserAccount selectByUserId(Long userId) {
        return userAccountDao.selectAccountByUserId(userId);
    }

    @Override
    public int updateUserAccount(UserAccount record) {
        return userAccountDao.updateByPrimaryKeySelective(record);
    }
}
