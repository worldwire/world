package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.UserAccountDao;
import com.spring.worldwire.dao.UserCheckDao;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserCheck;
import com.spring.worldwire.service.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther pg
 * @Date create in 22:04 2018/5/10
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private UserCheckDao userCheckDao;
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public UserCheck checkUserCheck(Long userId, Long reqId) {
        return userCheckDao.getByUserAndReq(userId,reqId);
    }

    @Override
    @Transactional
    public int checkProductRequest(UserAccount userAccount,Long reqId) {
        userAccount.setViewingTimes(userAccount.getViewingTimes());
        userAccount.setOldVersion(userAccount.getVersion());
        userAccount.setVersion(userAccount.getVersion() + 1);
        int i = userAccountDao.updateByPrimaryKeySelective(userAccount);
        if(i>0){
            UserCheck userCheck = new UserCheck();
            userCheck.setUserId(userAccount.getUserId());
            userCheck.setReqId(reqId);
            userCheck.setCreateTime(new Date());
            return userCheckDao.insert(userCheck);
        }
        return 0;
    }
}
