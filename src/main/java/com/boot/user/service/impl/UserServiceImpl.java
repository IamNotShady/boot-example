package com.boot.user.service.impl;

import com.boot.common.base.BaseException;
import com.boot.common.aop.LoggerAnnotation;
import com.boot.user.bean.UserBean;
import com.boot.user.mapper.UserMapper;
import com.boot.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper pubUserMapper;

    @Override
    @LoggerAnnotation(description="PubUserServiceImpl.getUserByName")
    public UserBean getUserByName(UserBean pubUserBean)
            throws BaseException, Exception {
        UserBean bean = pubUserMapper.getUserByName(pubUserBean
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
