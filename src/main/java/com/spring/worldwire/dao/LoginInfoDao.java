package com.spring.worldwire.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.worldwire.model.LoginInfo;

public interface LoginInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);
    
    LoginInfo selectByEmail(@Param("email")String email);
}