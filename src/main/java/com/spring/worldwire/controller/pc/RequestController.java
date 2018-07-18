package com.spring.worldwire.controller.pc;

import com.spring.worldwire.enums.RequestTypeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private ProductRequestService productRequestService;

    @RequestMapping("/list/{userType}/{requestType}/{pageSize}/{pageNo}.html")
    public String productRequestList(Model model, @PathVariable int userType, @PathVariable int requestType, @PathVariable int pageSize, @PathVariable int pageNo) {
        {

            ProductRequestQuery personalQuery = new ProductRequestQuery();
            personalQuery.setPageSize(pageSize);
            personalQuery.setPageNo(pageNo);
            personalQuery.setUserType(userType);
            personalQuery.setRequestType(userType);
            personalQuery.setUserType(UserTypeEnum.PERSONAL.getCode());
            personalQuery.setPageCount(productRequestService.selectCountByQuery(personalQuery));
            List<ProductRequest> personalList = productRequestService.selectByQuery(personalQuery, true);

            ProductRequestQuery enterpriseQuery = new ProductRequestQuery();
            enterpriseQuery.setPageSize(pageSize);
            enterpriseQuery.setPageNo(pageNo);
            enterpriseQuery.setUserType(userType);
            enterpriseQuery.setRequestType(userType);
            enterpriseQuery.setUserType(UserTypeEnum.ENTERPRISE.getCode());
            enterpriseQuery.setPageCount(productRequestService.selectCountByQuery(enterpriseQuery));
            List<ProductRequest> enterpriseList = productRequestService.selectByQuery(enterpriseQuery, true);

            model.addAttribute("personalQuery", personalQuery);
            model.addAttribute("personalList", personalList);

            model.addAttribute("enterpriseQuery", enterpriseQuery);
            model.addAttribute("enterpriseList", enterpriseList);

            return "pc/requestList";
        }
    }

    @RequestMapping("/detail")
    public String toDetail(Model model, Long id) {

        System.out.println("=================" + id);
        ProductRequest productRequest = productRequestService.findById(id);
        model.addAttribute("productRequest", productRequest);
        return "pc/requestDetail";
    }


    @RequestMapping("myDetail")
    public String toMyDetail(Model model, long id) {
        ProductRequest productRequest = productRequestService.findById(id);

        model.addAttribute("productRequest", productRequest);

        return "pc/myDetail";
    }

    @RequestMapping("release")
    public String release(Model model) {

        return "pc/releaseRequest";
    }

    @RequestMapping("releaseCommit")
    public String releaseCommit(Model model, Integer requestType) {

        ProductRequest productRequest = new ProductRequest();

        RequestTypeEnum requestTypeEnum = RequestTypeEnum.getNameByCode(requestType);
        if (requestTypeEnum == null) {
            return "/";
        }

        productRequest.setRequestType(requestTypeEnum);

        productRequestService.save(productRequest);

        return "pc/releaseRequest";
    }
}
