package com.wsc.service;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.CompanyCol;
import com.wsc.dao.CompanyDAO;
import com.wsc.dao.CompanyDAOImpl;
import com.wsc.parentbean.Pager;

public class CompanyServiceImpl implements CompanyService {

	private CompanyDAO companyDAO;
	
	public CompanyServiceImpl() {
		companyDAO = new CompanyDAOImpl();
	}
	
	@Override
	public Pager<Company> queryByPage(int pageNo, int pageSize, String checked) {
		return companyDAO.queryByPage(pageNo, pageSize, checked);
	}

	@Override
	public List<Company> conditionQueryNotAuditCompany(String checked, String email, String name, String principal) {
		return companyDAO.conditionQueryNotAuditCompany(checked, email, name, principal);
	}

	@Override
	public void updateCompany(Company company) {
		companyDAO.updateCompany(company);
	}

	@Override
	public Company queryCompanyByEmail(String email) {
		return companyDAO.queryCompanyByEmail(email);
	}

	@Override
	public List<Company> queryCompanyByTop4() {
		return companyDAO.queryCompanyByTop4();
	}

	@Override
	public Company queryCompanyById(String id) {
		return companyDAO.queryCompanyById(id);
	}

	@Override
	public List<CompanyCol> queryCompanyColByProductId(String customer_id) {
		return companyDAO.queryCompanyColByProductId(customer_id);
	}

}
