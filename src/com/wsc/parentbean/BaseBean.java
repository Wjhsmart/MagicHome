package com.wsc.parentbean;

import java.util.Date;

/**
 * 
 * 基类，所有javaBean的父类，包含了所有表都有的字段
 * 
 * @author Administrator
 *
 */
public class BaseBean {

	private String id; // id 编号
	private Date created_time; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	@Override
	public String toString() {
		return "BaseBean [id=" + id + ", created_time=" + created_time + "]";
	}

}
