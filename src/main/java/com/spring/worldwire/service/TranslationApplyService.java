package com.spring.worldwire.service;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.model.AdminUser;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.vo.TranslationApplyVO;
import com.spring.worldwire.query.TranslationApplyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luxun on 2018/4/27.
 */
public interface TranslationApplyService {

    TranslationApply getById(Long id);

    int translation(TranslationApplyVO translationApplyVO);

    int payTranslation(Long id);

    int pageCount(TranslationApplyQuery translationApplyQuery);

    List<TranslationApply> page(TranslationApplyQuery translationApplyQuery);

    int applyFreeTranslation(TranslationApply translationApply, UserAccount userAccount);

    int save(TranslationApply translationApply);

    TranslationApply findReqIdAndFrom(Long reqId,LanguageEnum fromType);

    int updateAudit(TranslationApply translationApply);
}
