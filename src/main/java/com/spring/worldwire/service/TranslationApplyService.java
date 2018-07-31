package com.spring.worldwire.service;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.vo.TranslationApplyVO;
import com.spring.worldwire.query.TranslationApplyQuery;

import java.util.List;

/**
 * Created by luxun on 2018/4/27.
 */
public interface TranslationApplyService {


    TranslationApply applyTranslation(ProductRequest productRequest, LanguageEnum fromType) throws Exception;

    TranslationApply getById(Long id);

    int translation(TranslationApplyVO translationApplyVO);

    int updateAudit(Long id, Long auditId);

    int payTranslation(Long id);

    int pageCount(TranslationApplyQuery translationApplyQuery);

    List<TranslationApply> page(TranslationApplyQuery translationApplyQuery);
}
