package com.boot.bas.bean;


import com.boot.util.bean.BaseBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "t_sb_pro")
public class BasProBean extends BaseBean implements Serializable {

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	@Column
	private String pro_name;
	@Column
	private String pro_code;
	@OrderBy("desc")
	@Column
	private String created_time;
	@Column
	private String last_modified_time;
	@Transient
	private List<BasProBean> proList;

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

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getLast_modified_time() {
		return last_modified_time;
	}

	public void setLast_modified_time(String last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

	public List<BasProBean> getProList() {
		return proList;
	}

	public void setProList(List<BasProBean> proList) {
		this.proList = proList;
	}
}

