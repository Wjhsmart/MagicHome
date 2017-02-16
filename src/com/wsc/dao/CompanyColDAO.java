package com.wsc.dao;

import com.wsc.bean.CompanyCol;
import com.wsc.parentbean.Pager;

public interface CompanyColDAO {

	/**
	 * 分页查询指定Id的装修公司收藏
	 * @return
	 */
	public Pager<CompanyCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据companyCol对象插入数据
	 * @param productCol
	 */
	public void addCompanyCol(CompanyCol companyCol);
}
