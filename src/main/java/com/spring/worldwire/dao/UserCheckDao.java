package com.spring.worldwire.dao;

import com.spring.worldwire.model.UserCheck;

public interface UserCheckDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserCheck record);

    int insertSelective(UserCheck record);

    UserCheck selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCheck record);

    int updateByPrimaryKey(UserCheck record);
}