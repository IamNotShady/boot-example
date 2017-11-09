package com.boot.pub.mapper;

import org.springframework.stereotype.Repository;
import com.boot.pub.bean.PubUserBean;

@Repository
public interface PubUserMapper {

	PubUserBean getUserByName(String loginName);

}
