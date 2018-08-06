package com.spring.worldwire.controller.pc;

import com.alibaba.fastjson.JSON;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.ProductRequestStatusEnum;
import com.spring.worldwire.enums.RequestTypeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.manager.ProductRequestManager;
import com.spring.worldwire.manager.UserCenterManager;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.model.vo.ProductRequestVo;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private UserCenterManager userCenterManager;

    @Autowired
    private ProductRequestManager productRequestManager;

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    private UserInfoService userInfoService;

    public RequestController(UserCenterManager userCenterManager, ProductRequestService productRequestService) {
        this.userCenterManager = userCenterManager;
        this.productRequestService = productRequestService;
    }

    @RequestMapping("/list/{userType}/{requestType}/{pageSize}/{pageNo}.html")
    public String productRequestList(Model model, @PathVariable int userType, @PathVariable int requestType, @PathVariable int pageSize, @PathVariable int pageNo, String key) {

        ProductRequestVo personalVo = productRequestManager.getRequestByQuery(UserTypeEnum.PERSONAL, requestType, RequestTypeEnum.getNameByCode(requestType), pageSize, pageNo, key);
        ProductRequestVo enterpriseVo = productRequestManager.getRequestByQuery(UserTypeEnum.ENTERPRISE, requestType, RequestTypeEnum.getNameByCode(requestType), pageSize, pageNo, key);

        model.addAttribute("personalVo", personalVo);
        model.addAttribute("enterpriseVo", enterpriseVo);

        return "pc/requestList";

    }

    @RequestMapping("/list/search")
    @ResponseBody
    public String ajaxProductRequestList(int userType, int requestType, int pageSize, int pageNo, String key) {

        ProductRequestVo request = productRequestManager.getRequestByQuery(UserTypeEnum.getNameByCode(userType), requestType, RequestTypeEnum.getNameByCode(requestType), pageSize, pageNo, key);


        return JSON.toJSONString(request);
    }


    @RequestMapping("/detail")
    public String toDetail(Model model, Long id) {
        ProductRequest productRequest = productRequestService.findById(id);
        productRequest.setViewCount(productRequest.getViewCount() + 1);
        productRequestService.update(productRequest);

        model.addAttribute("productRequest", productRequest);
        return "pc/requestDetail";
    }


    @RequestMapping("myDetail")
    public String toMyDetail(Model model, long id) {
        ProductRequest productRequest = productRequestService.findById(id);

        model.addAttribute("productRequest", productRequest);

        return "pc/myDetail";
    }

    @RequestMapping("/lc/release")
    public String release(Model model) {

        return "pc/releaseRequest";
    }

    @RequestMapping(value = "/save", produces = "text/plain;charset=UTF-8")
    public String save(ProductRequest productRequest, HttpServletRequest request) {

        if (Objects.isNull(productRequest.getId())) {
            UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
            productRequest.setCreateTime(new Date());
            productRequest.setUserType(userInfo.getType());
            productRequest.setStatus(ProductRequestStatusEnum.NORMAL);
            productRequestService.save(productRequest);
        } else {
            productRequestService.update(productRequest);
        }

        return "redirect:/request/lc/history";
    }

    @RequestMapping("/lc/edit")
    public String edit(Long id, Model model, HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);

        ProductRequest productRequest = productRequestService.findById(id);
        if (productRequest.getUserId().intValue() != ((Long) userId).intValue()) {
            return "redirect:/login/";
        }
        model.addAttribute("service", RequestTypeEnum.SERVICE);
        model.addAttribute("request", RequestTypeEnum.REQUEST);
        model.addAttribute("productRequest", productRequest);
        return "pc/releaseDetailEdit";
    }

    @RequestMapping("/lc/releaseCommit")
    public String releaseCommit(Model model, Integer requestType, HttpServletRequest request) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);

        ProductRequest productRequest = new ProductRequest();
        RequestTypeEnum requestTypeEnum = RequestTypeEnum.getNameByCode(requestType);
        if (requestTypeEnum == null) {
            return "/";
        }
        productRequest.setRequestType(requestTypeEnum);
        productRequest.setUserId((Long) userId);

        model.addAttribute("productRequest", productRequest);
        return "pc/releaseDetailAdd";
    }

    @RequestMapping("/lc/history")
    public String releaseHistory(HttpServletRequest request, Model model) {

        Object userId = request.getAttribute(Constants.USER_ID_SESSION);

        Map<String, Object> map = userCenterManager.getUserDetailInfo((Long) userId);
        model.addAllAttributes(map);

        ProductRequestQuery query = new ProductRequestQuery();
        query.setPageSize(10);
        query.setPageNo(1);
        query.setUserId((Long) userId);
        List<ProductRequest> productRequestList = productRequestService.selectByQuery(query, false);

        model.addAttribute("productRequestList", productRequestList);
        return "pc/requestHistory";
    }

}
