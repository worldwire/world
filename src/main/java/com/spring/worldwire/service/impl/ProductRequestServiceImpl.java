package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductRequestDao;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductRequestServiceImpl implements ProductRequestService {
    @Autowired
    private ProductRequestDao productRequestDao;

    @Override
    public ProductRequest findById(Long id) {
        return productRequestDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(ProductRequest productRequest) {
        return productRequestDao.insert(productRequest);
    }

    @Override
    public List<ProductRequest> selectByQuery(ProductRequestQuery query) {
        if(Objects.isNull(query)){
            return null;
        }
        return productRequestDao.selectByQuery(query);
    }
}
