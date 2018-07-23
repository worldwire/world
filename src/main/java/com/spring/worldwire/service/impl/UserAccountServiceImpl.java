package com.spring.worldwire.service.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.dao.UserAccountDao;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by luxun on 2018/4/27.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public int insert(UserAccount record) {
        return userAccountDao.insertSelective(record);
    }

    @Override
    public UserAccount selectByUserId(Long userId) {
        return userAccountDao.selectAccountByUserId(userId);
    }

    @Override
    public int updateUserAccount(UserAccount record) {
        Integer version = record.getVersion();
        updateByVersion(record, version);
        return userAccountDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateViewCount(UserAccount record) {
        boolean isShare = redisUtils.isCacheExists(Constants.CACHE_SHARE_LOOK_UP + record.getUserId());
        // 缓存中存在表示当天已经获得免费的分享查看次数
        if(isShare){
            return 0;
        }
        record.setViewingTimes(record.getViewingTimes()+1);
        record.setOldVersion(record.getVersion());
        int result = userAccountDao.updateByPrimaryKeySelective(record);
        if(result >= 1){
            redisUtils.set(Constants.CACHE_SHARE_LOOK_UP + record.getUserId(),record.getUserId(),DateUtil.getTimeInterval(new Date()));
            return result;
        }
        return 0;
    }

    private void updateByVersion(UserAccount record, Integer version) {
        if (version == null) {
            throw new RuntimeException("you need a version recordId =" + record.getId());
        }
        record.setOldVersion(record.getVersion());
        record.setVersion(record.getVersion() + 1);
    }
}
