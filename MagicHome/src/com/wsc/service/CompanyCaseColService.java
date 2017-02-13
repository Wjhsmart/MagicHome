package com.wsc.service;

import com.wsc.bean.CompanyCaseCol;
import com.wsc.parentbean.Pager;

public interface CompanyCaseColService {

	/**
	 * ��ҳ��ѯָ��Id���ղ�װ�޹�˾
	 * @return
	 */
	public Pager<CompanyCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
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
	 * ����CompanyCaseCol�����������
	 * @param CompanyCaseCol
	 */
	public void addCompanyCaseCol(CompanyCaseCol companyCaseCol);
}
