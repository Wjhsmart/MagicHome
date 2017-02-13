package com.wsc.bean;

import com.wsc.parentbean.ActivityBean;

/**
 * 装修公司活动表， 继承至活动类
 * @author Administrator
 *
 */
public class CompanyActivity extends ActivityBean {

	private String company_id; // 装修公司编号
	private Company company; // 装修公司

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "CompanyActivity [company_id=" + company_id + "]";
	}
	
}
