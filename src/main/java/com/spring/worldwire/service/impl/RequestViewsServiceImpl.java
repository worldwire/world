package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.RequestViewsDao;
import com.spring.worldwire.model.RequestViews;
import com.spring.worldwire.service.RequestViewsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/17 11:01
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class RequestViewsServiceImpl implements RequestViewsService {

    @Autowired
    private RequestViewsDao requestViewsDao;

    @Override
    public int insertRequestViews(RequestViews requestViews) {
        return requestViewsDao.insert(requestViews);
    }

    @Override
    public RequestViews selectByUserId(Long userId) {
        return requestViewsDao.selectByUserId(userId);
    }

    @Override
    public RequestViews selectByLoginId(Long loginId) {
        return requestViewsDao.selectByLoginId(loginId);
    }
}
