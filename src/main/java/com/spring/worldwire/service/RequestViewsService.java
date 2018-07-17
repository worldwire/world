package com.spring.worldwire.service;

import com.spring.worldwire.model.RequestViews;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/17 11:00
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface RequestViewsService {

    int insertRequestViews(RequestViews requestViews);

    RequestViews selectByUserId(Long userId);

    RequestViews selectByLoginId(Long loginId);
}
