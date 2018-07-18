package com.spring.worldwire.service;

import com.spring.worldwire.model.ThirdPayOrder;

/**
 * @Auther pg
 * @Date create in 21:37 2018/7/18
 */
public interface ThirdPayOrderService {
    int save(ThirdPayOrder thirdPayOrder);

    int successByOrderNum(ThirdPayOrder thirdPayOrder);
}
