package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * װ�޹�˾�����ղر��̳����ղ���
 * @author Administrator
 *
 */
public class CompanyCaseCol extends CollectBean {
	
	private String case_id; //װ�޹�˾�������
	private CompanyCase companyCase; // װ�޹�˾����

	public CompanyCase getCompanyCase() {
		return companyCase;
	}

	public void setCompanyCase(CompanyCase companyCase) {
		this.companyCase = companyCase;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	@Override
	public String toString() {
		return "CompanyCaseCol [case_id=" + case_id + "]";
	}

}
