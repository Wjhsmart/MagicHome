package com.wsc.service;

import java.util.List;

import com.wsc.bean.Admin;
import com.wsc.parentbean.Pager;

public interface AdminService {

	/**
	 * ��ѯ���й���Ա
	 * @return
	 */
	public List<Admin> queryAll();
	
	/**
	 * ��ѯ��ǰ����Ա��������Ϣ
	 * @param name
	 * @return
	 */
	public Admin queryCurr(String name, String pwd);
	
	/**
	 * ����Admin�����޸���Ϣ
	 * @param admin
	 */
	public void updateAdmin(Admin admin);
	
	/**
	 * ����Admin��������ӹ���Ա
	 * @param admin
	 */
	public void addAdmin(Admin admin);
	
	/**
	 * ��ҳ��ѯ���й���Ա
	 * @return
	 */
	public Pager<Admin> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ��������ȥ��ȡ��Admin����
	 * @param email
	 * @return
	 */
	public Admin queryAdmin(String email);
	
	/**
	 * ������������ѯ����Ա
	 * @param admin
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Admin> conditionQueryAdmin(String email, String name);
	
	
	
}
