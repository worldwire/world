package com.spring.worldwire.dao;

import com.spring.worldwire.model.ProductInfo;

import java.util.List;

public interface ProductInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    List<ProductInfo> selectCheckProductList(int code);
}