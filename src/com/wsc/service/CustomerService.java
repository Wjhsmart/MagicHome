package com.wsc.service;

import java.util.List;

import com.wsc.bean.Customer;
import com.wsc.parentbean.Pager;

public interface CustomerService {

	/**
	 * ��ҳ��ѯ�����û�
	 * @return
	 */
	public Pager<Customer> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ���������ѯ�û��������
	 * @param email
	 * @return
	 */
	public Customer queryCustomer(String email);
	
	/**
	 * ����Customer��������û��������
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * ������������Ʋ�ѯ�û��������
	 */
	public List<Customer> conditionQueryCustomer(String email, String name);
	
	/**
	 * ����Customer���������ݿ�������ݣ��û�ע��
	 * @param customer
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * ��������������ѯ���ݿ�,�û���¼
	 * @param email
	 * @param password
	 * @return
	 */
	public Customer queryCustomer(String email, String password);
}
