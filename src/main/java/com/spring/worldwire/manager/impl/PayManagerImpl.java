package com.spring.worldwire.manager.impl;

import com.spring.worldwire.enums.PayStatusEnum;
import com.spring.worldwire.enums.ProductRequestStatusEnum;
import com.spring.worldwire.enums.ProductTypeEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.manager.PayManager;
import com.spring.worldwire.model.*;
import com.spring.worldwire.service.*;
import com.spring.worldwire.utils.RandomNum;
import com.spring.worldwire.utils.WordsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther pg
 * @Date create in 21:55 2018/7/18
 */
@SuppressWarnings("unused")
@Service
public class PayManagerImpl implements PayManager {
    private static Logger logger = LoggerFactory.getLogger(PayManagerImpl.class);
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ThirdPayOrderService thirdPayOrderService;
    @Autowired
    private PayService payService;
    @Autowired
    private TradeOrderService tradeOrderservice;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ProductRequestService productRequestService;
    @Autowired
    private TranslationApplyService translationApplyService;


    public PayManagerImpl(ProductInfoService productInfoService, ThirdPayOrderService thirdPayOrderService, PayService payService) {
        this.productInfoService = productInfoService;
        this.thirdPayOrderService = thirdPayOrderService;
        this.payService = payService;
    }

    @Override
    public String createRecharge(long userId, long productId, int payCode) {

        ThirdPayEnum thirdPayByCode = ThirdPayEnum.getThirdPayByCode(payCode);
        if (thirdPayByCode == null) {
            return getErroPage();
        }

        //查询相应的产品
        ProductInfo productInfo = productInfoService.findById(productId);

        return doPay(userId, thirdPayByCode, productInfo,productInfo.getAmount());
    }



    @Override
    public void completeOrder(TradeOrder tradeOrder) {
        tradeOrder.setStatus(PayStatusEnum.SUCCESS);
        tradeOrder.setSuccessTime(new Date());
        logger.info("[支付完成] 查找数据修改状态 orderNum={}", tradeOrder.getOrderNum());
        int i = tradeOrderservice.updateByPrimaryKeySelective(tradeOrder);
        if (i > 0) {
            logger.info("[支付完成] 支付完成更新数据库 orderNum = {}", tradeOrder.getOrderNum());
            ThirdPayOrder thirdPayOrder = new ThirdPayOrder();
            thirdPayOrder.setPaymentNo(tradeOrder.getOrderNum());
            thirdPayOrder.setThirdType(tradeOrder.getThirdType());
            thirdPayOrder.setUpdateTime(new Date());
            thirdPayOrder.setPayStatus(PayStatusEnum.SUCCESS);
            i = thirdPayOrderService.successByOrderNum(thirdPayOrder);
            if (i == 0) {
                logger.error("[支付完成] 是否有重复支付嫌疑 或没有找到订单 orderNum = {}", tradeOrder.getOrderNum());
            } else {
                logger.info("[支付完成] 修改状态成功 orderNum = {}", tradeOrder.getOrderNum());
                thirdPayOrder = thirdPayOrderService.selectByOrderNum(tradeOrder.getOrderNum());
                switch (thirdPayOrder.getProductType()) {
                    case CHECK_MESSAGE:
                        addCheckMessage(thirdPayOrder);
                        break;
                    case TRANSLATE_CHINA_TO_ENGLISH:
                    case TRANSLATE_ENGLISH_TO_CHINA:
                        doTranslate(thirdPayOrder);
                        break;
                }


            }
        }
    }

