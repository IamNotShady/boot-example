package com.wtyt.util.common;

public final class StringOption {

    /**
     * 判断字符串是否为空 当 字符串 为null、空串、空格组成时 返回 true 其他返回false
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || str.length() == 0
                || str.replaceAll(" ", "").length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 去除 string 前后空格
     */
    public static String removeLeftSpace(String str) {
        if (isEmpty(str)) {
            return null;
        }
        int i = 0;
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != 32) {
                break;
            }
        }
        return str.substring(i);
    }

    /**
     * 去除 string 前后空格
     */
    public static String removeRightSpace(String str) {
        if (isEmpty(str)) {
            return null;
        }
        int i = str.length() - 1;
        for (; i >= 0; i--) {
            char c = str.charAt(i);
            if (c != 32) {
                break;
            }
        }
        return str.substring(0, i + 1);
    }

    /**
     * 首字母大写
     */
    public static String toFirstUpper(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     */
    public static String toFirstLowwer(String str) {
        if (isEmpty(str)) {
            return null;
        }

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 在左边增加字符 以拿到等长字符串 不够指定长度 则在其左侧 加 ch 字符
     */
    public static String addLeftToEqualLength(String str, int length, char ch) {
        if (isEmpty(str)) {
            return null;
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = str.length(); i < length; i++) {
            sb.append(ch);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 在右边增加字符 以得到等长字符串 不够指定长度 则在其右侧 加 ch 字符
     */
    public static String addRightToEqualLength(String str, int length, char ch) {
        if (isEmpty(str)) {
            return null;
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = str.length(); i < length; i++) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * 在左边增加字符 以拿到等长字符串 不够指定长度 则在其左侧 加 0 字符
     */
    public static String addLeftToEqualLength(String str, int length) {
        return addLeftToEqualLength(str, length, '0');
    }

    /**
     * 在左边增加字符 以拿到等长字符串 不够指定长度 则在其右侧 加 0 字符
     */
    public static String addRightToEqualLength(String str, int length) {
        return addRightToEqualLength(str, length, '0');
    }

    /**
     * 将数据库字段名 转换为Java变量的命名规范(驼峰命名)
     */
    public static String columnNameToDeclareVar(String columnName) {
        String[] words = columnName.split("_");
        StringBuilder varName = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            varName.append(toFirstUpper(words[i].toLowerCase()));
        }
        return toFirstLowwer(varName.toString());
    }

    /**
     * 将.xx变成0.xx
     */
    public static String strToNumStr(String str) {
        if (str != null && str.trim().length() > 0
                && ".".equals(str.substring(0, 1))) {
            return "0" + str;
        }
        return str;
    }

    /**
     * 替换单引号
     */
    public static String removerMark(String str) {
        if (str.indexOf("'") > -1) { // 有单引号
            String nstr = str.replace("'", "");
            return nstr;
        }
        return str;
    }

}