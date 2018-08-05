package com.spring.worldwire.manager;

import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.TranslationApply;

import java.math.BigDecimal;

/**
 * @Auther pg
 * @Date create in 21:55 2018/7/18
 */
public interface PayManager {
    String createRecharge(long userId, long productId, int payCode);

    void completeOrder(TradeOrder tradeOrder);

    String createTranslation(long userId, long id, int payCode,int payType);

    BigDecimal wordsToAmount(int countWord, ProductInfo productInfo);

    int getProductWords(TranslationApply translationApply);
}
