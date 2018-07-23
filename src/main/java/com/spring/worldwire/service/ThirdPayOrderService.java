package com.spring.worldwire.service;

import com.spring.worldwire.model.ThirdPayOrder;
import com.spring.worldwire.query.ThirdPayOrderQuery;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 21:37 2018/7/18
 */
public interface ThirdPayOrderService {
    int save(ThirdPayOrder thirdPayOrder);

    int successByOrderNum(ThirdPayOrder thirdPayOrder);

    List<ThirdPayOrder> selectByUserId(ThirdPayOrderQuery query);

    ThirdPayOrder selectByOrderNum(String orderNum);
}
