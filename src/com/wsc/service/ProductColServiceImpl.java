package com.wsc.service;

import com.wsc.bean.ProductCol;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.dao.ProductColDAO;
import com.wsc.dao.ProductColDAOImpl;
import com.wsc.parentbean.Pager;

public class ProductColServiceImpl implements ProductColService {

	private ProductColDAO productColDAO;
	private CommonDAO commonDAO;
	
	public ProductColServiceImpl() {
		productColDAO = new ProductColDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public Pager<ProductCol> queryByPage(int pageNo, int pageSize, String customerId) {
		return productColDAO.queryByPage(pageNo, pageSize, customerId);
	}

	@Override
	public int queryCount(String tableName, String customerId) {
		return commonDAO.queryCount(tableName, customerId);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}

	@Override
	public void addProductCol(ProductCol productCol) {
		productColDAO.addProductCol(productCol);
	}

	


}
