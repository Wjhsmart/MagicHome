package com.wsc.bean;

import com.wsc.parentbean.CaseBean;

/**
 * װ�޹�˾�������̳���������
 * @author Administrator
 *
 */
public class CompanyCase extends CaseBean {

	private String company_id; // װ�޹�˾���
	private Company company; // װ�޹�˾��Ϣ
	private boolean collected; // �Ƿ��ղ�

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
