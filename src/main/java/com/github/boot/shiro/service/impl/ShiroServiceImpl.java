package com.github.boot.shiro.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.boot.shiro.bean.PermissionBean;
import com.github.boot.shiro.bean.RoleBean;
import com.github.boot.shiro.bean.RolePermissionBean;
import com.github.boot.shiro.bean.RolesPermsCacheBean;
import com.github.boot.shiro.bean.UserRoleBean;
import com.github.boot.shiro.mapper.PermissionMapper;
import com.github.boot.shiro.mapper.RoleMapper;
import com.github.boot.shiro.mapper.RolePermissionMapper;
import com.github.boot.shiro.mapper.UserRoleMapper;
import com.github.boot.shiro.service.ShiroService;
import com.github.boot.user.bean.UserBean;
import com.github.boot.user.mapper.UserMapper;
import com.github.boot.util.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/10
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // REDIS CACHE KEY
    private static final String CACHE_ROLES_PREMS_PREFIX_KEY = "roles_perms_";

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserMapper userMapper;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Map<String, Collection<String>> getUserRolesAndPerms(Object uniqueIdentity) {
        Map<String, Collection<String>> maps = new HashMap<>();
        ;
        String cacheKey = CACHE_ROLES_PREMS_PREFIX_KEY + uniqueIdentity;
        String cacheValues = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isNotBlank(cacheValues)) {
            RolesPermsCacheBean cacheObj = (RolesPermsCacheBean) JSON
                .parseObject(cacheValues, RolesPermsCacheBean.class);
            ;
            maps.put(Constants.DEFAULT_ROLES_KEY, cacheObj.getRoles());
            maps.put(Constants.DEFAULT_PERMS_KEY, cacheObj.getPerms());
            logger.debug("get cache success. roles and perms: {} ==> {}", uniqueIdentity,
                cacheValues);
            return maps;
        }

        RolesPermsCacheBean rolesAndPermsCache = new RolesPermsCacheBean();
        // 查用户和角色关系
        Example userRoleExample = new Example(UserRoleBean.class);
        userRoleExample.createCriteria().andEqualTo("userid", uniqueIdentity);
        List<UserRoleBean> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (!CollectionUtils.isEmpty(userRoles)) {
            Set<Long> roleids = new HashSet<>();
            for (UserRoleBean userRole : userRoles) {
                roleids.add(userRole.getRoleid());
            }
            // 查询角色
            Example roleExample = new Example(RoleBean.class);
            roleExample.createCriteria().andIn("id", roleids);
            List<RoleBean> roles = roleMapper.selectByExample(roleExample);
            if (!CollectionUtils.isEmpty(roles)) {
                List<String> roleNames = new ArrayList<>();
                for (RoleBean roleBean : roles) {
                    roleNames.add(roleBean.getRole_name());
                }
                logger.debug("current user have roles{}", roleNames);
                maps.put(Constants.DEFAULT_ROLES_KEY, roleNames);
                rolesAndPermsCache.setRoles(roleNames);
            }

            // 查角色和权限关系
            Example rolePermissionExample = new Example(RolePermissionBean.class);
            rolePermissionExample.createCriteria().andIn("roleid", roleids);
            List<RolePermissionBean> rolePermissions = rolePermissionMapper
                .selectByExample(rolePermissionExample);
            if (!CollectionUtils.isEmpty(rolePermissions)) {
                Set<Long> permids = new HashSet<>();
                for (RolePermissionBean rolePermission : rolePermissions) {
                    permids.add(rolePermission.getPermid());
                }
                // 查询权限
                Example permissionExample = new Example(PermissionBean.class);
                permissionExample.createCriteria().andIn("roleid", roleids);
                List<PermissionBean> permissions = permissionMapper
                    .selectByExample(permissionExample);
                if (!CollectionUtils.isEmpty(permissions)) {
                    List<String> permNames = new ArrayList<>();
                    for (PermissionBean permission : permissions) {
                        permNames.add(permission.getPerm_name());
                    }
                    logger.debug("current user have an perms{}", permNames);
                    maps.put(Constants.DEFAULT_PERMS_KEY, permNames);
                    rolesAndPermsCache.setPerms(permNames);
                }
            }
        } else {
            // ADD DEFAULT ROLES
            List<String> defaultRoles = new ArrayList<>();
            defaultRoles.add(Constants.DEFAULT_ROLE);
            rolesAndPermsCache.setRoles(defaultRoles);
        }
        redisTemplate.opsForValue().set(cacheKey, rolesAndPermsCache.toString());

        redisTemplate.expire(cacheKey, Constants.GLOBAL_SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
        logger.debug("set cache roles and perms: {} ==> {}", uniqueIdentity, rolesAndPermsCache);
        return maps;
    }

    @Override
    public Map<String, Object> getUserUniqueIdentityAndPassword(String userName) {
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean = userMapper.selectOne(userBean);
        if (userBean != null) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.DEFAULT_IDENTITY_KEY, userBean.getUsername());
            map.put(Constants.DEFAULT_PWD_KEY, userBean.getPassword());
            map.put(Constants.DEFAULT_SALT_KEY, userBean.getPwdsalt());
            return map;
        }
        return null;
    }
}
