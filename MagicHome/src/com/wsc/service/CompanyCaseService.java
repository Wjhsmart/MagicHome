package com.wsc.service;

import java.util.List;

import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.parentbean.Pager;

public interface CompanyCaseService {

	/**
	 * ��ҳ��ѯ���й�˾����
	 * @return
	 */
	public Pager<CompanyCase> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ��ҳ��ѯָ��װ�޹�˾����
	 * @return
	 */
	public Pager<CompanyCase> queryByPageAndCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * ��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName);
	
	/**
	 * ����idɾ��ָ�����е�����
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * ��ѯһ��װ�޹�˾������Ϣ
	 * @return
	 */
	public CompanyCase queryCompanyCase();
	
	/**
	 * ��ѯָ��id��ǰ4��װ�޹�˾������Ϣ
	 * @return
	 */
	public List<CompanyCase> queryCompanyCaseByTop4(String company_id);
	
	/**
	 * ����id��ѯװ�޹�˾������Ϣ
	 * @param id
	 * @return
	 */
	public CompanyCase queryCompanyCaseById(String id);
	
	/**
	 * ����companyCase������°���
	 * @param activity
	 */
	public void updateCase(CompanyCase companyCase);
	
	/**
	 * ����CompanyCase�������װ�ް���
	 */
	public void addCompanyCase(CompanyCase companyCase);
	
	/**
	 * ��ҳ��ѯָ��װ�޹�˾����
	 * @return
	 */
	public Pager<CompanyCase> queryByCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * ����װ�޹�˾id��ѯ��װ�޹�˾�����а�������
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
	
	/**
	 * �����û���id��ѯװ�޹�˾�����ղر�
	 * @param product_id
	 * @return
	 */
	public List<CompanyCaseCol> queryCompanyCaseColByProductId(String customer_id);
	
	/**
	 * ����ָ��������ѯ������
	 * @param company_name
	 * @param case_name
	 * @param plot_name
	 * @return
	 */
	public List<CompanyCase> queryCompanyCaseByCondition(String company_name, String case_name, String plot_name);

}
