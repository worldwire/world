package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.AdminUserDao;
import com.spring.worldwire.model.AdminUser;
import com.spring.worldwire.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 19:26 2018/7/29
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;


    @Override
    public AdminUser findAdmin(AdminUser adminUser) {
        return adminUserDao.selectAdmin(adminUser);
    }

    @Override
    public int save(AdminUser adminUser) {
        return adminUserDao.insert(adminUser);
    }

    @Override
    public List<AdminUser> findByUser(AdminUser adminUser) {
        return adminUserDao.findByUser(adminUser);
    }

    @Override
    public int del(long id) {
        return adminUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public int adopt(long id) {
        return adminUserDao.adopt(id);
    }


}
