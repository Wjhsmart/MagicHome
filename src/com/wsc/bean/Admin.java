package com.wsc.bean;

import com.wsc.parentbean.UserBean;

/**
 * ����Ա���̳����û���
 * @author Administrator
 *
 */
public class Admin extends UserBean {

	private String role; // ��ɫ

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
