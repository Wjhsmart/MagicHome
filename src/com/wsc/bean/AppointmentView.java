package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

/**
 * �û�ԤԼ�鿴���̳���BaseBean
 * 
 * @author Administrator
 *
 */
public class AppointmentView extends BaseBean {

	private String app_id; // ԤԼ���
	private String company_id; // װ�޹�˾���
	private Appointment appointment; // ԤԼ��

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
