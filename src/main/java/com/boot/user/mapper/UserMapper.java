package com.boot.user.mapper;

import org.springframework.stereotype.Repository;
import com.boot.user.bean.UserBean;

@Repository
public interface UserMapper {

	UserBean getUserByName(String loginName);

}
