package com.wsc.service;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
import com.wsc.dao.SupplyDAO;
import com.wsc.dao.SupplyDAOImpl;
import com.wsc.parentbean.Pager;

public class SupplyServiceImpl implements SupplyService {

	private SupplyDAO supplyDAO;
	
	public SupplyServiceImpl() {
		supplyDAO = new SupplyDAOImpl();
	}
	
	@Override
	public Pager<Supply> queryByPage(int pageNo, int pageSize, String checked) {
		return supplyDAO.queryByPage(pageNo, pageSize, checked);
	}

	@Override
	public List<Supply> querySupAll() {
		return supplyDAO.querySupAll();
	}

	@Override
	public Supply queryCurr(String email, String pwd) {
		return supplyDAO.queryCurr(email, pwd);
	}

	@Override
	public void addSupply(Supply supply) {
		supplyDAO.addSupply(supply);
	}

	@Override
	public void updateSupply(Supply supply) {
		supplyDAO.updateSupply(supply);
	}

	@Override
	public Pager<Product> queryByPage(int pageNo, int pageSize) {
		return supplyDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public void addProduct(Product product) {
		supplyDAO.addProduct(product);
	}
	
	@Override
	public Product queryProById(String id) {
		return supplyDAO.queryProById(id);
	}

	@Override
	public void deleteProById(String id) {
		supplyDAO.deleteProById(id);
	}
	
	@Override
	public Pager<SupplyActivity> queryByActivityPage(int pageNo, int pageSize) {
		return supplyDAO.queryByActivityPage(pageNo, pageSize);
	}

	@Override
	public void addActivity(SupplyActivity activity) {
		supplyDAO.addActivity(activity);
	}

	@Override
	public void deleteActByid(String id) {
		supplyDAO.deleteActById(id);
	}

	@Override
	public SupplyActivity queryActById(String id) {
		return supplyDAO.queryActById(id);
	}

	@Override
	public void updateAct(SupplyActivity activity) {
		supplyDAO.updateAct(activity);
	}

	@Override
	public Company queryCurrCom(String email, String pwd) {
		return supplyDAO.queryCurrCom(email, pwd);
	}

	@Override
	public void updateCompany(Company company) {
		supplyDAO.updateCompany(company);
	}

	@Override
	public void addCompany(Company company) {
		supplyDAO.addCompany(company);
	}

	@Override
	public Supply querySupplyByEmail(String email) {
		return supplyDAO.querySupplyByEmail(email);
	}




}
