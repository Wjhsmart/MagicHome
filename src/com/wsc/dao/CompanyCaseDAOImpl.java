package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CompanyCaseDAOImpl extends BaseDAO implements CompanyCaseDAO {

	@Override
	public Pager<CompanyCase> queryByPage(int pageNo, int pageSize) {
		Pager<CompanyCase> pager = new Pager<CompanyCase>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_case where id not in(select top " + top1 + "id from t_company_case) order by company_id desc");
			ResultSet rs = ps.executeQuery();
			List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
			while (rs.next()) {
				CompanyCase companyCase = new CompanyCase();
				SetDAOUtil.setCompanyCase(companyCase, rs, conn);
				companyCases.add(companyCase);
			}
			pager.setResult(companyCases);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}
	
	@Override
	public Pager<CompanyCase> queryByPageAndCompanyId(int pageNo, int pageSize, String company_id) {
		Pager<CompanyCase> pager = new Pager<CompanyCase>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_case where id not in(select top " + top1 + "id from t_company_case) and company_id = ?");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
			while (rs.next()) {
				CompanyCase companyCase = new CompanyCase();
				SetDAOUtil.setCompanyCase(companyCase, rs, conn);
				companyCases.add(companyCase);
			}
			pager.setResult(companyCases);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public CompanyCase queryCompanyCase() {
		CompanyCase companyCase = new CompanyCase();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 1 * from t_company_case");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				companyCase = SetDAOUtil.setCompanyCase(companyCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCase;
	}

	@Override
	public List<CompanyCase> queryCompanyCaseByTop4(String company_id) {
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_company_case where company_id = ?");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyCase companyCase = new CompanyCase();
				companyCase = SetDAOUtil.setCompanyCase(companyCase, rs, conn);
				companyCases.add(companyCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCases;
	}

	@Override
	public CompanyCase queryCompanyCaseById(String id) {
		CompanyCase companyCase = new CompanyCase();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company_case where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				companyCase = SetDAOUtil.setCompanyCase(companyCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCase;
	}
	
	@Override
	public void updateCase(CompanyCase companyCase) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_company_case set company_id = ?, name = ?, plot_name = ?, style = ?, image_1 = ?, image_2 = ?, image_3 = ?, image_4 = ?, image_5 = ?, des = ?, created_time = ? where id = ?");
			ps.setString(1, companyCase.getCompany_id());
			ps.setString(2, companyCase.getName());
			ps.setString(3, companyCase.getPlot_name());
			ps.setString(4, companyCase.getStyle());
			ps.setString(5, companyCase.getImage1());
			ps.setString(6, companyCase.getImage2());
			ps.setString(7, companyCase.getImage3());
			ps.setString(8, companyCase.getImage4());
			ps.setString(9, companyCase.getImage5());
			ps.setString(10, companyCase.getDes());
			ps.setDate(11, new Date(companyCase.getCreated_time().getTime()));
			ps.setString(12, companyCase.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	
	@Override
	public void addCompanyCase(CompanyCase companyCase) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, companyCase.getCompany_id());
			ps.setString(2, companyCase.getName());
			ps.setString(3, companyCase.getPlot_name());
			ps.setString(4, companyCase.getStyle());
			ps.setString(5, companyCase.getImage1());
			ps.setString(6, companyCase.getImage2());
			ps.setString(7, companyCase.getImage3());
			ps.setString(8, companyCase.getImage4());
			ps.setString(9, companyCase.getImage5());
			ps.setString(10, companyCase.getDes());
			ps.setDate(11, new Date(companyCase.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Pager<CompanyCase> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		Pager<CompanyCase> pager = new Pager<CompanyCase>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_case where id not in(select top " + top1 + "id from t_company_case) and company_id = ?");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
			while (rs.next()) {
				CompanyCase companyCase = new CompanyCase();
				SetDAOUtil.setCompanyCase(companyCase, rs, conn);
				companyCases.add(companyCase);
			}
			pager.setResult(companyCases);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_company_case where company_id = ?");
			ps.setString(1, company_id);
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

	@Override
	public List<CompanyCaseCol> queryCompanyCaseColByProductId(String customer_id) {
		List<CompanyCaseCol> companyCaseCols = new ArrayList<CompanyCaseCol>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company_case_col where customer_id = ?");
			ps.setString(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyCaseCol companyCaseCol = new CompanyCaseCol();
				companyCaseCol = SetDAOUtil.setCompanyCaseCol(companyCaseCol, rs, conn);
				companyCaseCols.add(companyCaseCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCaseCols;
	}

	@Override
	public List<CompanyCase> queryCompanyCaseByCondition(String company_name, String case_name, String plot_name) {
		List<CompanyCase> companyCases = new ArrayList<CompanyCase>();
		getConn();
		PreparedStatement ps = null;
		try {
			if (company_name != null && !company_name.equals("") && case_name != null && !case_name.equals("") && plot_name != null && !plot_name.equals("")) {
				ps = conn.prepareStatement("select * from t_company_case where company_id in(select id from t_company where name like(?)) and name like(?) and plot_name like(?) order by company_id desc");
				ps.setString(1, "%" + company_name + "%");
				ps.setString(2, "%" + case_name + "%");
				ps.setString(3, "%" + plot_name + "%");
			} else {
				if (company_name != null && !company_name.equals("") && case_name != null && !case_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where company_id in(select id from t_company where name like(?)) and name like(?) order by company_id desc");
					ps.setString(1, "%" + company_name + "%");
					ps.setString(2, "%" + case_name + "%");
				} else if (company_name != null && !company_name.equals("") && plot_name != null && !plot_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where company_id in(select id from t_company where name like(?)) and plot_name like(?) order by company_id desc");
					ps.setString(1, "%" + company_name + "%");
					ps.setString(2, "%" + plot_name + "%");
				} else if (case_name != null && !case_name.equals("") && plot_name != null && !plot_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where name like(?) and plot_name like(?)");
					ps.setString(1, "%" + case_name + "%");
					ps.setString(2, "%" + plot_name + "%");
				} else if (company_name != null && !company_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where company_id in(select id from t_company where name like(?)) order by company_id desc");
					ps.setString(1, "%" + company_name + "%");
				} else if (case_name != null && !case_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where name like(?) order by company_id desc");
					ps.setString(1, "%" + case_name + "%");
				} else if (plot_name != null && !plot_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_case where plot_name like(?) order by company_id desc");
					ps.setString(1, "%" + plot_name + "%");
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyCase companyCase = new CompanyCase();
				companyCase = SetDAOUtil.setCompanyCase(companyCase, rs, conn);
				companyCases.add(companyCase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyCases;
	}

}
