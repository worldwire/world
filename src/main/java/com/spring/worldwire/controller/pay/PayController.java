package com.spring.worldwire.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.service.PayService;
import com.spring.worldwire.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private PayService payService;

    @RequestMapping("")
    public String toRecharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("recharge")
    public String recharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("payRecharge")
    public String payRecharge(Model model,long id,int payCode) {
        ProductInfo productInfo = productInfoService.findById(id);
        TradeOrder tradeOrder = new TradeOrder("123"+System.currentTimeMillis()/1000,10,productInfo.getAmount(),"test",ThirdPayEnum.getThirdPayByCode(payCode),productInfo.getPayType());
        String s = payService.doPayWay(tradeOrder);
        System.out.println(s);
        return s;
    }


    @RequestMapping("product")
    @ResponseBody
    public String productList(Model model,int code) {
        List<ProductInfo> productInfoList = productInfoService.selectCheckProductList(code);

        return JSONObject.toJSONString(productInfoList);
    }
}
