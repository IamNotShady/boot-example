package com.wtyt.pub.mapper;

import org.springframework.stereotype.Repository;
import com.wtyt.pub.bean.PubUserBean;

@Repository
public interface PubUserMapper {

	PubUserBean getUserByName(String loginName);

}
