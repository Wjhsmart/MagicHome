package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.CompanyCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CompanyColDAOImpl extends BaseDAO implements CompanyColDAO {

	private CompanyCol setCompanyCol(CompanyCol companyCol, ResultSet rs, Connection conn) throws SQLException {
		companyCol.setId(rs.getString("id"));
		companyCol.setCustomer_id(rs.getString("customer_id"));
		companyCol.setCompany_id(rs.getString("company_id"));
		companyCol.setCreated_time(rs.getDate("created_time"));
		companyCol.setCompany(SetDAOUtil.queryCompanyById(conn, rs.getString("company_id")));
		return companyCol;
	}
	
	@Override
	public Pager<CompanyCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<CompanyCol> pager = new Pager<CompanyCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_col where id not in(select top " + top1 + " id from t_company_col) and customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<CompanyCol> companyCols = new ArrayList<CompanyCol>();
			while (rs.next()) {
				CompanyCol companyCol = new CompanyCol();
				setCompanyCol(companyCol, rs, conn);
				companyCols.add(companyCol);
			}
			pager.setResult(companyCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addCompanyCol(CompanyCol companyCol) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_company_col(customer_id, company_id, created_time) values(?, ?, ?)");
			ps.setString(1, companyCol.getCustomer_id());
			ps.setString(2, companyCol.getCompany_id());
			ps.setDate(3, new Date(companyCol.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

}
