package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

/**
 * 用户预约查看表，继承至BaseBean
 * 
 * @author Administrator
 *
 */
public class AppointmentView extends BaseBean {

	private String app_id; // 预约编号
	private String company_id; // 装修公司编号
	private Appointment appointment; // 预约表

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "AppointmentView [app_id=" + app_id + ", company_id=" + company_id + "]";
	}

}
