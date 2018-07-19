package com.spring.worldwire.manager;

import com.spring.worldwire.model.UserAccount;

import java.util.Map;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 10:35
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface UserCenterManager {

    Map<String,Object> getUserDetailInfo(Long userId);

    UserAccount signUp(Long userId);
}
