package com.wsc.service;

import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerColService {

	/**
	 * 分页查询指定Id的收藏装修公司
	 * @return
	 */
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId);
	
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
	 * 根据DesignerCol对象插入数据
	 * @param DesignerCol
	 */
	public void addDesignerCol(DesignerCol designerCol);
}
