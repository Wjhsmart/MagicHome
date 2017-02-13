package com.wsc.service;

import com.wsc.bean.AppointmentView;
import com.wsc.dao.AppointmentViewDAO;
import com.wsc.dao.AppointmentViewDAOImpl;
import com.wsc.parentbean.Pager;

public class AppointmentViewServiceImpl implements AppointmentViewService {

	private AppointmentViewDAO appointmentViewDAO;
	
	public AppointmentViewServiceImpl() {
		appointmentViewDAO = new AppointmentViewDAOImpl();
	}
	
	@Override
	public boolean queryAppointmentViewByAppIdAndCompanyId(String app_id, String company_id) {
		return appointmentViewDAO.queryAppointmentViewByAppIdAndCompanyId(app_id, company_id);
	}

	@Override
	public int queryCountByAppId(String app_id) {
		return appointmentViewDAO.queryCountByAppId(app_id);
	}

	@Override
	public void addAppointmentView(AppointmentView appointmentView) {
		appointmentViewDAO.addAppointmentView(appointmentView);
	}

	@Override
	public Pager<AppointmentView> queryByPage(int pageNo, int pageSize, String company_id) {
		return appointmentViewDAO.queryByPage(pageNo, pageSize, company_id);
	}

	@Override
	public int queryCountByCompanyId(String company_id) {
		return appointmentViewDAO.queryCountByCompanyId(company_id);
	}

}
