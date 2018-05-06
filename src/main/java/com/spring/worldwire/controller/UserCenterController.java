package com.spring.worldwire.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by luxun on 2018/4/28.
 */
@Controller
@RequestMapping("/center")
public class UserCenterController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TradeOrderService tradeOrderservice;

    @RequestMapping("/detail")
    public String userCenter(TradeOrderQuery query){
        JSONObject json = new JSONObject();
        UserAccount account = userAccountService.selectByUserId(query.getUserId());
        List<TradeOrder> tradeOrder = tradeOrderservice.selectByPage(query);
        json.put("account",account);
        json.put("tradeOrder",tradeOrder);
        return json.toJSONString();
    }
}
