package com.spring.worldwire.dao;


import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;


public interface TradeOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);

    TradeOrder getByThirdTradeNum(String thirdOrderNum,ThirdPayEnum thirdPayEnum);
}