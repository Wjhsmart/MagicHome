package com.wsc.service;

import java.util.List;

import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.DesignerCaseDAO;
import com.wsc.dao.DesignerCaseDAOImpl;
import com.wsc.parentbean.Pager;

public class DesignerCaseServiceImpl implements DesignerCaseService {

	private DesignerCaseDAO designerCaseDAO;
	private CommonDAO commonDAO;
	
	public DesignerCaseServiceImpl() {
		designerCaseDAO = new DesignerCaseDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<DesignerCase> queryByPage(int pageNo, int pageSize) {
		return designerCaseDAO.queryByPage(pageNo, pageSize);
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
	public DesignerCase queryDesignerCase() {
		return designerCaseDAO.queryDesignerCase();
	}

	@Override
	public List<DesignerCase> queryDesignerCaseByTop4(String designer_id) {
		return designerCaseDAO.queryDesignerCaseByTop4(designer_id);
	}

	@Override
	public DesignerCase queryDesignerCaseById(String id) {
		return designerCaseDAO.queryDesignerCaseById(id);
	}

	@Override
	public Pager<DesignerCase> queryDesignerCaseByDesignerId(int pageNo, int pageSize, String designer_id) {
		return designerCaseDAO.queryDesignerCaseByDesignerId(pageNo, pageSize, designer_id);
	}

	@Override
	public int queryCountByDesignerId(String designer_id) {
		return designerCaseDAO.queryCountByDesignerId(designer_id);
	}

	@Override
	public void addDesignerCase(DesignerCase designerCase) {
		designerCaseDAO.addDesignerCase(designerCase);
	}

	@Override
	public void updateCase(DesignerCase designerCase) {
		designerCaseDAO.updateCase(designerCase);
	}

	@Override
	public List<DesignerCaseCol> queryDesignerCaseColByProductId(String customer_id) {
		return designerCaseDAO.queryDesignerCaseColByProductId(customer_id);
	}

}
