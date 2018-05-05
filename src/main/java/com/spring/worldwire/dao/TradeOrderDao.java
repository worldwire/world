package com.spring.worldwire.dao;

import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface TradeOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);

    List<TradeOrder> selectByPage(@Param("query")TradeOrderQuery query);

    TradeOrder getByThirdTradeNum(@Param("thirdOrderNum")String thirdOrderNum,@Param("thirdPayEnum")ThirdPayEnum thirdPayEnum);


    TradeOrder getByTradeNum(@Param("orderNum") String orderNum, @Param("thirdPayEnum") ThirdPayEnum thirdPayEnum);
}