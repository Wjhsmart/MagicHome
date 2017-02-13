package com.wsc.service;

import java.util.List;

import com.wsc.bean.Customer;
import com.wsc.dao.CustomerDAO;
import com.wsc.dao.CustomerDAOImpl;
import com.wsc.parentbean.Pager;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;
	
	public CustomerServiceImpl() {
		customerDAO = new CustomerDAOImpl();
	}
	
	@Override
	public Pager<Customer> queryByPage(int pageNo, int pageSize) {
		return customerDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public Customer queryCustomer(String email) {
		return customerDAO.queryCustomer(email);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
	}

	@Override
	public List<Customer> conditionQueryCustomer(String email, String name) {
		return customerDAO.conditionQueryCustomer(email, name);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);
	}

	@Override
	public Customer queryCustomer(String email, String password) {
		return customerDAO.queryCustomer(email, password);
	}

}