    @Override
    public String createTranslation(long userId, long id, int payCode,int payType) {
        ThirdPayEnum thirdPayByCode = ThirdPayEnum.getThirdPayByCode(payCode);
        if (thirdPayByCode == null) {
            return getErroPage();
        }
        TranslationApply translationApply = translationApplyService.getById(id);
        if(translationApply==null){
            return getErroPage();
        }
        if(translationApply.getUserId()!=userId){
            return getErroPage();
        }
        int countWord = getProductWords(translationApply);
        if(countWord==0){
            return getErroPage();
        }

        List<ProductInfo> productInfos = productInfoService.selectCheckProductList(payType, 2);
        if(productInfos==null||productInfos.size()==0){
            return getErroPage();
        }
        ProductInfo productInfo = productInfos.get(0);
        BigDecimal amount = wordsToAmount(countWord, productInfo);
        return doPay(userId,thirdPayByCode,productInfo,amount);
    }

    @Override
    public BigDecimal wordsToAmount(int countWord, ProductInfo productInfo) {
        Integer times = productInfo.getTimes();
        int pageNo = countWord / times + 1;
        return new BigDecimal(pageNo).multiply(productInfo.getAmount());
    }

    @Override
    public int getProductWords(TranslationApply translationApply) {
        ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
        if(productRequest==null){
            return 0;
        }
        String content = productRequest.getContent();
        return WordsUtils.countWord(content, productRequest.getLanguageType().getEnName());
    }

    private void addCheckMessage(ThirdPayOrder thirdPayOrder) {
        //增加查看次数
        UserAccount userAccount = userAccountService.selectByUserId(thirdPayOrder.getUserId());
        ProductInfo productInfo = productInfoService.findById(thirdPayOrder.getProductId());
        userAccount.setViewingTimes(userAccount.getViewingTimes() + productInfo.getTimes());
        userAccountService.updateUserAccount(userAccount);
    }

    private void doTranslate(ThirdPayOrder thirdPayOrder) {
        ProductRequest productRequest = productRequestService.findById(thirdPayOrder.getForeignId());
        if (ProductRequestStatusEnum.NO_PAY.equals(productRequest.getStatus())) {
            productRequest.setStatus(ProductRequestStatusEnum.WAIT_TRANSLATE);
            productRequest.setUpdateTime(new Date());
            productRequestService.updateStatus(productRequest);
            logger.info("[支付完成] 修改状态 orderNum = {} productRequestId = {} userId ={}",
                    thirdPayOrder.getPaymentNo(), productRequest.getId(), thirdPayOrder.getUserId());
        } else {
            logger.error("[支付完成] 修改状态出现错误状态 orderNum = {} productRequestId = {} userId ={}",
                    thirdPayOrder.getPaymentNo(), productRequest.getId(), thirdPayOrder.getUserId());
        }

    }

    private String doPay(long userId, ThirdPayEnum thirdPayByCode, ProductInfo productInfo,BigDecimal amount) {
        ThirdPayOrder thirdPayOrder = new ThirdPayOrder();
        thirdPayOrder.setAmount(amount);
        thirdPayOrder.setCreateTime(new Date());
        thirdPayOrder.setCurrency(productInfo.getPayType());
        thirdPayOrder.setPaymentNo(System.currentTimeMillis() / 1000 + RandomNum.getPre3Num() + userId);
        thirdPayOrder.setUserId(userId);
        thirdPayOrder.setPayStatus(PayStatusEnum.HAVING);
        thirdPayOrder.setProductType(productInfo.getType());
        thirdPayOrder.setProductId(productInfo.getId());
        if (ProductTypeEnum.CHECK_MESSAGE.equals(productInfo.getType())) {
            thirdPayOrder.setPayDetail("购买查看次数");
        } else {
            thirdPayOrder.setPayDetail("购买翻译次数");
        }
        int i = thirdPayOrderService.save(thirdPayOrder);

        TradeOrder tradeOrder = new TradeOrder(thirdPayOrder.getPaymentNo(), userId,
                amount, productInfo.getProductName(), thirdPayByCode, productInfo.getPayType());
        tradeOrder.setTradeName(productInfo.getProductName());
        return payService.doPayWay(tradeOrder);
    }


    private String getErroPage() {
        return "<script type='text/javascript'>location.href='/';</script>";//todo 错误页面跳转
    }
}
