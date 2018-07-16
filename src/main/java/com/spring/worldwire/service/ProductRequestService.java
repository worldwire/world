package com.spring.worldwire.service;

import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;

import java.util.List;

public interface ProductRequestService {
    ProductRequest findById(Long id);

    int save(ProductRequest productRequest);

    /**
     * 分页查询需求详情
     * @param query
     * @param userInfo true表示会带上用户详细信息
     * @return
     */
    List<ProductRequest> selectByQuery(ProductRequestQuery query, boolean userInfo);

    /**
     * 条件查询需求的总条数
     * @param query
     * @return
     */
    int selectCountByQuery(ProductRequestQuery query);
}
