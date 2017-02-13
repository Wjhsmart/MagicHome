package com.wsc.service;

import com.wsc.bean.SupplyActivity;
import com.wsc.dao.SupplyActivityDAO;
import com.wsc.dao.SupplyActivityDAOImpl;
import com.wsc.parentbean.Pager;

public class SupplyActivityServiceImpl implements SupplyActivityService {

	private SupplyActivityDAO supplyActivityDAO;
	
	public SupplyActivityServiceImpl() {
		supplyActivityDAO = new SupplyActivityDAOImpl();
	}
	
	@Override
	public Pager<SupplyActivity> queryByPage(int pageNo, int pageSize) {
		return supplyActivityDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public Pager<SupplyActivity> queryBySupplyId(int pageNo, int pageSize, String supply_id) {
		return supplyActivityDAO.queryBySupplyId(pageNo, pageSize, supply_id);
	}

}
