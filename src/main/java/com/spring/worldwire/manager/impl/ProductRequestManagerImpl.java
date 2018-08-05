package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.RequestTypeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.manager.ProductRequestManager;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.model.RequestViews;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.model.vo.ProductRequestVo;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import com.spring.worldwire.service.RequestViewsService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/18 17:22
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service
public class ProductRequestManagerImpl implements ProductRequestManager {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RequestViewsService requestViewsService;
    @Autowired
    private ProductRequestService productRequestService;

    @Override
    public UserInfo viewRequestContract(Long userId, Long productRequestId) {

        ProductRequest request = productRequestService.findById(productRequestId);

        RequestViews views = requestViewsService.selectByUserId(userId);
        //已经查看过该需求，直接显示即可
        if (Objects.nonNull(views)) {
            return userInfoService.selectById(request.getUserId());
        }

        // 未查看过需求且当天免费查看尚未使用
        boolean noFreeView = redisUtils.isCacheExists(Constants.CACHE_FREE_LOOK_UP + userId);
        if (!noFreeView) {
            processFreeViews(userId, productRequestId);
            return userInfoService.selectById(request.getUserId());
        }

        // 未查看过需求且免费查看已经被使用
        UserAccount userAccount = userAccountService.selectByUserId(userId);
        if (userAccount.getViewingTimes() <= 0) {//没有付费查看次数
            return null;
        }

        // 有付费查看次数，次数减一，并增加查看记录
        processChargeViews(userId, productRequestId, userAccount);

        return userInfoService.selectById(request.getUserId());

    }

    @Override
    public ProductRequestVo getRequestByQuery(int userType, int requestType, UserTypeEnum userTypeEnum, int pageSize, int pageNo, String key) {
        RequestTypeEnum requestTypeEnum = RequestTypeEnum.getNameByCode(requestType);
        ProductRequestQuery query = new ProductRequestQuery();
        query.setPageSize(pageSize);
        query.setPageNo(pageNo);
        query.setRequestType(requestTypeEnum);
        query.setUserType(userTypeEnum);
        query.setPageCount(productRequestService.selectCountByQuery(query));
        List<ProductRequest> result = productRequestService.selectByQuery(query, true);
        ProductRequestVo vo = new ProductRequestVo();
        vo.setList(result);
        vo.setQuery(query);
        return vo;
    }

    /**
     * 处理付费查看次数
     *
     * @param userId
     * @param productRequestId
     */
    private void processChargeViews(Long userId, Long productRequestId, UserAccount userAccount) {

        userAccount.setViewingTimes(userAccount.getViewingTimes() - 1);
        userAccountService.updateUserAccount(userAccount);

        // 增加一条查看记录
        RequestViews requestViews = new RequestViews();
        requestViews.setUserId(userId);
        requestViews.setRequestId(productRequestId);
        requestViews.setCreateTime(new Date());
        requestViews.setUpdateTime(new Date());
        requestViewsService.insertRequestViews(requestViews);

    }

    /**
     * 处理免费次数的使用情况
     *
     * @param userId
     * @param productRequestId
     */
    private void processFreeViews(Long userId, Long productRequestId) {
        // 缓存中增加已使用的记录
        long remains = DateUtil.getTimeInterval(new Date());
        redisUtils.set(Constants.CACHE_FREE_LOOK_UP + userId, productRequestId, remains);
        // 增加一条查看记录
        RequestViews requestViews = new RequestViews();
        requestViews.setUserId(userId);
        requestViews.setRequestId(productRequestId);
        requestViews.setCreateTime(new Date());
        requestViews.setUpdateTime(new Date());
        requestViewsService.insertRequestViews(requestViews);
    }
}
