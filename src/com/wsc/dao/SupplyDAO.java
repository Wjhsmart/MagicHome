package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
import com.wsc.parentbean.Pager;

public interface SupplyDAO {

	/**
	 * 分页查询所有指定是否已经审核的建材商
	 * @return
	 */
	public Pager<Supply> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * 根据传递过来Supply对象更新数据库
	 */
	public void updateSupply(Supply supply);
	
	/**
	 * 查询所有建材商
	 * @return
	 */
	public List<Supply> querySupAll();
	
	/**
	 * 根据邮箱密码查询当前建材商的信息
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Supply queryCurr(String email, String pwd);
	
	/**
	 * 根据supply对象来添加建材商
	 * @param supply
	 */
	public void addSupply(Supply supply);
	
	/**
	 * 分页查询所有商品
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Product> queryByPage(int pageNo, int pageSize);

	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Product> queryProAll();
	
	/**
	 * 根据product对象添加商品
	 * @param product
	 */
	public void addProduct(Product product);
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	public Product queryProById(String id);
	
	/**
	 * 根据id删除商品
	 * @param id
	 * @return
	 */
	public void deleteProById(String id);
	
	/**
	 * 查询所有活动
	 * @return
	 */
	public List<SupplyActivity> queryActAll();
	
	/**
	 * 分页查询所有活动
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<SupplyActivity> queryByActivityPage(int pageNo, int pageSize);
	
	/**
	 * 添加活动
	 * @param activity
	 */
	public void addActivity(SupplyActivity activity);
	
	/**
	 * 删除活动
	 * @param id
	 */
	public void deleteActById(String id);
	
	/**
	 * 根据id查询活动
	 * @param id
	 */
	public SupplyActivity queryActById(String id);
	
	/**
	 * 根据邮箱查询Supply对象
	 * @param email
	 * @return
	 */
	public Supply querySupplyByEmail(String email);
	
	/**
	 * 根据activity对象更新活动
	 * @param activity
	 */
	public void updateAct(SupplyActivity activity);
	
	/**
	 * 根据邮箱密码查询装修公司
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Company queryCurrCom(String email, String pwd);
	
	/**
	 * 根据company更新数据库
	 * @param company
	 */
	public void updateCompany(Company company);
	
	/**
	 * 根据Company添加装修公司
	 * @param company
	 */
	public void addCompany(Company company);
	
}
