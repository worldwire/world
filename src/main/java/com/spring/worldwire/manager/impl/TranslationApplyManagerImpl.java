package com.spring.worldwire.manager.impl;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.manager.TranslationApplyManager;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.TranslationApplyService;
import com.spring.worldwire.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther pg
 * @Date create in 21:46 2018/8/1
 */
@SuppressWarnings("unused")
@Service
public class TranslationApplyManagerImpl implements TranslationApplyManager {

    @Autowired
    private TranslationApplyService translationApplyService;
    @Autowired
    private UserAccountService userAccountService;

    public TranslationApplyManagerImpl(TranslationApplyService translationApplyService, UserAccountService userAccountService) {
        this.translationApplyService = translationApplyService;
        this.userAccountService = userAccountService;
    }

    @Override
    public TranslationApply applyTranslation(ProductRequest productRequest, LanguageEnum fromType) {

        UserAccount userAccount = userAccountService.selectByUserId(productRequest.getUserId());
        TranslationApply translationApply = new TranslationApply();
        translationApply.setReqId(productRequest.getId());
        translationApply.setFromType(fromType);
        translationApply.setOrigType(productRequest.getLanguageType());
        translationApply.setCreateTime(new Date());
        translationApply.setUserId(productRequest.getUserId());
        translationApply.setTitle(productRequest.getTitle());
        if (userAccount.getFreeTranslate() > 0) {
            translationApply.setStatus(TranslationApplyStatusEnum.NORMAL);
            userAccount.setFreeTranslate(userAccount.getFreeTranslate() - 1);
            userAccount.setOldVersion(userAccount.getVersion());
            userAccount.setVersion(userAccount.getVersion() + 1);
            userAccount.setUpdateTime(new Date());
            int i = translationApplyService.applyFreeTranslation(translationApply, userAccount);
            return null;
        } else {
            translationApply.setStatus(TranslationApplyStatusEnum.INIT);
            translationApplyService.save(translationApply);
        }
        return translationApply;
    }

    @Override
    public TranslationApply check(ProductRequest productRequest, LanguageEnum fromType) {
        return  translationApplyService.findReqIdAndFrom(productRequest.getId(),fromType);
    }
}
