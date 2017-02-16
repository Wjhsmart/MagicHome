package com.wsc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.SupplyCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class SupplyColDAOImpl extends BaseDAO implements SupplyColDAO {

	private SupplyCol setSupplyCol(SupplyCol supplyCol, ResultSet rs, Connection conn) throws SQLException {
		supplyCol.setId(rs.getString("id"));
		supplyCol.setCustomer_id(rs.getString("customer_id"));
		supplyCol.setSupply_id(rs.getString("supply_id"));
		supplyCol.setCreated_time(rs.getDate("created_time"));
		supplyCol.setSupply(SetDAOUtil.querySupplyById(conn, rs.getString("supply_id")));
		return supplyCol;
	}
	
	@Override
	public Pager<SupplyCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<SupplyCol> pager = new Pager<SupplyCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_supply_col where id not in(select top " + top1 + "id from t_supply_col) and customer_id = ? order by created_time desc");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<SupplyCol> supplyCols = new ArrayList<SupplyCol>();
			while (rs.next()) {
				SupplyCol supplyCol = new SupplyCol();
				setSupplyCol(supplyCol, rs, conn);
				supplyCols.add(supplyCol);
			}
			pager.setResult(supplyCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

}
