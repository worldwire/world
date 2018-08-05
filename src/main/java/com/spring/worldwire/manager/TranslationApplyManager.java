package com.spring.worldwire.manager;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;

/**
 * @Auther pg
 * @Date create in 21:46 2018/8/1
 */
public interface TranslationApplyManager {
    TranslationApply applyTranslation(ProductRequest productRequest, LanguageEnum nameByCode);

    TranslationApply check(ProductRequest productRequest, LanguageEnum nameByCode);
}
