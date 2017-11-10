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
    private UserMapper userMapper;

    @Override
    @LoggerAnnotation(description="PubUserServiceImpl.getUserByName")
    public UserBean getUserByName(UserBean userBean)
            throws BaseException {
        UserBean bean = userMapper.selectOne(userBean);
        if (bean == null) {
            throw new BaseException("用户在系统中不存在");
        }
        return bean;
    }

}
