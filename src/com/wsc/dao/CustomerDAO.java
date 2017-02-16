package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Customer;
import com.wsc.parentbean.Pager;

public interface CustomerDAO {

	/**
	 * 分页查询所有用户
	 * @return
	 */
	public Pager<Customer> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 根据邮箱查询用户表的数据
	 * @param email
	 * @return
	 */
	public Customer queryCustomer(String email);
	
	/**
	 * 根据Customer对象更新用户表的数据
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * 根据邮箱或名称查询用户表的数据
	 */
	public List<Customer> conditionQueryCustomer(String email, String name);
	
	/**
	 * 根据Customer对象向数据库添加数据，用户注册
	 * @param customer
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * 根据邮箱和密码查询数据库
	 * @param email
	 * @param password
	 * @return
	 */
	public Customer queryCustomer(String email, String password);
}
