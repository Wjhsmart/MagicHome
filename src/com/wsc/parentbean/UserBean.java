package com.wsc.parentbean;

import java.util.Date;

/**
 * 用户类型，继承至BaseBean,是用户类型的父类
 * 
 * @author Administrator
 *
 */
public class UserBean extends BaseBean {

	private String e_mail; // 邮箱
	private String password; // 密码
	private String name; // 名称
	private String phone; // 手机
	private Date last_login_time; // 最近一次登录时间
	private int login_count; // 登录次数
	private String status; // 状态

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public int getLogin_count() {
		return login_count;
	}

	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserBean [e_mail=" + e_mail + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", last_login_time=" + last_login_time + ", login_count=" + login_count + ", status=" + status + "]";
	}

}
