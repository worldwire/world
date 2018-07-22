package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ThirdPayOrderDao;
import com.spring.worldwire.model.ThirdPayOrder;
import com.spring.worldwire.query.ThirdPayOrderQuery;
import com.spring.worldwire.service.ThirdPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 21:37 2018/7/18
 */
@SuppressWarnings("unused")
@Service
public class ThirdPayOrderServiceImpl implements ThirdPayOrderService {

    @Autowired
    private ThirdPayOrderDao thirdPayOrderDao;

    public ThirdPayOrderServiceImpl(ThirdPayOrderDao thirdPayOrderDao) {
        this.thirdPayOrderDao = thirdPayOrderDao;
    }

    @Override
    public int save(ThirdPayOrder thirdPayOrder) {
        return thirdPayOrderDao.insert(thirdPayOrder);
    }

    @Override
    public int successByOrderNum(ThirdPayOrder thirdPayOrder) {
        return thirdPayOrderDao.successByOrderNum(thirdPayOrder);
    }

    @Override
    public List<ThirdPayOrder> selectByUserId(ThirdPayOrderQuery query) {
        return thirdPayOrderDao.selectByUserId(query);
    }

    @Override
    public ThirdPayOrder selectByOrderNum(String orderNum) {
        return thirdPayOrderDao.selectByOrderNum(orderNum);
    }
}
