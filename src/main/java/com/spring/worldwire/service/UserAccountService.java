package com.spring.worldwire.service;

import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.UserAccount;

/**
 * Created by luxun on 2018/4/27.
 */
public interface UserAccountService {

    int insert(UserAccount record);

    UserAccount selectByUserId(Long userId);

    int updateUserAccount(UserAccount record);

}
