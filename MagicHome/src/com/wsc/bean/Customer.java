package com.wsc.bean;

import com.wsc.parentbean.UserBean;

/**
 * ҵ�����̳����û���
 * @author Administrator
 *
 */
public class Customer extends UserBean {

	private String plot_name; // С������
	private String address; // ��ַ
	public String getPlot_name() {
		return plot_name;
	}
	public void setPlot_name(String plot_name) {
		this.plot_name = plot_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [plot_name=" + plot_name + ", address=" + address + "]";
	}
	
}
