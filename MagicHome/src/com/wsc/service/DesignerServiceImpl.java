package com.wsc.service;

import java.util.List;

import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCol;
import com.wsc.dao.DesignerDAO;
import com.wsc.dao.DesignerDAOImpl;
import com.wsc.parentbean.Pager;

public class DesignerServiceImpl implements DesignerService {

	private DesignerDAO designerDAO;
	
	public DesignerServiceImpl() {
		designerDAO = new DesignerDAOImpl();
	}
	
	@Override
	public Pager<Designer> queryByPage(int pageNo, int pageSize, String checked) {
		return designerDAO.queryByPage(pageNo, pageSize, checked);
	}

	@Override
	public void updateDesigner(Designer designer) {
		designerDAO.updateDesigner(designer);
	}

	@Override
	public List<Designer> queryDesignerByTop4() {
		return designerDAO.queryDesignerByTop4();
	}

	@Override
	public Designer queryDesignerById(String id) {
		return designerDAO.queryDesignerById(id);
	}

	@Override
	public void addDesigner(Designer designer) {
		designerDAO.addDesigner(designer);
	}

	@Override
	public Designer queryCurr(String email, String pwd) {
		return designerDAO.queryCurr(email, pwd);
	}

	@Override
	public Designer queryDesignerByEmail(String email) {
		return designerDAO.queryDesignerByEmail(email);
	}

	@Override
	public List<DesignerCol> queryDesignerColByCustomerId(String customer_id) {
		return designerDAO.queryDesignerColByCustomerId(customer_id);
	}

	@Override
	public int queryCountByDesignerChecked(String checked) {
		return designerDAO.queryCountByDesignerChecked(checked);
	}

}
