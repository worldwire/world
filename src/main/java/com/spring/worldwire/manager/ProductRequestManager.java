package com.spring.worldwire.manager;

import com.spring.worldwire.enums.RequestTypeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.model.vo.ProductRequestVo;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/18 17:22
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface ProductRequestManager {

    UserInfo viewRequestContract(Long userId, Long productRequestId);

    ProductRequestVo getRequestByQuery(UserTypeEnum userTypeEnum, int nationType, RequestTypeEnum requestTypeEnum, int pageSize, int pageNo, String key);

}
