package com.wsc.service;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseColService {

	/**
	 * 分页查询指定Id的收藏设计师
	 * @return
	 */
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据传递进来的id查询传递进来的表有多少条数据
	 * @return
	 */
	public int queryCount(String tableName, String customerId);
	
	/**
	 * 根据id删除指定表中的数据
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * 根据DesignerCaseCol对象插入数据
	 * @param DesignerCaseCol
	 */
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol);
}
