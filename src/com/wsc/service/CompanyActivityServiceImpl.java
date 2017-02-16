package com.wsc.service;

import java.util.List;

import com.wsc.bean.CompanyActivity;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.CompanyActivityDAO;
import com.wsc.dao.CompanyActivityDAOImpl;
import com.wsc.parentbean.Pager;

public class CompanyActivityServiceImpl implements CompanyActivityService {

	private CompanyActivityDAO companyActivityDAO;
	private CommonDAO commonDAO;
	
	public CompanyActivityServiceImpl() {
		companyActivityDAO = new CompanyActivityDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<CompanyActivity> queryByPage(int pageNo, int pageSize) {
		return companyActivityDAO.queryByPage(pageNo, pageSize);
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
	public void addActivity(CompanyActivity activity) {
		companyActivityDAO.addActivity(activity);
	}

	@Override
	public CompanyActivity queryActById(String id) {
		return companyActivityDAO.queryActById(id);
	}

	@Override
	public void updateAct(CompanyActivity activity) {
		companyActivityDAO.updateAct(activity);
	}

	@Override
	public int queryCountCompanyId(String company_id) {
		return companyActivityDAO.queryCountCompanyId(company_id);
	}

	@Override
	public Pager<CompanyActivity> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		return companyActivityDAO.queryByCompanyId(pageNo, pageSize, company_id);
	}

	@Override
	public List<CompanyActivity> queryByTop4(String company_id) {
		return companyActivityDAO.queryByTop4(company_id);
	}

	@Override
	public List<CompanyActivity> queryCompanyActivityByCondition(String company_name, String activity_name) {
		return companyActivityDAO.queryCompanyActivityByCondition(company_name, activity_name);
	}

}
