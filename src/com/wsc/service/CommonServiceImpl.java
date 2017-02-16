package com.wsc.service;

import java.sql.Date;

import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;

public class CommonServiceImpl implements CommonService {

	private CommonDAO commonDAO;
	
	public CommonServiceImpl() {
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public int queryCount(String tableName) {
		return commonDAO.queryCount(tableName);
	}
	
	@Override
	public void deleteData(String email, String tableName) {
		commonDAO.deleteDataEmail(email, tableName);
	}
	
	@Override
	public boolean queryEmail(String email, String tableName) {
		return commonDAO.queryEmail(email, tableName);
	}

	@Override
	public int queryCountByChecked(String checked, String tableName) {
		return commonDAO.queryCountByChecked(checked, tableName);
	}

	@Override
	public void colData(String tableName, String field, String customer_id, String col_id, Date created_time) {
		commonDAO.colData(tableName, field, customer_id, col_id, created_time);
	}

	@Override
	public int queryCountByTableId(String id, String field, String tableName) {
		return commonDAO.queryCountByTableId(id, field, tableName);
	}
}
