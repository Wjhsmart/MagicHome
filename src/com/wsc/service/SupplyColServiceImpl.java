package com.wsc.service;

import com.wsc.bean.SupplyCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.SupplyColDAO;
import com.wsc.dao.SupplyColDAOImpl;
import com.wsc.parentbean.Pager;

public class SupplyColServiceImpl implements SupplyColService {

	private SupplyColDAO supplyColDAO;
	private CommonDAO commonDAO;
	
	public SupplyColServiceImpl() {
		supplyColDAO = new SupplyColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<SupplyCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return supplyColDAO.queryByPage(pageNo, pageSize, customerId);
	}

	@Override
	public int queryCount(String tableName, String customerId) {
		return commonDAO.queryCount(tableName, customerId);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}


}
