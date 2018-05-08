package com.spring.worldwire.controller;

import com.spring.worldwire.enums.ProductRequestStatusEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.service.ProductRequestService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by pg on 2018/5/7.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/productRequest")
public class ProductRequestController {

    private static Logger logger = LoggerFactory.getLogger(ProductRequestController.class);

    private ProductRequestService productRequestService;

    @Autowired
    public ProductRequestController(ProductRequestService productRequestService) {
        this.productRequestService = productRequestService;
    }

    @RequestMapping("/toApply")
    public String toApply(){
        //申请页面
        return "";
    }

    @RequestMapping("/apply")
    public String apply(ProductRequest productRequest){
        //todo 申请页面
        Long userId = 0L;
        if(check(productRequest)){
            productRequest.setStatus(ProductRequestStatusEnum.ON);
            productRequest.setUserId(userId);
            int saveFlag = productRequestService.save(productRequest);
            if(saveFlag>0){
                return "";
            }
        }else{
            logger.info("[发布需求] 申请信息数据校验不完整");
        }

        return "";
    }

    /**
     * 校验是否参数是否完整
     * @param productRequest 校验参数
     * @return 返回结果
     */
    private boolean check(ProductRequest productRequest) {
        return StringUtils.isNotBlank(productRequest.getContent())&&
                StringUtils.isNotBlank(productRequest.getTitile());
    }


}
