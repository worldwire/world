package com.spring.worldwire.service;

import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;

import java.util.List;

public interface ProductRequestService {
    ProductRequest findById(Long id);

    int save(ProductRequest productRequest);

    List<ProductRequest> selectByQuery(ProductRequestQuery query);
}
