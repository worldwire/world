package com.spring.worldwire.manager;

import com.spring.worldwire.model.TradeOrder;

/**
 * @Auther pg
 * @Date create in 21:55 2018/7/18
 */
public interface PayManager {
    String createRecharge(long userId, long productId, int payCode);

    void completeOrder(TradeOrder tradeOrder);

    String createTranslation(long userId, long id, int payCode);
}
