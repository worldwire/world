package com.spring.worldwire.manager.impl;

import com.spring.worldwire.enums.PayStatusEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.manager.PayManager;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.model.ThirdPayOrder;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.service.PayService;
import com.spring.worldwire.service.ProductInfoService;
import com.spring.worldwire.service.ThirdPayOrderService;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.utils.RandomNum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public PayManagerImpl(ProductInfoService productInfoService, ThirdPayOrderService thirdPayOrderService, PayService payService) {
        this.productInfoService = productInfoService;
        this.thirdPayOrderService = thirdPayOrderService;
        this.payService = payService;
    }

    @Override
    public String createOrder(long userId, long productId, int payCode) {

        ThirdPayEnum thirdPayByCode = ThirdPayEnum.getThirdPayByCode(payCode);
        if(thirdPayByCode==null){
            return "<script type='text/javascript'>location.href='/';</script>";//todo 错误页面跳转
        }

        //查询相应的产品
        ProductInfo productInfo = productInfoService.findById(productId);

        ThirdPayOrder thirdPayOrder = new ThirdPayOrder();
        thirdPayOrder.setAmount(productInfo.getAmount());
        thirdPayOrder.setCreateTime(new Date());
        thirdPayOrder.setCurrency(productInfo.getPayType());
        thirdPayOrder.setPaymentNo(System.currentTimeMillis() / 1000 + RandomNum.getPre3Num() + userId);
        thirdPayOrder.setUserId(userId);
        thirdPayOrder.setPayStatus(PayStatusEnum.HAVING);
        thirdPayOrder.setProductType(productInfo.getType());
        int i = thirdPayOrderService.save(thirdPayOrder);

        TradeOrder tradeOrder = new TradeOrder("123" + System.currentTimeMillis() / 1000, 10, productInfo.getAmount(), productInfo.getProductName(), thirdPayByCode, productInfo.getPayType());
        tradeOrder.setTradeName(productInfo.getProductName());
        return payService.doPayWay(tradeOrder);
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
                //todo 后续其他逻辑  比如根据状态判断添加查看次数，如果是翻译 直接修改翻译申请订单状态
                logger.info("[支付完成] 修改状态成功");
            }
        }
    }
}
