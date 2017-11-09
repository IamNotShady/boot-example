package com.boot.pub.service;

import com.boot.pub.bean.PubUserBean;
import com.boot.util.base.BaseException;

public interface PubUserService {

	PubUserBean getUserByName(PubUserBean pubUserBean) throws BaseException, Exception;
}
