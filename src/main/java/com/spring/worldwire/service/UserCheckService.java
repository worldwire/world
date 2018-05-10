package com.spring.worldwire.service;

import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserCheck;

/**
 * @Auther pg
 * @Date create in 22:04 2018/5/10
 */
public interface UserCheckService {
    UserCheck checkUserCheck(Long userId, Long reqId);

    int checkProductRequest(UserAccount userAccount,Long reqId);
}
