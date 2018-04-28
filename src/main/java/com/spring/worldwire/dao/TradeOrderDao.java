package com.spring.worldwire.dao;

import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TradeOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);

    List<TradeOrder> selectByPage(@Param("query")TradeOrderQuery query);

    TradeOrder getByThirdTradeNum(String thirdOrderNum,ThirdPayEnum thirdPayEnum);

}