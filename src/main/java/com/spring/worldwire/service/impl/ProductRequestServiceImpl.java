package com.spring.worldwire.service.impl;

import com.spring.worldwire.dao.ProductRequestDao;
import com.spring.worldwire.dao.UserInfoDao;
import com.spring.worldwire.model.ProductRequest;
import com.spring.worldwire.query.ProductRequestQuery;
import com.spring.worldwire.service.ProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ProductRequestServiceImpl implements ProductRequestService {
    @Autowired
    private ProductRequestDao productRequestDao;
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public ProductRequest findById(Long id) {
        ProductRequest request = productRequestDao.selectByPrimaryKey(id);
        request.setUserInfo(userInfoDao.selectSimpleById(request.getUserId()));
        return request;
    }

    @Override
    public int save(ProductRequest productRequest) {
        return productRequestDao.insert(productRequest);
    }

    @Override
    public List<ProductRequest> selectByQuery(ProductRequestQuery query, boolean userInfo) {
        if (Objects.isNull(query)) {
            return null;
        }
        List<ProductRequest> list = productRequestDao.selectByQuery(query);
        if (!userInfo || CollectionUtils.isEmpty(list)) {
            return list;
        }
        list.stream().forEach(request -> {
            request.setUserInfo(userInfoDao.selectSimpleById(request.getUserId()));
        });
        return list;
    }

    @Override
    public int selectCountByQuery(ProductRequestQuery query) {
        return productRequestDao.selectCountByQuery(query);
    }

    @Override
    public int updateStatus(ProductRequest productRequest) {
        return productRequestDao.updateStatus(productRequest);
    }
}
