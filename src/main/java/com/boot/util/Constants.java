package com.boot.util;

public class Constants {

    /**
     * 需要拦截session是否失效的控制器数组
     */
    public static final String[] intercepter_array = {"bas"};

    /**
     * shiro
     */
    public static final String DEFAULT_ROLES_KEY = "roles";
    public static final String DEFAULT_PERMS_KEY = "perms";
    // 成功认证的用户，默认的角色：（适用没有资源授权的用户）
    public static final String DEFAULT_ROLE = "user";
    // 密码在map中的key
    public static final String DEFAULT_PWD_KEY = "password";
    // 用户身份唯一标识在Map中的key
    public static final String DEFAULT_IDENTITY_KEY = "username";
    public static final String DEFAULT_SALT_KEY = "salt";

    /**
     * 接口返回错误号
     */
    public static String SUCCESS_FLAG = "0";
    public static String SUCCESS_MSG = "成功!";
    public static String SYS_FAIL_FLAG = "1";
    public static String SYS_FAIL_MSG = "系统错误!";

}
