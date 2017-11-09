package com.boot.pub.service.impl;

import com.boot.pub.aop.LoggerAnnotation;
import com.boot.pub.bean.PubUserBean;
import com.boot.pub.mapper.PubUserMapper;
import com.boot.pub.service.PubUserService;
import com.boot.util.base.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubUserServiceImpl implements PubUserService {

    @Autowired
    private PubUserMapper pubUserMapper;

    @Override
    @LoggerAnnotation(description="PubUserServiceImpl.getUserByName")
    public PubUserBean getUserByName(PubUserBean pubUserBean)
            throws BaseException, Exception {
        PubUserBean bean = pubUserMapper.getUserByName(pubUserBean
                .getLogin_name());
        if (bean == null) {
            throw new BaseException("用户在系统中不存在");
        }
        if (!pubUserBean.getPass_word().equals(bean.getPass_word())) {
            throw new BaseException("密码不正确");
        }
        return bean;
    }

}
