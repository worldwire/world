package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.TradeOrderDao;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.service.TradeOrderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luxun on 2018/4/28.
 */
@Service
public class TradeOrderServiceImpl implements TradeOrderservice{

    @Autowired
    private TradeOrderDao tradeOrderDao;

    @Override
    public List<TradeOrder> selectByPage(TradeOrderQuery query) {
        return tradeOrderDao.selectByPage(query);
    }
}
