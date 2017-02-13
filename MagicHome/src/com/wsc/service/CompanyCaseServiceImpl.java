package com.wsc.service;

import java.util.List;

import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.CompanyCaseDAO;
import com.wsc.dao.CompanyCaseDAOImpl;
import com.wsc.parentbean.Pager;

public class CompanyCaseServiceImpl implements CompanyCaseService {

	private CompanyCaseDAO companyCaseDAO;
	private CommonDAO commonDAO;
	
	public CompanyCaseServiceImpl() {
		companyCaseDAO = new CompanyCaseDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<CompanyCase> queryByPage(int pageNo, int pageSize) {
		return companyCaseDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public int queryCount(String tableName) {
		return commonDAO.queryCount(tableName);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}

	@Override
	public CompanyCase queryCompanyCase() {
		return companyCaseDAO.queryCompanyCase();
	}

	@Override
	public List<CompanyCase> queryCompanyCaseByTop4(String company_id) {
		return companyCaseDAO.queryCompanyCaseByTop4(company_id);
	}

	@Override
	public CompanyCase queryCompanyCaseById(String id) {
		return companyCaseDAO.queryCompanyCaseById(id);
	}

	@Override
	public void updateCase(CompanyCase companyCase) {
		companyCaseDAO.updateCase(companyCase);
	}

	@Override
	public void addCompanyCase(CompanyCase companyCase) {
		companyCaseDAO.addCompanyCase(companyCase);
	}

	@Override
	public Pager<CompanyCase> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		return companyCaseDAO.queryByCompanyId(pageNo, pageSize, company_id);
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		return companyCaseDAO.queryCountByCompanyId(company_id);
	}

	@Override
	public Pager<CompanyCase> queryByPageAndCompanyId(int pageNo, int pageSize, String company_id) {
		return companyCaseDAO.queryByPageAndCompanyId(pageNo, pageSize, company_id);
	}

	@Override
	public List<CompanyCaseCol> queryCompanyCaseColByProductId(String customer_id) {
		return companyCaseDAO.queryCompanyCaseColByProductId(customer_id);
	}

	@Override
	public List<CompanyCase> queryCompanyCaseByCondition(String company_name, String case_name, String plot_name) {
		return companyCaseDAO.queryCompanyCaseByCondition(company_name, case_name, plot_name);
	}


}
