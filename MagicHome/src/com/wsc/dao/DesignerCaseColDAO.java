package com.wsc.dao;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseColDAO {
	
	/**
	 * 分页查询指定Id的装修公司收藏
	 * @return
	 */
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据DesignerCaseCol对象插入数据
	 * @param DesignerCaseCol
	 */
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol);
}
