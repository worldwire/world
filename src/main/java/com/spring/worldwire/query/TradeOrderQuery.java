package com.spring.worldwire.query;

import com.spring.worldwire.query.base.Pager;

/**
 * Created by luxun on 2018/4/28.
 */
public class TradeOrderQuery extends Pager{

    //排序 0正序，1 倒序
    private Integer sort;

    private Long userId;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
