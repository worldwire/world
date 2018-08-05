package com.spring.worldwire.manager;

import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.model.vo.ProductRequestVo;

import java.util.List;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/18 17:22
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface ProductRequestManager {

    UserInfo viewRequestContract(Long userId, Long productRequestId);

    ProductRequestVo getRequestByQuery(int userType, int requestType, UserTypeEnum userTypeEnum, int pageSize, int pageNo, String key);
}
