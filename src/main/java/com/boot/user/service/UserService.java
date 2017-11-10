package com.boot.user.service;

import com.boot.common.base.BaseException;
import com.boot.user.bean.UserBean;

public interface UserService {

	UserBean getUserByName(UserBean pubUserBean) throws BaseException;
}
