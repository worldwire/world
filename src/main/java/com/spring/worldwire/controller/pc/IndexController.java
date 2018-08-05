package com.spring.worldwire.controller.pc;

import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductRequestService productRequestService;

    public IndexController(ProductRequestService productRequestService) {
        this.productRequestService = productRequestService;
    }

    @RequestMapping("")
    public String toIndex(Model model) {

        ProductRequestQuery personalQuery = new ProductRequestQuery();
        personalQuery.setPageSize(4);
        personalQuery.setPageNo(1);
        personalQuery.setUserType(UserTypeEnum.PERSONAL);
        List<ProductRequest> personalList = productRequestService.selectByQuery(personalQuery,false);

        ProductRequestQuery enterpriseQuery = new ProductRequestQuery();
        enterpriseQuery.setPageSize(4);
        enterpriseQuery.setPageNo(1);
        enterpriseQuery.setUserType(UserTypeEnum.ENTERPRISE);
        List<ProductRequest> enterpriseList = productRequestService.selectByQuery(enterpriseQuery,false);

        model.addAttribute("personalList", personalList);
        model.addAttribute("enterpriseList", enterpriseList);
        return "pc/index";
    }

    @RequestMapping("helpCenter")
    public String toHelpCenter(Model model) {
        return "pc/helpCenter";
    }

    @RequestMapping("/requestSearch")
    public String search(String key) throws UnsupportedEncodingException {
        return "/request/0/0/10/1?key="+ URLEncoder.encode(key,"utf8");
    }

}
