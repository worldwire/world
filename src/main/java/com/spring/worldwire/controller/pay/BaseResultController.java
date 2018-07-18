package com.spring.worldwire.controller.pay;

import com.spring.worldwire.manager.PayManager;
import com.spring.worldwire.model.TradeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理公共的业务逻辑区域
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseResultController {
    private static Logger logger = LoggerFactory.getLogger(BaseResultController.class);

    @Autowired
    private PayManager payManager;


    void completeOrder(TradeOrder tradeOrder) {

        payManager.completeOrder(tradeOrder);

    }


    String notifyOrder() {


        return "index";
    }

    String cancel() {
        return "cancel";
    }


    String saveThirdPay() {
        return "";
    }

}
