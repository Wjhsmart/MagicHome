package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.CompanyCaseCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CompanyCaseColDAOImpl extends BaseDAO implements CompanyCaseColDAO {

	private CompanyCaseCol setCompanyCaseCol(CompanyCaseCol companyCaseCol, ResultSet rs, Connection conn) throws SQLException {
		companyCaseCol.setId(rs.getString("id"));
		companyCaseCol.setCase_id(rs.getString("case_id"));
		companyCaseCol.setCustomer_id(rs.getString("customer_id"));
		companyCaseCol.setCreated_time(rs.getDate("created_time"));
		companyCaseCol.setCompanyCase(SetDAOUtil.queryCompanyCaseById(conn, rs.getString("case_id")));
		return companyCaseCol;
	}
	
	@Override
	public Pager<CompanyCaseCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<CompanyCaseCol> pager = new Pager<CompanyCaseCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_case_col where id not in(select top " + top1 + " id from t_company_case_col) and customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<CompanyCaseCol> companyCaseCols = new ArrayList<CompanyCaseCol>();
			while (rs.next()) {
				CompanyCaseCol companyCaseCol = new CompanyCaseCol();
				setCompanyCaseCol(companyCaseCol, rs, conn);
				companyCaseCols.add(companyCaseCol);
			}
			pager.setResult(companyCaseCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addCompanyCaseCol(CompanyCaseCol companyCaseCol) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_company_case_col(customer_id, case_id, created_time) values(?, ?, ?)");
			ps.setString(1, companyCaseCol.getCustomer_id());
			ps.setString(2, companyCaseCol.getCase_id());
			ps.setDate(3, new Date(companyCaseCol.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

}
