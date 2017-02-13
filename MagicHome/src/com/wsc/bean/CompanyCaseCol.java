package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 装修公司案例收藏表，继承至收藏类
 * @author Administrator
 *
 */
public class CompanyCaseCol extends CollectBean {
	
	private String case_id; //装修公司案例编号
	private CompanyCase companyCase; // 装修公司案例

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
