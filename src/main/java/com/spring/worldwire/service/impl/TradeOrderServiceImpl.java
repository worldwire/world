package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.TradeOrderDao;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luxun on 2018/4/28.
 */
@Service
public class TradeOrderServiceImpl implements TradeOrderService {

    @Autowired
    private TradeOrderDao tradeOrderDao;

    @Override
    public List<TradeOrder> selectByPage(TradeOrderQuery query) {
        return tradeOrderDao.selectByPage(query);
    }

    @Override
    public TradeOrder getByThirdTradeNum(String thirdOrderNum, ThirdPayEnum thirdPayEnum) {
        return tradeOrderDao.getByThirdTradeNum(thirdOrderNum,thirdPayEnum);
    }

    @Override
    public int updateByPrimaryKeySelective(TradeOrder tradeOrder){
        return tradeOrderDao.updateByPrimaryKeySelective(tradeOrder);
    }

    @Override
    public TradeOrder getByTradeNum(String tradeNo, ThirdPayEnum thirdPayEnum) {
        return tradeOrderDao.getByTradeNum(tradeNo,thirdPayEnum);
    }
}
