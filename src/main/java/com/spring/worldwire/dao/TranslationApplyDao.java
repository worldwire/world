package com.spring.worldwire.dao;

import com.spring.worldwire.model.TranslationApply;

public interface TranslationApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(TranslationApply record);

    int insertSelective(TranslationApply record);

    TranslationApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranslationApply record);

    int updateByPrimaryKey(TranslationApply record);
}