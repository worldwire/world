package com.spring.worldwire.controller;

import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductRequestService productRequestService;

    @RequestMapping("")
    public String toIndex(Model model) {

        ProductRequestQuery personalQuery = new ProductRequestQuery();
        personalQuery.setPageSize(4);
        personalQuery.setUserType(UserTypeEnum.PERSONAL.getCode());
        List<ProductRequest> personalList = productRequestService.selectByQuery(personalQuery);

        ProductRequestQuery enterpriseQuery = new ProductRequestQuery();
        enterpriseQuery.setPageSize(4);
        enterpriseQuery.setUserType(UserTypeEnum.ENTERPRISE.getCode());
        List<ProductRequest> enterpriseList = productRequestService.selectByQuery(enterpriseQuery);

        model.addAttribute("personalList", personalList);
        model.addAttribute("enterpriseList", enterpriseList);
        return "index";
    }
}
