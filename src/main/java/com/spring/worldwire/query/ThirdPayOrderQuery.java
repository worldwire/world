package com.spring.worldwire.query;

import com.spring.worldwire.query.base.Pager;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 10:57
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class ThirdPayOrderQuery extends Pager{

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
