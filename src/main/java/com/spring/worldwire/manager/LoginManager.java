package com.spring.worldwire.manager;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/16 17:07
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface LoginManager {

    String viewRequestContract(Long userId, Long productRequestId);

    login(String email, String password);
}
