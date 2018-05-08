package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductRequestDao;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRequestServiceImpl implements ProductRequestService {
    @Autowired
    private ProductRequestDao productRequestDao;

    @Override
    public ProductRequest findById(Long id) {
        return productRequestDao.selectByPrimaryKey(id);
    }
}
