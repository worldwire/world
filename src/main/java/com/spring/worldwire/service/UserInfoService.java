package com.spring.worldwire.service;

import com.spring.worldwire.model.UserInfo;

/**
 * Created by luxun on 2018/4/27.
 */
public interface UserInfoService {

    int insert(UserInfo record);

    UserInfo selectByLoginId(Long id);

    UserInfo selectById(Long id);

    int update(UserInfo userInfo);

    UserInfo selectSimpleById(long l);
}
