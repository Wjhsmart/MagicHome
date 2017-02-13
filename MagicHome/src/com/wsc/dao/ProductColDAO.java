package com.wsc.dao;

import com.wsc.bean.ProductCol;
import com.wsc.parentbean.Pager;

public interface ProductColDAO {

	/**
	 * ��ҳ��ѯָ��Id�Ľ�����Ʒ
	 * @return
	 */
	public Pager<ProductCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ����productCol�����������
	 * @param productCol
	 */
	public void addProductCol(ProductCol productCol);
	
}
