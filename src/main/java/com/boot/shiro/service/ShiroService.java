package com.boot.shiro.service;

import java.util.Collection;
import java.util.Map;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/10
 */
public interface ShiroService {

    /**
     * 获取用户的角色和权限
     *
     * @return map
     */
    Map<String, Collection<String>> getUserRolesAndPerms(Object uniqueIdentity);

}
