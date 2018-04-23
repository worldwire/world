<<<<<<< HEAD
package com.spring.worldwire.dao;

import com.spring.worldwire.query.LoginInfoQuery;
import org.apache.ibatis.annotations.Param;

import com.spring.worldwire.model.LoginInfo;

import java.util.List;

public interface LoginInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);
    
    List<LoginInfo> selectByQuery(@Param("query")LoginInfoQuery loginInfoQuery);


}