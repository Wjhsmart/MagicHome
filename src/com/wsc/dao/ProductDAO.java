package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.parentbean.Pager;

public interface ProductDAO {

	/**
	 * ��ҳ��ѯ���н�����Ʒ
	 * @return
	 */
	public Pager<Product> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ��ҳ��ѯ����ָ���Ƿ��ϼܵĽ�����Ʒ
	 * @return
	 */
	public Pager<Product> queryByStatus(int pageNo, int pageSize, String status);
	
	/**
	 * ��������ָ���Ƿ��ϼ���Ʒ�ĸ���
	 * @return
	 */
	public int queryCountByStatus(String status);
	
	/**
	 * ��ҳ��ѯָ�������̵���Ʒ
	 * @return
	 */
	public Pager<Product> queryBySupplyId(int pageNo, int pageSize, String supply_id);
	
	/**
	 * ��ѯǰ4����Ʒ��¼
	 * @return
	 */
	public List<Product> queryProductByTop4();
	
	/**
	 * �����û���id��ѯ��Ʒ�ղر�
	 * @param product_id
	 * @return
	 */
	public List<ProductCol> queryProductColByProductId(String customer_id);
	
	/**
	 * ����product���������Ʒ
	 * @param product
	 */
	public void updateProduct(Product product);
	
	
	
}
