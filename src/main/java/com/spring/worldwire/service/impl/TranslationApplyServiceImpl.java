package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductRequestDao;
import com.spring.worldwire.dao.TranslationApplyDao;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.vo.TranslationApplyVO;
import com.spring.worldwire.service.TranslationApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pg on 2018/4/28.
 */
@SuppressWarnings("unused")
@Service
public class TranslationApplyServiceImpl implements TranslationApplyService {

    private TranslationApplyDao translationApplyDao;
    private ProductRequestDao productRequestDao;

    @Autowired
    public TranslationApplyServiceImpl(TranslationApplyDao translationApplyDao, ProductRequestDao productRequestDao) {
        this.translationApplyDao = translationApplyDao;
        this.productRequestDao = productRequestDao;
    }


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
            translationApply.setUserId(converFromProduct.getUserId());
            if(translationApplyDao.insert(translationApply)>0){
                return translationApply;
            }else{
                throw new Exception("[翻译] 插入数据库出问题");
            }
        }
        return null;
    }

    @Override
    public TranslationApply getById(Long id) {
        return translationApplyDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int translation(TranslationApplyVO translationApplyVO) {
        ProductRequest fromProduct = productRequestDao.selectByPrimaryKey(translationApplyVO.getFromReqId());
        TranslationApply translationApply = translationApplyDao.selectByPrimaryKey(translationApplyVO.getId());
        if(StringUtils.isNotBlank(translationApplyVO.getContext())){
            fromProduct.setContent(translationApplyVO.getContext());
            fromProduct.setTitle(translationApplyVO.getTitle());
            translationApply.setStatus(TranslationApplyStatusEnum.AUDITION);
            translationApply.setOperatorId(translationApplyVO.getOperatorId());
            translationApply.setUpdateTime(new Date());
            translationApply.setOperatorTime(new Date());
            int i = productRequestDao.updateByPrimaryKeySelective(fromProduct);
            if(i>0){
                i = translationApplyDao.updateByPrimaryKeySelective(translationApply);
            }
            return i;
        }
        return 0;
    }

    @Override
    public int updateAudit(Long id, Long auditId) {
        TranslationApply translationApply = new TranslationApply();
        translationApply.setId(id);
        translationApply.setAuditorId(auditId);
        translationApply.setAuditorTime(new Date());
        return translationApplyDao.updateByPrimaryKeySelective(translationApply);

    }

    @Override
    public int payTranslation(Long id) {
        TranslationApply translationApply = new TranslationApply();
        translationApply.setId(id);
        translationApply.setStatus(TranslationApplyStatusEnum.NORMAL);
        return translationApplyDao.updateByPrimaryKeySelective(translationApply);
    }
}
