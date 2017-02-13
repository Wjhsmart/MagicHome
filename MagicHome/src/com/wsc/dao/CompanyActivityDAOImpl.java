package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.CompanyActivity;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CompanyActivityDAOImpl extends BaseDAO implements CompanyActivityDAO {

	private CompanyActivity setCompanyActivity(CompanyActivity companyActivity, ResultSet rs, Connection conn) throws SQLException {
		companyActivity.setId(rs.getString("id"));
		companyActivity.setCompany_id(rs.getString("company_id"));
		companyActivity.setName(rs.getString("name"));
		companyActivity.setImage(rs.getString("image"));
		companyActivity.setDes(rs.getString("des"));
		companyActivity.setCreated_time(rs.getDate("created_time"));
		companyActivity.setCompany(SetDAOUtil.queryCompanyById(conn, rs.getString("company_id")));
		return companyActivity;
	}
	
	@Override
	public Pager<CompanyActivity> queryByPage(int pageNo, int pageSize) {
		Pager<CompanyActivity> pager = new Pager<CompanyActivity>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_activity where id not in(select top " + top1 + " id from t_company_activity) order by company_id desc");
			ResultSet rs = ps.executeQuery();
			List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
			while (rs.next()) {
				CompanyActivity companyActivity = new CompanyActivity();
				setCompanyActivity(companyActivity, rs, conn);
				companyActivitys.add(companyActivity);
			}
			pager.setResult(companyActivitys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}
	
	@Override
	public void addActivity(CompanyActivity activity) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_company_activity(company_id, name, image, des, created_time) values(?, ?, ?, ?, ?)");
			ps.setString(1, activity.getCompany_id());
			ps.setString(2, activity.getName());
			ps.setString(3, activity.getImage());
			ps.setString(4, activity.getDes());
			ps.setDate(5, new Date(activity.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
	}

	@Override
	public CompanyActivity queryActById(String id) {
		getConn();
		CompanyActivity activity = new CompanyActivity();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company_activity where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setCompanyActivity(activity, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return activity;
	}

	@Override
	public void updateAct(CompanyActivity activity) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_company_activity set company_id = ?, name = ?, image = ?, des = ?, created_time = ? where id = ?");
			ps.setString(1, activity.getCompany_id());
			ps.setString(2, activity.getName());
			ps.setString(3, activity.getImage());
			ps.setString(4, activity.getDes());
			ps.setDate(5, new Date(activity.getCreated_time().getTime()));
			ps.setString(6, activity.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public int queryCountCompanyId(String company_id) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_company_activity where company_id = ?");
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
	public Pager<CompanyActivity> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		Pager<CompanyActivity> pager = new Pager<CompanyActivity>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_company_activity where id not in(select top " + top1 + " id from t_company_activity) and company_id = ? order by created_time desc");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
			while (rs.next()) {
				CompanyActivity companyActivity = new CompanyActivity();
				setCompanyActivity(companyActivity, rs, conn);
				companyActivitys.add(companyActivity);
			}
			pager.setResult(companyActivitys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public List<CompanyActivity> queryByTop4(String company_id) {
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_company_activity where company_id = ?");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity = setCompanyActivity(companyActivity, rs, conn);
				companyActivitys.add(companyActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyActivitys;
	}

	@Override
	public List<CompanyActivity> queryCompanyActivityByCondition(String company_name, String activity_name) {
		List<CompanyActivity> companyActivitys = new ArrayList<CompanyActivity>();
		getConn();
		PreparedStatement ps = null;
		try {
			if (company_name != null && !company_name.equals("") && activity_name != null && !activity_name.equals("")) {
				ps = conn.prepareStatement("select * from t_company_activity where company_id in(select id from t_company where name like(?)) and name like(?) order by company_id desc");
				ps.setString(1, "%" + company_name + "%");
				ps.setString(2, "%" + activity_name + "%");
			} else {
				if (company_name != null && !company_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_activity where company_id in(select id from t_company where name like(?)) order by company_id desc");
					ps.setString(1, "%" + company_name + "%");
				} else if (activity_name != null && !activity_name.equals("")) {
					ps = conn.prepareStatement("select * from t_company_activity where name like(?) order by company_id desc");
					ps.setString(1, "%" + activity_name + "%");
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyActivity companyActivity = new CompanyActivity();
				companyActivity = setCompanyActivity(companyActivity, rs, conn);
				companyActivitys.add(companyActivity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return companyActivitys;
	}

}
