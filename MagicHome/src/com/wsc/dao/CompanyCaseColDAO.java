package com.wsc.dao;

import com.wsc.bean.CompanyCaseCol;
import com.wsc.parentbean.Pager;

public interface CompanyCaseColDAO {
	
	/**
	 * ��ҳ��ѯָ��Id��װ�޹�˾�ղ�
	 * @return
	 */
	public Pager<CompanyCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ����CompanyCaseCol�����������
	 * @param CompanyCaseCol
	 */
	public void addCompanyCaseCol(CompanyCaseCol companyCaseCol);
}
