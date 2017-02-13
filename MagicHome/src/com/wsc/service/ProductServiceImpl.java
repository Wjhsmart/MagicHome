package com.wsc.service;

import java.util.List;

import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.ProductDAO;
import com.wsc.dao.ProductDAOImpl;
import com.wsc.parentbean.Pager;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;
	private CommonDAO commonDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<Product> queryByPage(int pageNo, int pageSize) {
		return productDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public int queryCount(String tableName) {
		return commonDAO.queryCount(tableName);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}

	@Override
	public List<Product> queryProductByTop4() {
		return productDAO.queryProductByTop4();
	}

	@Override
	public Pager<Product> queryBySupplyId(int pageNo, int pageSize, String supply_id) {
		return productDAO.queryBySupplyId(pageNo, pageSize, supply_id);
	}

	@Override
	public List<ProductCol> queryProductColByProductId(String customer_id) {
		return productDAO.queryProductColByProductId(customer_id);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	@Override
	public Pager<Product> queryByStatus(int pageNo, int pageSize, String status) {
		return productDAO.queryByStatus(pageNo, pageSize, status);
	}

	@Override
	public int queryCountByStatus(String status) {
		return productDAO.queryCountByStatus(status);
	}

}
