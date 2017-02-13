package com.wsc.service;

import com.wsc.bean.Appointment;
import com.wsc.dao.AppointmentDAO;
import com.wsc.dao.AppointmentDAOImpl;
import com.wsc.dao.CommonDAO;
import com.wsc.dao.CommonDAOImpl;
import com.wsc.parentbean.Pager;

public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentDAO appointmentDAO;
	private CommonDAO commonDAO; 
	
	public AppointmentServiceImpl() {
		appointmentDAO = new AppointmentDAOImpl();
		commonDAO = new CommonDAOImpl();
	}
	
	@Override
	public void addAppointment(Appointment appointment) {
		appointmentDAO.addAppointment(appointment);
	}

	@Override
	public Appointment queryAppointmentByNameAndPhone(String name, String phone) {
		return appointmentDAO.queryAppointmentByNameAndPhone(name, phone);
	}

	@Override
	public Pager<Appointment> queryByPage(int pageNo, int pageSize) {
		return appointmentDAO.queryByPage(pageNo, pageSize);
	}

	@Override
	public int queryCount(String tableName, String customerId) {
		return commonDAO.queryCount(tableName, customerId);
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		commonDAO.deleteDataId(id, tableName);
	}

	@Override
	public int queryCount(String tableName) {
		return commonDAO.queryCount(tableName);
	}

	@Override
	public Appointment queryAppointmentById(String id) {
		return appointmentDAO.queryAppointmentById(id);
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		return appointmentDAO.queryCountByCompanyId(company_id);
	}

	@Override
	public Pager<Appointment> queryByCompanyId(int pageNo, int pageSize, String company_id) {
		return appointmentDAO.queryByCompanyId(pageNo, pageSize, company_id);
	}

}
