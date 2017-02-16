package com.wsc.dao;

import com.wsc.bean.ProductCol;
import com.wsc.parentbean.Pager;

public interface ProductColDAO {

	/**
	 * 分页查询指定Id的建材商品
	 * @return
	 */
	public Pager<ProductCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * 根据productCol对象插入数据
	 * @param productCol
	 */
	public void addProductCol(ProductCol productCol);
	
}
