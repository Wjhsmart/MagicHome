package com.wsc.dao;

import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerColDAO {

	/**
	 * 分页查询指定Id的装修公司收藏
	 * @return
	 */
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据DesignerCol对象插入数据
	 * @param DesignerCol
	 */
	public void addDesignerCol(DesignerCol designerCol);
}
