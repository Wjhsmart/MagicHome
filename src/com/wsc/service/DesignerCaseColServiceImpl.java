package com.wsc.service;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.DesignerCaseColDAO;
import com.wsc.dao.DesignerCaseColDAOImpl;
import com.wsc.parentbean.Pager;

public class DesignerCaseColServiceImpl implements DesignerCaseColService {

	private DesignerCaseColDAO designerCaseColDAO;
	private CommonDAO commonDAO;
	
	public DesignerCaseColServiceImpl() {
		designerCaseColDAO = new DesignerCaseColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return designerCaseColDAO.queryByPage(pageNo, pageSize, customerId);
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
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol) {
		designerCaseColDAO.addDesignerCaseCol(designerCaseCol);
	}


}
