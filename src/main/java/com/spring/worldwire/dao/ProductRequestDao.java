package com.spring.worldwire.dao;

import com.spring.worldwire.model.ProductRequest;

public interface ProductRequestDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductRequest record);

    int insertSelective(ProductRequest record);

    ProductRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductRequest record);

    int updateByPrimaryKey(ProductRequest record);
}