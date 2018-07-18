package com.spring.worldwire.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.PayManager;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private PayManager payManager;


    @RequestMapping("")
    public String toRecharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("recharge")
    public String recharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("payRecharge")
    public String payRecharge(Model model,HttpServletRequest request, long id, int payCode) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();

        String nextOperationBody = payManager.createOrder(Long.parseLong(userIdStr),id,payCode);
        model.addAttribute("nextOperation",nextOperationBody);
        return "pay/payBlank";
    }


    @RequestMapping("product")
    @ResponseBody
    public String productList(Model model,int code) {
        List<ProductInfo> productInfoList = productInfoService.selectCheckProductList(code);

        return JSONObject.toJSONString(productInfoList);
    }
}
