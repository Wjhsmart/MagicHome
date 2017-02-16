package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {
	
	@Override
	public Pager<Product> queryByPage(int pageNo, int pageSize) {
		Pager<Product> pager = new Pager<Product>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_product where id not in(select top " + top1 + " id from t_product) order by supply_id desc");
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
			pager.setResult(products);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}
	
	@Override
	public Pager<Product> queryBySupplyId(int pageNo, int pageSize, String supply_id) {
		Pager<Product> pager = new Pager<Product>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select distinct top " + top + " * from t_product where id not in(select top " + top1 + " id from t_product) and supply_id = ? order by created_time desc");
			ps.setString(1, supply_id);
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
			pager.setResult(products);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public List<Product> queryProductByTop4() {
		List<Product> products = new ArrayList<Product>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product = SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return products;
	}

	@Override
	public List<ProductCol> queryProductColByProductId(String customer_id) {
		List<ProductCol> productCols = new ArrayList<ProductCol>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product_col where customer_id = ?");
			ps.setString(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductCol productCol = new ProductCol();
				productCol = SetDAOUtil.setProductCol(productCol, rs, conn);
				productCols.add(productCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return productCols;
	}

	@Override
	public void updateProduct(Product product) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_product set supply_id = ?, name = ?, price = ?, sale_price = ?, image = ?, des = ?, created_time = ?, status = ? where id = ?");
			ps.setString(1, product.getSupply_id());
			ps.setString(2, product.getName());
			ps.setFloat(3, product.getPrice());
			ps.setFloat(4, product.getSale_price());
			ps.setString(5, product.getImage());
			ps.setString(6, product.getDes());
			ps.setDate(7, new Date(product.getCreated_time().getTime()));
			ps.setString(8, product.getStatus());
			ps.setString(9, product.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Pager<Product> queryByStatus(int pageNo, int pageSize, String status) {
		Pager<Product> pager = new Pager<Product>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_product where id not in(select top " + top1 + " id from t_product) and status = ? order by created_time desc");
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
			pager.setResult(products);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public int queryCountByStatus(String status) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_product where status = ?");
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}


}
