package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.ProductCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class ProductColDAOImpl extends BaseDAO implements ProductColDAO {

	@Override
	public Pager<ProductCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<ProductCol> pager = new Pager<ProductCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_product_col where id not in(select top " + top1 + " id from t_product_col) and customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<ProductCol> productCols = new ArrayList<ProductCol>();
			while (rs.next()) {
				ProductCol productCol = new ProductCol();
				SetDAOUtil.setProductCol(productCol, rs, conn);
				productCols.add(productCol);
			}
			pager.setResult(productCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addProductCol(ProductCol productCol) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_product_col(customer_id, product_id, created_time) values(?, ?, ?)");
			ps.setString(1, productCol.getCustomer_id());
			ps.setString(2, productCol.getProduct_id());
			ps.setDate(3, new Date(productCol.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
}
