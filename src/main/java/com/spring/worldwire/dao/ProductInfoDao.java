package com.spring.worldwire.dao;

import com.spring.worldwire.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    List<ProductInfo> selectCheckProductList(@Param("code") int code,@Param("type") int type);

    List<ProductInfo> selectProductByType(@Param("type") int type);


    ProductInfo selectByList(List<String> types);
}