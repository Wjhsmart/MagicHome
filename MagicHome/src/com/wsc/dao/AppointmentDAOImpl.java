package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Appointment;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class AppointmentDAOImpl extends BaseDAO implements AppointmentDAO {

	@Override
	public void addAppointment(Appointment appointment) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_appointment(company_id, name, plot_name, phone, area, budget, way, created_time) values(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, appointment.getCompany_id());
			ps.setString(2, appointment.getName());
			ps.setString(3, appointment.getPlot_name());
			ps.setString(4, appointment.getPhone());
			ps.setFloat(5, appointment.getArea());
			ps.setString(6, appointment.getBudget());
			ps.setString(7, appointment.getWay());
			ps.setDate(8, new Date(appointment.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Appointment queryAppointmentByNameAndPhone(String name, String phone) {
		getConn();
		Appointment appointment = new Appointment();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_appointment where name = ? and phone = ?");
			ps.setString(1, name);
			ps.setString(2, phone);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				appointment = SetDAOUtil.setAppointment(appointment, rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return appointment;
	}

	@Override
	public Pager<Appointment> queryByPage(int pageNo, int pageSize) {
		Pager<Appointment> pager = new Pager<Appointment>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_appointment where id not in(select top " + top1 +" id from t_appointment where id not in(select app_id from t_appointment_view)) and id not in(select app_id from t_appointment_view) order by created_time desc");
			ResultSet rs = ps.executeQuery();
			List<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment = SetDAOUtil.setAppointment(appointment, rs);
				appointments.add(appointment);
			}
			pager.setResult(appointments);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public Appointment queryAppointmentById(String id) {
		Appointment appointment = new Appointment();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_appointment where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				appointment = SetDAOUtil.setAppointment(appointment, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return appointment;
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_appointment where company_id = ?");
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
	public Pager<Appointment> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		Pager<Appointment> pager = new Pager<Appointment>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_appointment where id not in(select top " + top1 +" id from t_appointment where id not in(select app_id from t_appointment_view)) and id not in(select app_id from t_appointment_view) and company_id = ? order by created_time desc");
			ps.setString(1, company_id);
			ResultSet rs = ps.executeQuery();
			List<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment = SetDAOUtil.setAppointment(appointment, rs);
				appointments.add(appointment);
			}
			pager.setResult(appointments);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

}
