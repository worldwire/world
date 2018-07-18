package com.spring.worldwire.manager;

import com.spring.worldwire.model.TradeOrder;

/**
 * @Auther pg
 * @Date create in 21:55 2018/7/18
 */
public interface PayManager {
    String createOrder(long userId, long productId, int payCode);

    void completeOrder(TradeOrder tradeOrder);
}
