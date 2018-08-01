package com.spring.worldwire.dao;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.query.TranslationApplyQuery;

import java.util.List;

public interface TranslationApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(TranslationApply record);

    int insertSelective(TranslationApply record);

    TranslationApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranslationApply record);

    int updateByPrimaryKey(TranslationApply record);

    int pageCount(TranslationApplyQuery translationApplyQuery);

    List<TranslationApply> page(TranslationApplyQuery translationApplyQuery);

    TranslationApply findReqIdAndFrom(Long reqId, LanguageEnum fromType);
}