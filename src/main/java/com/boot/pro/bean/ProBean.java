package com.boot.pro.bean;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.boot.common.base.BaseBean;


@Table(name = "t_pro")
public class ProBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = -2295008401186313139L;

    @Id
    private Long id;
    @Column
    private String pro_name;
    @Column
    private String pro_code;
    @OrderBy("desc")
    @Column
    private String create_dt;
    @Column
    private String update_dt;
    @Transient
    private List<ProBean> proList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(String create_dt) {
        this.create_dt = create_dt;
    }

    public String getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(String update_dt) {
        this.update_dt = update_dt;
    }

    public List<ProBean> getProList() {
        return proList;
    }

    public void setProList(List<ProBean> proList) {
        this.proList = proList;
    }
}

