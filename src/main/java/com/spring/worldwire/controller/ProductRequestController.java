package com.spring.worldwire.controller;

import com.spring.worldwire.enums.ProductRequestStatusEnum;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserCheck;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserCheckService;
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

    private UserCheckService userCheckService;

    private UserAccountService userAccountService;

    @Autowired
    public ProductRequestController(ProductRequestService productRequestService,UserCheckService userCheckService,
                                    UserAccountService userAccountService) {
        this.productRequestService = productRequestService;
        this.userCheckService = userCheckService;
        this.userAccountService = userAccountService;
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

    @RequestMapping("/detail")
    public String detail(Long reqId){

        ProductRequest productRequest = productRequestService.findById(reqId);

        return "";
    }

    @RequestMapping("/checkProductRequest")
    public String checkProductRequest(Long reqId){
        Long userId = 0L;

        UserCheck userCheck = userCheckService.checkUserCheck(userId, reqId);
        if(userCheck!=null&&userCheck.getId()!=null){
            return "";
        }else{
            //循环执行三次，内部是乐观锁实现
            for(int i = 0 ; i <3 ;i++) {
                UserAccount userAccount = userAccountService.selectByUserId(userId);
                if (userAccount.getViewingTimes() > 0) {
                    if(userCheckService.checkProductRequest(userAccount,reqId)>0){
                        break;
                    }
                    logger.info("[申请查看] userId={} reqId={} 更新失败",userId,reqId);
                } else {
                    //支付页面
                    return "";
                }
            }
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
                StringUtils.isNotBlank(productRequest.getTitle());
    }


}
