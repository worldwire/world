package com.spring.worldwire.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * Created by pg on 2018/5/27.
 * 我的账户页面
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/myaccount")
public class MyAcountController {

    @Autowired
    private TradeOrderService tradeOrderService;

    @RequestMapping("/toTrade")
    String initMyTran(int type) {

        switch (type) {
            case 1:
                return "myAcount/trades";
            case 2:
                return "";
            case 3:
                return "";
            default:
                return "";
        }

    }

    @RequestMapping("/myTrades/{userId}")
    @ResponseBody
    String translation(int pageNo, @PathVariable("userId") long userId) {

        TradeOrderQuery tradeOrderQuery = new TradeOrderQuery();
        tradeOrderQuery.setUserId(userId);
        tradeOrderQuery.setSort(1);
        tradeOrderQuery.setPageNo(pageNo);
        List<TradeOrder> tradeOrders = tradeOrderService.selectByPage(tradeOrderQuery);
        return JSONObject.toJSONString(tradeOrders);
    }

}
