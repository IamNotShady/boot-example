package com.github.boot.shiro.bean;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/10
 */
public class RolesPermsCacheBean {

    private List<String> roles;

    private List<String> perms;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
