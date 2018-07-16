package com.spring.worldwire.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("")
    public String toRecharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("recharge")
    public String recharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("product")
    @ResponseBody
    public String productList(Model model,int code) {
        List<ProductInfo> productInfoList = productInfoService.selectCheckProductList(code);

        return JSONObject.toJSONString(productInfoList);
    }
}
