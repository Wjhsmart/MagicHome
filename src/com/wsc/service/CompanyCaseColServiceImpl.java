package com.wsc.service;

import com.wsc.bean.CompanyCaseCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.CompanyCaseColDAO;
import com.wsc.dao.CompanyCaseColDAOImpl;
import com.wsc.parentbean.Pager;

public class CompanyCaseColServiceImpl implements CompanyCaseColService {

	private CompanyCaseColDAO companyCaseColDAO;
	private CommonDAO commonDAO;
	
	public CompanyCaseColServiceImpl() {
		companyCaseColDAO = new CompanyCaseColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<CompanyCaseCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return companyCaseColDAO.queryByPage(pageNo, pageSize, customerId);
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
	public void addCompanyCaseCol(CompanyCaseCol companyCaseCol) {
		companyCaseColDAO.addCompanyCaseCol(companyCaseCol);
	}


}
