package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.AppointmentView;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class AppointmentViewDAOImpl extends BaseDAO implements AppointmentViewDAO {

	private AppointmentView setAppointmentView(AppointmentView appointmentView, ResultSet rs, Connection conn) throws SQLException {
		appointmentView.setId(rs.getString("id"));
		appointmentView.setApp_id(rs.getString("app_id"));
		appointmentView.setCompany_id(rs.getString("company_id"));
		appointmentView.setCreated_time(rs.getDate("created_time"));
		appointmentView.setAppointment(SetDAOUtil.queryAppointmentById(conn, rs.getString("app_id")));
		return appointmentView;
	}
	
	@Override
	public boolean queryAppointmentViewByAppIdAndCompanyId(String app_id, String company_id) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_appointment_view where app_id = ? and company_id = ?");
			ps.setString(1, app_id);
			ps.setString(2, company_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return false;
	}

	@Override
	public int queryCountByAppId(String app_id) {
		int count = 0;
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_appointment_view where app_id = ?");
			ps.setString(1, app_id);
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
	public void addAppointmentView(AppointmentView appointmentView) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_appointment_view(app_id, company_id, created_time) values(?, ?, ?)");
			ps.setString(1, appointmentView.getApp_id());
			ps.setString(2, appointmentView.getCompany_id());
			ps.setDate(3, new Date(appointmentView.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Pager<AppointmentView> queryByPage(int pageNo, int pageSize, String company_id) {
		Pager<AppointmentView> pager = new Pager<AppointmentView>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_appointment_view where id not in(select top " + top1 + " id from t_appointment_view) and company_id = ? order by created_time desc");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			List<AppointmentView> appointmentViews = new ArrayList<AppointmentView>();
			while (rs.next()) {
				AppointmentView appointmentView = new AppointmentView();
				appointmentView = setAppointmentView(appointmentView, rs, conn);
				appointmentViews.add(appointmentView);
			}
			pager.setResult(appointmentViews);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		int count = 0;
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_appointment_view where company_id = ?");
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

}
