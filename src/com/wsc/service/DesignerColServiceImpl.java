package com.wsc.service;

import com.wsc.bean.DesignerCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.DesignerColDAO;
import com.wsc.dao.DesignerColDAOImpl;
import com.wsc.parentbean.Pager;

public class DesignerColServiceImpl implements DesignerColService {

	private DesignerColDAO designerColDAO;
	private CommonDAO commonDAO;
	
	public DesignerColServiceImpl() {
		designerColDAO = new DesignerColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return designerColDAO.queryByPage(pageNo, pageSize, customerId);
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
	public void addDesignerCol(DesignerCol designerCol) {
		designerColDAO.addDesignerCol(designerCol);
	}


}
