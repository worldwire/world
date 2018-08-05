package com.spring.worldwire.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.manager.PayManager;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.service.ProductInfoService;
import com.spring.worldwire.service.TranslationApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private PayManager payManager;
    @Autowired
    private TranslationApplyService translationApplyService;


    @RequestMapping("recharge")
    public String recharge(Model model) {

        return "pc/recharge";
    }

    @RequestMapping("translation")
    public String translation(Model model, long id) {

        TranslationApply translationApply = translationApplyService.getById(id);
        model.addAttribute("translationApply",translationApply);
        int words = payManager.getProductWords(translationApply);
        model.addAttribute("wordsCount",words);
        List<ProductInfo> productInfos = productInfoService.selectProductByType(2);
        for (ProductInfo productInfo : productInfos) {
            BigDecimal bigDecimal = payManager.wordsToAmount(words, productInfo);
            if (CurrencyEnum.RMB.equals(productInfo.getPayType())) {
                model.addAttribute("rmbAmount", bigDecimal.doubleValue());
            } else {
                model.addAttribute("usdAmount", bigDecimal.doubleValue());
            }
        }
        return "pc/translationPay";
    }

    @RequestMapping("payRecharge")
    public String payRecharge(Model model, HttpServletRequest request, long id, int payCode) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();

        String nextOperationBody = payManager.createRecharge(Long.parseLong(userIdStr), id, payCode);
        model.addAttribute("nextOperation", nextOperationBody);
        return "pay/payBlank";
    }


    @RequestMapping("product")
    @ResponseBody
    public String productList(Model model, int code) {
        int type = 1;//只是充值产品查看次数的
        List<ProductInfo> productInfoList = productInfoService.selectCheckProductList(code, type);

        return JSONObject.toJSONString(productInfoList);
    }

    @RequestMapping("payTranslation")
    public String payTranslation(Model model, HttpServletRequest request, long id, int payCode,int payType) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();

        String nextOperationBody = payManager.createTranslation(Long.parseLong(userIdStr), id, payCode,payType);
        model.addAttribute("nextOperation", nextOperationBody);
        return "pay/payBlank";
    }
}
