package com.github.boot.user.service;

import com.github.boot.common.base.BaseException;
import com.github.boot.user.bean.UserBean;

public interface UserService {

    UserBean getUserByName(UserBean pubUserBean) throws BaseException;
}
