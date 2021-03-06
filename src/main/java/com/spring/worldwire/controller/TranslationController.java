package com.spring.worldwire.controller;

import com.spring.worldwire.config.BaseConfig;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.manager.TranslationApplyManager;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.TranslationApplyQuery;
import com.spring.worldwire.result.ResponseResult;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.TranslationApplyService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.WordsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/translation")
public class TranslationController {

	private static Logger logger = LoggerFactory.getLogger(TranslationController.class);

	private TranslationApplyService translationApplyService;

	private ProductRequestService productRequestService;

	private UserAccountService userAccountService;

	private TranslationApplyManager translationApplyManager;
	@Autowired
	public TranslationController(TranslationApplyService translationApplyService, ProductRequestService productRequestService
			,UserAccountService userAccountService,TranslationApplyManager translationApplyManager) {
		this.translationApplyService = translationApplyService;
		this.productRequestService = productRequestService;
		this.userAccountService = userAccountService;
		this.translationApplyManager = translationApplyManager;
	}

	@RequestMapping("list")
	public String showHistory(Model model, HttpServletRequest request, TranslationApplyQuery translationApplyQuery){

		Long userId = (Long)request.getAttribute(Constants.USER_ID_SESSION);
		translationApplyQuery.setUserId(userId);


		int translationCount = translationApplyService.pageCount(translationApplyQuery);
		List<TranslationApply> translationApplyList = translationApplyService.page(translationApplyQuery);

		model.addAttribute("translationCount",translationCount);
		model.addAttribute("translationApplyList",translationApplyList);

		return "pc/translationList";
	}


	@RequestMapping("/apply")
	@ResponseBody
	public ResponseResult<TranslationApply> apply(Long reqId,Integer fromType){
		if(reqId==null||fromType==null){
			logger.info("[翻译申请] 请求参数有误");
			return new ResponseResult<>(null,StatusCodeEnum.FAIL.getCode(),"请求参数有误");
		}
		ProductRequest productRequest = productRequestService.findById(reqId);
		if(productRequest==null){
			logger.info("[翻译申请] 请求参数有误 reqId = {} fromType = {}",reqId,fromType);
			return new ResponseResult<>(null,StatusCodeEnum.FAIL.getCode(),"请求参数有误");
		}
		if(productRequest.getLanguageType().getCode()==fromType){
			return new ResponseResult<>(null,StatusCodeEnum.FAIL.getCode(),"不能翻译成本语言");
		}

		try {
			TranslationApply translationApply = translationApplyManager.check(productRequest,LanguageEnum.getNameByCode(fromType));
			if(translationApply!=null){
				return new ResponseResult<>(null,StatusCodeEnum.FAIL.getCode(),"已经申请翻译");
			}
			translationApply = translationApplyManager.applyTranslation(productRequest, LanguageEnum.getNameByCode(fromType));
			if(translationApply==null){
				logger.info("[翻译申请] 报错数据异常 reqId = {} fromType = {}",reqId,fromType);
				return new ResponseResult<>(null,StatusCodeEnum.FAIL.getCode(),"请求参数有误");
			}
			if(translationApply.getStatus().equals(TranslationApplyStatusEnum.INIT)) {
				return new ResponseResult<>(translationApply, StatusCodeEnum.SUCCESS.getCode(), "缴费后翻译");
			}else{
				return new ResponseResult<>(translationApply, StatusCodeEnum.SUCCESS.getCode(), "请等待翻译");
			}
		} catch (Exception e) {
			logger.error("[翻译申请] 报错数据异常 reqId = {} fromType = {}",reqId,fromType,e);
		}

		return new ResponseResult<>(null,StatusCodeEnum.ERROR.getCode(),"请求出错");
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
