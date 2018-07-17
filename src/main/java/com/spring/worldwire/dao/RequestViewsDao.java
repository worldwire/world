package com.spring.worldwire.dao;

import com.spring.worldwire.model.RequestViews;

public interface RequestViewsDao {
    int deleteByPrimaryKey(Long id);

    int insert(RequestViews record);

    int insertSelective(RequestViews record);

    RequestViews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RequestViews record);

    int updateByPrimaryKey(RequestViews record);

    RequestViews selectByLoginId(Long loginId);

    RequestViews selectByUserId(Long userId);
}