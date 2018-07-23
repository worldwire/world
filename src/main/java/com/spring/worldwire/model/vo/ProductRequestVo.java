package com.spring.worldwire.model.vo;

import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;

import java.util.List;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/23 14:18
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class ProductRequestVo {

    List<ProductRequest> list;

    ProductRequestQuery query;

    public List<ProductRequest> getList() {
        return list;
    }

    public void setList(List<ProductRequest> list) {
        this.list = list;
    }

    public ProductRequestQuery getQuery() {
        return query;
    }

    public void setQuery(ProductRequestQuery query) {
        this.query = query;
    }
}
