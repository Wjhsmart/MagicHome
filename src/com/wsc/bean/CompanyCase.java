package com.wsc.bean;

import com.wsc.parentbean.CaseBean;

/**
 * 装修公司案例表，继承至案例类
 * @author Administrator
 *
 */
public class CompanyCase extends CaseBean {

	private String company_id; // 装修公司编号
	private Company company; // 装修公司信息
	private boolean collected; // 是否被收藏

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CompanyCase [company_id=" + company_id + "]";
	}
	
}
