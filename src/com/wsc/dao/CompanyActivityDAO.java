package com.wsc.dao;

import java.util.List;

import com.wsc.bean.CompanyActivity;
import com.wsc.parentbean.Pager;

public interface CompanyActivityDAO {

	/**
	 * ��ҳ��ѯ����װ�޹�˾�
	 * @return
	 */
	public Pager<CompanyActivity> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ��ӻ
	 * @param activity
	 */
	public void addActivity(CompanyActivity activity);
	
	/**
	 * ����id��ѯ�
	 * @param id
	 */
	public CompanyActivity queryActById(String id);
	
	/**
	 * ����activity������»
	 * @param activity
	 */
	public void updateAct(CompanyActivity activity);
	
	/**
	 * ����װ�޹�˾id��ѯװ�޹�˾����ܸ���
	 * @param company_id
	 * @return
	 */
	public int queryCountCompanyId(String company_id);
	
	/**
	 * ����װ�޹�˾id��ҳ��ѯ����װ�޹�˾�
	 * @return
	 */
	public Pager<CompanyActivity> queryByCompanyId(int pageNo, int pageSize, String company_id);
	
	/**
	 * ����װ�޹�˾id��ѯװ�޹�˾���ǰ4������
	 * @param company_id
	 * @return
	 */
	public List<CompanyActivity> queryByTop4(String company_id);
	
	/**
	 * ����ָ��������ѯ���
	 * @param company_name
	 * @param case_name
	 * @param plot_name
	 * @return
	 */
	public List<CompanyActivity> queryCompanyActivityByCondition(String company_name, String activity_name);
}
