package com.boot.util.common;

public class SbConstants {
    /**
     * 分页显示行数
     */
    public static final int PAGE_SIZE = 15;
    public static final int PAGE_SIZE_15 = 15;
    public static final int PAGE_SIZE_20 = 20;
    public static final int PAGE_SIZE_50 = 50;
    public static final int PAGE_SIZE_100 = 100;
    public static final String PAGE_CURRENT = "1";
    /**
     * 需要拦截session是否失效的控制器数组
     */
    public static final String[] intercepter_session_array = {"bas"};
    /**
     * 系统常量
     */
    public static final String USER_KEY = "sb_user";
    public static final String ORG_KEY = "sb_org";
    /**
     * 异常提示
     */
    public static final String SYS_EXCEPTION = "服务器异常";
    /**
     * 接口返回错误号
     */
    public static String SUCCESS_FLAG = "0";
    public static String SUCCESS_MSG = "成功!";
    public static String IP_FAIL_FLAG = "1";
    public static String IP_FAIL_MSG = "IP鉴权失败!";
    public static String FORMAT_FAIL_FLAG = "1";
    public static String FORMAT_FAIL_MSG = "JSON格式异常!";
    public static String SYS_FAIL_FLAG = "1";
    public static String SYS_FAIL_MSG = "系统错误!";
    public static String SIGN_FAIL_FLAG = "1";
    public static String SIGN_FAIL_MSG = "签名验证失败!";
    public static String SV_TYPE_FAIL_FLAG = "1";
    public static String SV_TYPE_FAIL_MSG = "接口类型错误!";
    public static String BEAN_TYPE_FAIL_FLAG = "1";
    public static String BEAN_TYPE_FAIL_MSG = "处理bean没有映射成功,请联系技术人员检查!";
    public static String HIS_TYPE_FAIL_FLAG = "1";
    public static String HIS_TYPE_FAIL_MSG = "没有最新轨迹";
    public static String DATABASE_FAIL_FLAG = "1";
    public static String DATABASE_FAIL_MSG = "数据库异常,请检查字段是否合法!";
}
