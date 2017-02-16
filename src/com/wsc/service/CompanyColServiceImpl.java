package com.wsc.service;

import com.wsc.bean.CompanyCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.CompanyColDAO;
import com.wsc.dao.CompanyColDAOImpl;
import com.wsc.parentbean.Pager;

public class CompanyColServiceImpl implements CompanyColService {

	private CompanyColDAO companyColDAO;
	private CommonDAO commonDAO;
	
	public CompanyColServiceImpl() {
		companyColDAO = new CompanyColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<CompanyCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return companyColDAO.queryByPage(pageNo, pageSize, customerId);
	}

	@Override
	public int queryCount(String tableName, String customerId) {
		return commonDAO.queryCount(tableName, customerId);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}

	@Override
	public void addCompanyCol(CompanyCol companyCol) {
		companyColDAO.addCompanyCol(companyCol);
	}


}
