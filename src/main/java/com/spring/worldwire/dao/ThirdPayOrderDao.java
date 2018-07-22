package com.spring.worldwire.dao;

import com.spring.worldwire.model.ThirdPayOrder;
import com.spring.worldwire.query.ThirdPayOrderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 21:38 2018/7/18
 */
public interface ThirdPayOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(ThirdPayOrder record);

    ThirdPayOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdPayOrder record);

    int updateByPrimaryKey(ThirdPayOrder record);

    List<ThirdPayOrder> selectByUserId(@Param("query") ThirdPayOrderQuery query);

    int successByOrderNum(ThirdPayOrder thirdPayOrder);

    ThirdPayOrder selectByOrderNum(String orderNum);
}
