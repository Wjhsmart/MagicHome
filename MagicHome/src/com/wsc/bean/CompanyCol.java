package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * װ�޹�˾�ղر��̳����ղ���
 * @author Administrator
 *
 */
public class CompanyCol extends CollectBean {
	
	private String company_id; // װ�޹�˾���
	private Company company; // װ�޹�˾

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
