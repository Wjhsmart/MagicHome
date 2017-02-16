package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 装修公司收藏表，继承至收藏类
 * @author Administrator
 *
 */
public class CompanyCol extends CollectBean {
	
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
		return "CompanyCol [company_id=" + company_id + "]";
	}

}
