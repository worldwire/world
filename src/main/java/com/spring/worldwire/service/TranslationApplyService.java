package com.spring.worldwire.service;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.query.TradeOrderQuery;

import java.util.List;

/**
 * Created by luxun on 2018/4/27.
 */
public interface TranslationApplyService {


    TranslationApply applyTranslation(ProductRequest productRequest, LanguageEnum fromType) throws Exception;
}
