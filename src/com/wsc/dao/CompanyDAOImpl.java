package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.CompanyCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CompanyDAOImpl extends BaseDAO implements CompanyDAO {

	@Override
	public Pager<Company> queryByPage(int pageNo, int pageSize, String checked) {
		Pager<Company> pager = new Pager<Company>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company where id not in(select top " + top1 + "id from t_company) and checked = ? order by created_time desc");
			ps.setString(1, checked);
			ResultSet rs = ps.executeQuery();
			List<Company> companys = new ArrayList<Company>();
			while (rs.next()) {
				Company company = new Company();
				SetDAOUtil.setCompany(company, rs);
				companys.add(company);
			}
			pager.setResult(companys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public List<Company> conditionQueryNotAuditCompany(String checked, String email, String name, String principal) {
		List<Company> companys = new ArrayList<Company>();
		getConn();
		PreparedStatement ps = null;
		try {
			if (email != null && !email.equals("") && name != null && !name.equals("") && principal != null && !principal.equals("")) {
				ps = conn.prepareStatement("select * from t_company where checked = ? and email like(?) and name like(?) and principal like(?)");
				ps.setString(1, checked);
				ps.setString(2, "%" + email + "%");
				ps.setString(3, "%" + name + "%");
				ps.setString(4, "%" + principal + "%");
			} else {
				if (email != null && !email.equals("") && name != null && !name.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and email like(?) and name like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + email + "%");
					ps.setString(3, "%" + name + "%");
				} else if (email != null && !email.equals("") && principal != null && !principal.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and email like(?) and principal like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + email + "%");
					ps.setString(3, "%" + principal + "%");
				} else if (name != null && !name.equals("") && principal != null && !principal.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and and name like(?) and principal like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + name + "%");
					ps.setString(3, "%" + principal + "%");
				} else if (email != null && !email.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and email like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + email + "%");
				} else if (name != null && !name.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and name like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + name + "%");
				} else if (principal != null && !principal.equals("")) {
					ps = conn.prepareStatement("select * from t_company where checked = ? and principal like(?)");
					ps.setString(1, checked);
					ps.setString(2, "%" + principal + "%");
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				SetDAOUtil.setCompany(company, rs);
				companys.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companys;
	}

	@Override
	public void updateCompany(Company company) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_company set name = ?, password = ?, principal = ?, logo = ?, phone = ?, tel = ?, address = ?, longitude = ?, latitude = ?, des = ?, checked = ?, checked_time = ?, status = ? where email = ?");
			ps.setString(1, company.getName());
			ps.setString(2, company.getPassword());
			ps.setString(3, company.getPrincipal());
			ps.setString(4, company.getLogo());
			ps.setString(5, company.getPhone());
			ps.setString(6, company.getTel());
			ps.setString(7, company.getAddress());
			ps.setFloat(8, company.getLongitude());
			ps.setFloat(9, company.getLatitude());
			ps.setString(10, company.getDes());
			ps.setString(11, company.getChecked());
			ps.setDate(12, new Date(company.getChecked_time().getTime()));
			ps.setString(13, company.getStatus());
			ps.setString(14, company.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
	}

	@Override
	public Company queryCompanyByEmail(String email) {
		Company company = new Company();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				company = SetDAOUtil.setCompany(company, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return company;
	}
	
	@Override
	public List<Company> queryCompanyByTop4() {
		List<Company> companys = new ArrayList<Company>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_company order by created_time desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company = SetDAOUtil.setCompany(company, rs);
				companys.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companys;
	}

	@Override
	public Company queryCompanyById(String id) {
		Company company = new Company();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				company = SetDAOUtil.setCompany(company, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return company;
	}

	@Override
	public List<CompanyCol> queryCompanyColByProductId(String customer_id) {
		List<CompanyCol> companyCols = new ArrayList<CompanyCol>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company_col where customer_id = ?");
			ps.setString(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyCol companyCol = new CompanyCol();
				companyCol = SetDAOUtil.setCompanyCol(companyCol, rs, conn);
				companyCols.add(companyCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCols;
	}

	


}
