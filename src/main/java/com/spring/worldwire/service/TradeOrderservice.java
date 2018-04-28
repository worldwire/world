package com.spring.worldwire.service;

import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;

import java.util.List;

/**
 * Created by luxun on 2018/4/27.
 */
public interface TradeOrderservice {

    List<TradeOrder> selectByPage(TradeOrderQuery query);

}
