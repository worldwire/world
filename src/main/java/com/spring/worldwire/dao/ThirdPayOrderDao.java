package com.spring.worldwire.dao;

import com.spring.worldwire.model.ThirdPayOrder;

/**
 * @Auther pg
 * @Date create in 21:38 2018/7/18
 */
public interface ThirdPayOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(ThirdPayOrder record);

    int insertSelective(ThirdPayOrder record);

    ThirdPayOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdPayOrder record);

    int updateByPrimaryKey(ThirdPayOrder record);

    ThirdPayOrder selectByUserId(Long userId);

    int successByOrderNum(ThirdPayOrder thirdPayOrder);
}
