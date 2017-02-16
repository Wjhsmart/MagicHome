package com.wsc.bean;

import com.wsc.parentbean.UserBean;

/**
 * 管理员表，继承至用户类
 * @author Administrator
 *
 */
public class Admin extends UserBean {

	private String role; // 角色

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [role=" + role + "]";
	}
	
}
