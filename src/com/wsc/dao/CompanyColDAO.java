package com.wsc.dao;

import com.wsc.bean.CompanyCol;
import com.wsc.parentbean.Pager;

public interface CompanyColDAO {

	/**
	 * ��ҳ��ѯָ��Id��װ�޹�˾�ղ�
	 * @return
	 */
	public Pager<CompanyCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ����companyCol�����������
	 * @param productCol
	 */
	public void addCompanyCol(CompanyCol companyCol);
}
