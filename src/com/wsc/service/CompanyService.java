package com.wsc.service;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.CompanyCol;
import com.wsc.parentbean.Pager;

public interface CompanyService {

	/**
	 * 分页查询所有指定是否已经审核的公司
	 * @return
	 */
	public Pager<Company> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * 根据条件查询指定是否已经审核的公司
	 * @param email
	 * @param name
	 * @param principal
	 * @return
	 */
	public List<Company> conditionQueryNotAuditCompany(String checked, String email, String name, String principal);

	/**
	 * 根据传递过来company对象更新数据库
	 */
	public void updateCompany(Company company);
	
	/**
	 * 根据邮箱查找装修公司
	 * @param eamil
	 * @return
	 */
	public Company queryCompanyByEmail(String email);
	
	/**
	 * 根据id查询装修公司
	 * @param id
	 * @return
	 */
	public Company queryCompanyById(String id);
	
	/**
	 * 查询前4条装修公司
	 * @return
	 */
	public List<Company> queryCompanyByTop4();
	
	/**
	 * 根据用户的id查询装修公司收藏表
	 * @param product_id
	 * @return
	 */
	public List<CompanyCol> queryCompanyColByProductId(String customer_id);
	
	
}
