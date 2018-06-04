package com.github.boot.user.bean;

import com.alibaba.fastjson.JSONObject;
import com.github.boot.util.Constants;

public class AjaxBean {

    private String reCode;
    private String reInfo;
    private JSONObject data;

    public AjaxBean() {
        reCode = Constants.SUCCESS_FLAG;
        reInfo = Constants.SUCCESS_MSG;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getReInfo() {
        return reInfo;
    }

    public void setReInfo(String reInfo) {
        this.reInfo = reInfo;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
