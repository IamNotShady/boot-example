package com.boot.user.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
public class UserBean implements Serializable{

    private static final long serialVersionUID = 9129370215157758832L;
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Date create_dt;
    @Column
    private Date update_dt;
    @Column
    private String pwdsalt;
    @Column
    private Date last_login_dt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public Date getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(Date update_dt) {
        this.update_dt = update_dt;
    }

    public String getPwdsalt() {
        return pwdsalt;
    }

    public void setPwdsalt(String pwdsalt) {
        this.pwdsalt = pwdsalt;
    }

    public Date getLast_login_dt() {
        return last_login_dt;
    }

    public void setLast_login_dt(Date last_login_dt) {
        this.last_login_dt = last_login_dt;
    }
}
