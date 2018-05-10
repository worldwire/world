package com.spring.worldwire.controller;

import com.spring.worldwire.config.BaseConfig;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.TranslationApplyService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.WordsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/translation")
public class TranslationController {

	private static Logger logger = LoggerFactory.getLogger(TranslationController.class);

	private TranslationApplyService translationApplyService;

	private ProductRequestService productRequestService;

	private UserAccountService userAccountService;
	@Autowired
	public TranslationController(TranslationApplyService translationApplyService, ProductRequestService productRequestService,UserAccountService userAccountService) {
		this.translationApplyService = translationApplyService;
		this.productRequestService = productRequestService;
		this.userAccountService = userAccountService;
	}


	@RequestMapping("/apply")
	public String apply(Long reqId,Integer fromType){
		if(reqId!=null&&fromType!=null){
			ProductRequest productRequest = productRequestService.findById(reqId);
			if(productRequest!=null){
				try {
					TranslationApply translationApply = translationApplyService.applyTranslation(productRequest, LanguageEnum.getNameByCode(fromType));
					if(translationApply==null){
						logger.info("[翻译申请] 报错数据异常 reqId = {} fromType = {}",reqId,fromType);
					}else{
						//todo 返回正常结果路径
						return "";
					}
				} catch (Exception e) {
					logger.error("[翻译申请] 报错数据异常 reqId = {} fromType = {}",reqId,fromType,e);
				}

			}else{
				logger.info("[翻译申请] 请求参数有误 reqId = {} fromType = {}",reqId,fromType);
			}
		}else{
			logger.info("[翻译申请] 请求参数有误");
		}
		return BaseConfig.BAD_PAGE;
	}

	/**
	 *
	 * @param id 翻译申请的ID
	 * @param freeFlag 是否需要使用免费翻译 true使用 false不使用
	 * @return
	 */
	@RequestMapping("/payTranslation")
	public String payTranslation(Long id,boolean freeFlag){
		//todo 获取用户信息
		Long userId = 0L;
		TranslationApply translationApply = translationApplyService.getById(id);
		if(translationApply!=null){
			ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
			//todo 计算费用
			String content = productRequest.getContent();
			if(freeFlag&&WordsUtils.countWord(content,productRequest.getLanguageType().getEnName())<200){
				//翻译流程通过
				UserAccount userInfo = userAccountService.selectByUserId(userId);
				if(userInfo.getFreeTranslate()>0){
					//
					int i = translationApplyService.payTranslation(id);
					//等待页面
					return "";
				}else{
					//跳转支付页面
					return "";
				}
			}else{
				//跳转支付页面
				return "";
			}
		}
		return BaseConfig.BAD_PAGE;
	}

}
