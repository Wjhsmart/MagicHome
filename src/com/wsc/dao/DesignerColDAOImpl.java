package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.DesignerCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class DesignerColDAOImpl extends BaseDAO implements DesignerColDAO {

	private DesignerCol setDesignerCol(DesignerCol designerCol, ResultSet rs, Connection conn) throws SQLException {
		designerCol.setId(rs.getString("id"));
		designerCol.setCustomer_id(rs.getString("customer_id"));
		designerCol.setDesigner_id(rs.getString("designer_id"));
		designerCol.setCreated_time(rs.getDate("created_time"));
		designerCol.setDesigner(SetDAOUtil.queryDesignerById(conn, rs.getString("designer_id")));
		return designerCol;
	}
	
	@Override
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<DesignerCol> pager = new Pager<DesignerCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select distinct top " + top + " * from t_designer_col where id not in(select top " + top1 + " id from t_designer_col) and customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<DesignerCol> DesignerCols = new ArrayList<DesignerCol>();
			while (rs.next()) {
				DesignerCol DesignerCol = new DesignerCol();
				setDesignerCol(DesignerCol, rs, conn);
				DesignerCols.add(DesignerCol);
			}
			pager.setResult(DesignerCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addDesignerCol(DesignerCol designerCol) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_designer_col(customer_id, designer_id, created_time) values(?, ?, ?)");
			ps.setString(1, designerCol.getCustomer_id());
			ps.setString(2, designerCol.getDesigner_id());
			ps.setDate(3, new Date(designerCol.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

}
