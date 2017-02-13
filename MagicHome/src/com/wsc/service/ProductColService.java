package com.wsc.service;

import com.wsc.bean.ProductCol;
import com.wsc.parentbean.Pager;

public interface ProductColService {

	/**
	 * ��ҳ��ѯָ��Id���ղ�װ�޹�˾
	 * @return
	 */
	public Pager<ProductCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ���ݴ��ݽ�����id��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName, String customerId);
	
	/**
	 * ����idɾ��ָ�����е�����
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * ����productCol��������ղ�����
	 * @param productCol
	 */
	public void addProductCol(ProductCol productCol);
}
