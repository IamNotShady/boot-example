package com.boot.user.service.impl;

import java.util.List;

import com.boot.common.aop.LoggerAnnotation;
import com.boot.common.base.BaseException;
import com.boot.user.bean.UserBean;
import com.boot.user.mapper.UserMapper;
import com.boot.user.service.UserService;
import com.boot.util.ShiroUtils;

import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @LoggerAnnotation(description="PubUserServiceImpl.getUserByName")
    public UserBean getUserByName(UserBean userBean)
            throws BaseException {
        Example userExample = new Example(UserBean.class);
        userExample.createCriteria().andEqualTo("username", userBean.getUsername());
        List<UserBean> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            throw new BaseException("用户在系统中不存在");
        }
        UserBean user = users.get(0);
        AuthenticationToken token = new UsernamePasswordToken(userBean.getUsername(), userBean.getPassword());
        try {
            ShiroUtils.getSubject().login(token);
            ShiroUtils.setAttribute(ShiroUtils.USER_ID, user.getId());
            return user;
        }catch (UnknownAccountException e){
            return null;
        }
    }

}
