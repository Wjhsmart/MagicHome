package com.wsc.service;

import java.util.List;

import com.wsc.bean.Admin;
import com.wsc.dao.AdminDAO;
import com.wsc.dao.AdminDAOImpl;
import com.wsc.parentbean.Pager;

public class AdminServiceImpl implements AdminService {

	private AdminDAO adminDAO;
	
	
	public AdminServiceImpl() {
		adminDAO = new AdminDAOImpl();
		
	}
	
	@Override
	public List<Admin> queryAll() {
		return adminDAO.queryAll();
	}

	@Override
	public Admin queryCurr(String name, String pwd) {
		return adminDAO.queryCurr(name, pwd);
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDAO.updateAdmin(admin);
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDAO.addAdmin(admin);
	}

	@Override
	public Pager<Admin> queryByPage(int pageNo, int pageSize) {
		return adminDAO.queryByPage(pageNo, pageSize);
	}

	

	@Override
	public Admin queryAdmin(String email) {
		return adminDAO.queryAdmin(email);
	}

	

	@Override
	public List<Admin> conditionQueryAdmin(String email, String name) {
		return adminDAO.conditionQueryAdmin(email, name);
	}

	

	
}
