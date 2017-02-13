package com.wsc.dao;

import java.util.List;

import com.wsc.bean.CompanyActivity;
import com.wsc.parentbean.Pager;

public interface CompanyActivityDAO {

	/**
	 * 分页查询所有装修公司活动
	 * @return
	 */
	public Pager<CompanyActivity> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 添加活动
	 * @param activity
	 */
	public void addActivity(CompanyActivity activity);
	
	/**
	 * 根据id查询活动
	 * @param id
	 */
	public CompanyActivity queryActById(String id);
	
	/**
	 * 根据activity对象更新活动
	 * @param activity
	 */
	public void updateAct(CompanyActivity activity);
	
	/**
	 * 根据装修公司id查询装修公司活动的总个数
	 * @param company_id
	 * @return
	 */
	public int queryCountCompanyId(String company_id);
	
	/**
	 * 根据装修公司id分页查询所有装修公司活动
	 * @return
	 */
	public Pager<CompanyActivity> queryByCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * 根据装修公司id查询装修公司活动的前4条数据
	 * @param company_id
	 * @return
	 */
	public List<CompanyActivity> queryByTop4(String company_id);
	
	/**
	 * 根据指定条件查询活动表
	 * @param company_name
	 * @param case_name
	 * @param plot_name
	 * @return
	 */
	public List<CompanyActivity> queryCompanyActivityByCondition(String company_name, String activity_name);
}
