package com.wsc.service;

import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.CompanyCol;
import com.wsc.parentbean.Pager;

public interface CompanyService {

	/**
	 * ��ҳ��ѯ����ָ���Ƿ��Ѿ���˵Ĺ�˾
	 * @return
	 */
	public Pager<Company> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * ����������ѯָ���Ƿ��Ѿ���˵Ĺ�˾
	 * @param email
	 * @param name
	 * @param principal
	 * @return
	 */
	public List<Company> conditionQueryNotAuditCompany(String checked, String email, String name, String principal);

	/**
	 * ���ݴ��ݹ���company����������ݿ�
	 */
	public void updateCompany(Company company);
	
	/**
	 * �����������װ�޹�˾
	 * @param eamil
	 * @return
	 */
	public Company queryCompanyByEmail(String email);
	
	/**
	 * ����id��ѯװ�޹�˾
	 * @param id
	 * @return
	 */
	public Company queryCompanyById(String id);
	
	/**
	 * ��ѯǰ4��װ�޹�˾
	 * @return
	 */
	public List<Company> queryCompanyByTop4();
	
	/**
	 * �����û���id��ѯװ�޹�˾�ղر�
	 * @param product_id
	 * @return
	 */
	public List<CompanyCol> queryCompanyColByProductId(String customer_id);
	
	
}
