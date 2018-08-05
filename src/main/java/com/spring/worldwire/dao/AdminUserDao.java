package com.spring.worldwire.dao;

import com.spring.worldwire.model.AdminUser;

import java.util.List;

public interface AdminUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    AdminUser selectAdmin(AdminUser adminUser);

    List<AdminUser> findByUser(AdminUser adminUser);

    int adopt(long id);
}