package com.wsc.service;

import java.util.List;

import com.wsc.bean.Admin;
import com.wsc.parentbean.Pager;

public interface AdminService {

	/**
	 * 查询所有管理员
	 * @return
	 */
	public List<Admin> queryAll();
	
	/**
	 * 查询当前管理员的所有信息
	 * @param name
	 * @return
	 */
	public Admin queryCurr(String name, String pwd);
	
	/**
	 * 根据Admin对象修改信息
	 * @param admin
	 */
	public void updateAdmin(Admin admin);
	
	/**
	 * 根据Admin对象来添加管理员
	 * @param admin
	 */
	public void addAdmin(Admin admin);
	
	/**
	 * 分页查询所有管理员
	 * @return
	 */
	public Pager<Admin> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 根据邮箱去获取到Admin对象
	 * @param email
	 * @return
	 */
	public Admin queryAdmin(String email);
	
	/**
	 * 根据条件来查询管理员
	 * @param admin
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Admin> conditionQueryAdmin(String email, String name);
	
	
	
}
