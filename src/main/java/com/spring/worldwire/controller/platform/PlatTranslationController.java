package com.spring.worldwire.controller.platform;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;
import com.spring.worldwire.model.AdminUser;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.TranslationApply;
import com.spring.worldwire.model.vo.TranslationApplyVO;
import com.spring.worldwire.query.TranslationApplyQuery;
import com.spring.worldwire.result.LayuiResult;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.TranslationApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
	public String toDetail(Model model, long id){
		TranslationApply translationApply = translationApplyService.getById(id);
		ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
		model.addAttribute("translationApply",translationApply);
		model.addAttribute("productRequest",productRequest);
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
			/*TranslationApplyVO translationApplyVO = new TranslationApplyVO();
			translationApplyVO.setContext(productRequest.getContent());
			translationApplyVO.setTitle(productRequest.getTitle());
			translationApplyVO.setReqId(productRequest.getId());
			translationApplyVO.setOrigType(productRequest.getLanguageType());
			translationApplyVO.setFromType(translationApply.getFromType());
			translationApplyVO.setId(translationApply.getId());
			translationApplyVO.setFromReqId(translationApply.getFromReqId());*/
			//返回这个对象给前端

		}

		return LayuiResult.errResult("未查得");
	}

	@RequestMapping("/submit")
	@ResponseBody
	public String apply(HttpServletRequest request,TranslationApplyVO translationApplyVO){
		AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_SESSION);
		translationApplyVO.setOperatorId(adminUser.getId());
		translationApplyVO.setOperatorName(adminUser.getUserName());
		translationApplyVO.setOperatorTime(new Date());
		int saveFlag = translationApplyService.translation(translationApplyVO);
		if(saveFlag>0) {
			return LayuiResult.sussceResult();
		}
		return LayuiResult.errResult("系统错误");
	}

	@RequestMapping("/toAudit")
	public String auditDetail(Long id,Model model){
		TranslationApply translationApply = translationApplyService.getById(id);
		if(translationApply!=null){
			ProductRequest productRequest = productRequestService.findById(translationApply.getReqId());
			ProductRequest newProductRequest = productRequestService.findById(translationApply.getFromReqId());
			model.addAttribute("newProductRequest",newProductRequest);
			model.addAttribute("productRequest",productRequest);
		}
		model.addAttribute("translationApply",translationApply);
		return "platform/translationApplyAudit";
	}

	@RequestMapping("/audit")
	@ResponseBody
	public String audit(Long id,int status,HttpServletRequest request){
		AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_SESSION);
		TranslationApplyStatusEnum auditStatus = TranslationApplyStatusEnum.getNameByCode(status);
		if(auditStatus==null){
			return LayuiResult.errResult("状态错误");
		}
		if(status!=3&&status!=4){
			return LayuiResult.errResult("状态错误");
		}
		TranslationApply translationApply = translationApplyService.getById(id);
		if(TranslationApplyStatusEnum.AUDITION.equals(translationApply.getStatus())){
			translationApply.setStatus(auditStatus);
			translationApply.setAuditorName(adminUser.getUserName());
			translationApply.setAuditorId(adminUser.getId());
			translationApply.setAuditorTime(new Date());
			translationApplyService.updateAudit(translationApply);
			return LayuiResult.sussceResult();
		}else{
			return LayuiResult.errResult("不可进行审核");
		}

	}

}
