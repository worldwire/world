package com.spring.worldwire.controller.platform;

import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.vo.TranslationApplyVO;
import com.spring.worldwire.query.TranslationApplyQuery;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.TranslationApplyService;
import com.spring.worldwire.result.LayuiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/platTranslation")
public class PlatTranslationController {

	private static Logger logger = LoggerFactory.getLogger(PlatTranslationController.class);

	@Autowired
	private TranslationApplyService translationApplyService;
	@Autowired
	private ProductRequestService productRequestService;

	@RequestMapping("toList")
	public String toList(){
		return "platform/translationApply";
	}

	@RequestMapping("toDetail")
	public String toDetail(){
		return "platform/translationApplyDetail";
	}

	@RequestMapping("list")
	@ResponseBody
	public String list(TranslationApplyQuery translationApplyQuery){
		int count = translationApplyService.pageCount(translationApplyQuery);
		List<TranslationApply> list = translationApplyService.page(translationApplyQuery);

		return LayuiResult.formatPageResult(list,count);
	}
	

	@RequestMapping("/translation")
	@ResponseBody
	public String translation(Long id){
		TranslationApply translationApply = translationApplyService.getById(id);
		if(translationApply!=null){
			ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
			TranslationApplyVO translationApplyVO = new TranslationApplyVO();
			translationApplyVO.setContext(productRequest.getContent());
			translationApplyVO.setTitle(productRequest.getTitle());
			translationApplyVO.setReqId(productRequest.getId());
			translationApplyVO.setOrigType(productRequest.getLanguageType());
			translationApplyVO.setFromType(translationApply.getFromType());
			translationApplyVO.setId(translationApply.getId());
			translationApplyVO.setFromReqId(translationApply.getFromReqId());
			//返回这个对象给前端

		}

		return LayuiResult.errResult("未查得");
	}

	@RequestMapping("/submit")
	public String apply(TranslationApplyVO translationApplyVO){
		if(translationApplyVO!=null){
			translationApplyVO.setOperatorId(0L);
			int saveFlag = translationApplyService.translation(translationApplyVO);
			if(saveFlag>0){
				return "";
			}
		}
		return "";
	}

	@RequestMapping("/auditDetail")
	public String auditDetail(Long id){
		TranslationApply translationApply = translationApplyService.getById(id);
		if(translationApply!=null){
			ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
			TranslationApplyVO translationApplyVO = new TranslationApplyVO();
			translationApplyVO.setContext(productRequest.getContent());
			translationApplyVO.setTitle(productRequest.getTitle());
			translationApplyVO.setReqId(productRequest.getId());
			translationApplyVO.setOrigType(productRequest.getLanguageType());
			translationApplyVO.setFromType(translationApply.getFromType());
			translationApplyVO.setId(translationApply.getId());
			translationApplyVO.setFromReqId(translationApply.getFromReqId());
			//返回这个对象给前端

		}

		return "";
	}

	@RequestMapping("/audit")
	public String audit(Long id){
		Long auditId = 0L;
		int i = translationApplyService.updateAudit(id,auditId);
		if(i>0){
			return "";
		}
		return "";
	}

}
