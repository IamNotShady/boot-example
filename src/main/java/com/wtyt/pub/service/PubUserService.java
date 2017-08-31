package com.wtyt.pub.service;

import com.wtyt.pub.bean.PubUserBean;
import com.wtyt.util.base.BaseException;

public interface PubUserService {

	PubUserBean getUserByName(PubUserBean pubUserBean) throws BaseException, Exception;
}
