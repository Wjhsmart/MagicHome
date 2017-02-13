package com.wsc.service;

import com.wsc.bean.CompanyCol;
import com.wsc.parentbean.Pager;

public interface CompanyColService {

	/**
	 * ��ҳ��ѯָ��Id���ղ�װ�޹�˾
	 * @return
	 */
	public Pager<CompanyCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ���ݴ��ݽ�����id��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName, String customerId);
	
	/**
	 * ����idɾ��ָ�����е�����
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * ����companyCol�����������
	 * @param productCol
	 */
	public void addCompanyCol(CompanyCol companyCol);
}
