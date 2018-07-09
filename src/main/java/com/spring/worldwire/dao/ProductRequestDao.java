package com.spring.worldwire.dao;

import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRequestDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductRequest record);

    int insertSelective(ProductRequest record);

    ProductRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductRequest record);

    int updateByPrimaryKey(ProductRequest record);

    List<ProductRequest> selectByQuery(@Param("query") ProductRequestQuery query);
}