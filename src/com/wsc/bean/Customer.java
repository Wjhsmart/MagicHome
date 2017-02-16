package com.wsc.bean;

import com.wsc.parentbean.UserBean;

/**
 * 业主表，继承至用户类
 * @author Administrator
 *
 */
public class Customer extends UserBean {

	private String plot_name; // 小区名称
	private String address; // 地址
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
