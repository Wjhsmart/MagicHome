package com.wsc.dao;

import com.wsc.bean.CompanyCaseCol;
import com.wsc.parentbean.Pager;

public interface CompanyCaseColDAO {
	
	/**
	 * 分页查询指定Id的装修公司收藏
	 * @return
	 */
	public Pager<CompanyCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据CompanyCaseCol对象插入数据
	 * @param CompanyCaseCol
	 */
	public void addCompanyCaseCol(CompanyCaseCol companyCaseCol);
}
