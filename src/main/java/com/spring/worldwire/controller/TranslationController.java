package com.spring.worldwire.controller;

import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TradeOrder;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.service.PayService;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.TestService;
import com.spring.worldwire.service.TranslationApplyService;
import com.spring.worldwire.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/translation")
public class TranslationController {

	private static Logger logger = LoggerFactory.getLogger(TranslationController.class);

	@Autowired
	private TranslationApplyService translationApplyService;
	@Autowired
	private ProductRequestService productRequestService;


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
		return "";
	}

}
