package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
import com.wsc.parentbean.Pager;

public interface SupplyDAO {

	/**
	 * ��ҳ��ѯ����ָ���Ƿ��Ѿ���˵Ľ�����
	 * @return
	 */
	public Pager<Supply> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * ���ݴ��ݹ���Supply����������ݿ�
	 */
	public void updateSupply(Supply supply);
	
	/**
	 * ��ѯ���н�����
	 * @return
	 */
	public List<Supply> querySupAll();
	
	/**
	 * �������������ѯ��ǰ�����̵���Ϣ
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Supply queryCurr(String email, String pwd);
	
	/**
	 * ����supply��������ӽ�����
	 * @param supply
	 */
	public void addSupply(Supply supply);
	
	/**
	 * ��ҳ��ѯ������Ʒ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Product> queryByPage(int pageNo, int pageSize);

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	public List<Product> queryProAll();
	
	/**
	 * ����product���������Ʒ
	 * @param product
	 */
	public void addProduct(Product product);
	
	/**
	 * ����id��ѯ��Ʒ
	 * @param id
	 * @return
	 */
	public Product queryProById(String id);
	
	/**
	 * ����idɾ����Ʒ
	 * @param id
	 * @return
	 */
	public void deleteProById(String id);
	
	/**
	 * ��ѯ���л
	 * @return
	 */
	public List<SupplyActivity> queryActAll();
	
	/**
	 * ��ҳ��ѯ���л
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<SupplyActivity> queryByActivityPage(int pageNo, int pageSize);
	
	/**
	 * ��ӻ
	 * @param activity
	 */
	public void addActivity(SupplyActivity activity);
	
	/**
	 * ɾ���
	 * @param id
	 */
	public void deleteActById(String id);
	
	/**
	 * ����id��ѯ�
	 * @param id
	 */
	public SupplyActivity queryActById(String id);
	
	/**
	 * ���������ѯSupply����
	 * @param email
	 * @return
	 */
	public Supply querySupplyByEmail(String email);
	
	/**
	 * ����activity������»
	 * @param activity
	 */
	public void updateAct(SupplyActivity activity);
	
	/**
	 * �������������ѯװ�޹�˾
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Company queryCurrCom(String email, String pwd);
	
	/**
	 * ����company�������ݿ�
	 * @param company
	 */
	public void updateCompany(Company company);
	
	/**
	 * ����Company���װ�޹�˾
	 * @param company
	 */
	public void addCompany(Company company);
	
}
