package com.wsc.dao;

import java.util.List;

import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.parentbean.Pager;

public interface CompanyCaseDAO {

	/**
	 * 分页查询所有装修公司案例
	 * @return
	 */
	public Pager<CompanyCase> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 分页查询指定装修公司案例
	 * @return
	 */
	public Pager<CompanyCase> queryByPageAndCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * 查询一条装修公司案例信息
	 * @return
	 */
	public CompanyCase queryCompanyCase();
	
	/**
	 * 查询指定id的前4条装修公司案例信息
	 * @return
	 */
	public List<CompanyCase> queryCompanyCaseByTop4(String company_id);
	
	/**
	 * 根据id查询装修公司案例信息
	 * @param id
	 * @return
	 */
	public CompanyCase queryCompanyCaseById(String id);
	
	/**
	 * 根据companyCase对象更新案例
	 * @param activity
	 */
	public void updateCase(CompanyCase companyCase);
	
	/**
	 * 根据CompanyCase对象添加装修案例
	 */
	public void addCompanyCase(CompanyCase companyCase);
	
	/**
	 * 分页查询指定装修公司案例
	 * @return
	 */
	public Pager<CompanyCase> queryByCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * 根据装修公司id查询该装修公司的所有案例个数
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
	
	/**
	 * 根据用户的id查询装修公司案例收藏表
	 * @param product_id
	 * @return
	 */
	public List<CompanyCaseCol> queryCompanyCaseColByProductId(String customer_id);
	
	/**
	 * 根据指定条件查询案例表
	 * @param company_name
	 * @param case_name
	 * @param plot_name
	 * @return
	 */
	public List<CompanyCase> queryCompanyCaseByCondition(String company_name, String case_name, String plot_name);

}
