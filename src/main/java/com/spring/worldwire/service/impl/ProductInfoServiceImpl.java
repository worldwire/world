package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductInfoDao;
import com.spring.worldwire.model.ProductInfo;
import com.spring.worldwire.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;


    @Override
    public List<ProductInfo> selectCheckProductList(int code) {
        return productInfoDao.selectCheckProductList(code);
    }
}
