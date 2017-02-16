package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

/**
 * 用户预约表，继承至BaseBean
 * 
 * @author Administrator
 *
 */
public class Appointment extends BaseBean {

	private String company_id; // 装修公司的id
	private String name; // 称呼
	private String phone; // 手机
	private String plot_name; // 小区名称
	private float area; // 建筑面积
	private String way; // 装修方式
	private String budget; // 装修预算

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public String getPlot_name() {
		return plot_name;
	}

	public void setPlot_name(String plot_name) {
		this.plot_name = plot_name;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Appointment [name=" + name + ", phone=" + phone + ", plot_name=" + plot_name + ", area=" + area
				+ ", way=" + way + ", budget=" + budget + "]";
	}

}
