package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductRequestDao;
import com.spring.worldwire.dao.TradeOrderDao;
import com.spring.worldwire.dao.TranslationApplyDao;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.query.TradeOrderQuery;
import com.spring.worldwire.service.TradeOrderService;
import com.spring.worldwire.service.TranslationApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by pg on 2018/4/28.
 */
@Service
public class TranslationApplyServiceImpl implements TranslationApplyService {

    @Autowired
    private TranslationApplyDao translationApplyDao;
    @Autowired
    private ProductRequestDao productRequestDao;


    @Override
    @Transactional
    public TranslationApply applyTranslation(ProductRequest productRequest,LanguageEnum fromType) throws Exception {

        ProductRequest converFromProduct = productRequest.converFromProduct(fromType);
        if(productRequestDao.insert(converFromProduct)>0){
            TranslationApply translationApply = new TranslationApply();
            translationApply.setReqId(productRequest.getId());
            translationApply.setOrigType(productRequest.getLanguageType());
            translationApply.setFromType(fromType);
            translationApply.setStatus(TranslationApplyStatusEnum.INIT);
            translationApply.setCreateTime(new Date());
            translationApply.setFromReqId(converFromProduct.getId());
            if(translationApplyDao.insert(translationApply)>0){
                return translationApply;
            }else{
                throw new Exception("[翻译] 插入数据库出问题");
            }
        }
        return null;
    }
}
