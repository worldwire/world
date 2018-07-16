package com.spring.worldwire.service;


import com.spring.worldwire.model.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> selectCheckProductList(int code);
}
