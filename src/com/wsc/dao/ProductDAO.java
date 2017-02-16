package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.parentbean.Pager;

public interface ProductDAO {

	/**
	 * 分页查询所有建材商品
	 * @return
	 */
	public Pager<Product> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 分页查询所有指定是否上架的建材商品
	 * @return
	 */
	public Pager<Product> queryByStatus(int pageNo, int pageSize, String status);
	
	/**
	 * 计算所有指定是否上架商品的个数
	 * @return
	 */
	public int queryCountByStatus(String status);
	
	/**
	 * 分页查询指定建材商的商品
	 * @return
	 */
	public Pager<Product> queryBySupplyId(int pageNo, int pageSize, String supply_id);
	
	/**
	 * 查询前4条商品记录
	 * @return
	 */
	public List<Product> queryProductByTop4();
	
	/**
	 * 根据用户的id查询商品收藏表
	 * @param product_id
	 * @return
	 */
	public List<ProductCol> queryProductColByProductId(String customer_id);
	
	/**
	 * 根据product对象更新商品
	 * @param product
	 */
	public void updateProduct(Product product);
	
	
	
}
