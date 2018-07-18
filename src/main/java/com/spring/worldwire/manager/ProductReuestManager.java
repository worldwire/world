package com.spring.worldwire.manager;

import com.spring.worldwire.model.UserInfo;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/18 17:22
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface ProductReuestManager {

    UserInfo viewRequestContract(Long userId, Long productRequestId);
}
