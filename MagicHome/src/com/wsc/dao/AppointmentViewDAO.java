package com.wsc.dao;

import com.wsc.bean.AppointmentView;
import com.wsc.parentbean.Pager;

public interface AppointmentViewDAO {

	/**
	 * 根据预约表id和装修公司id查询预约记录表，如果有数据，则返回true， 否则返回false
	 * @param app_id
	 * @param company_id
	 * @return
	 */
	public boolean queryAppointmentViewByAppIdAndCompanyId(String app_id, String company_id);
	
	/**
	 * 根据预约表的id查询出有多少个装修公司查看该预约
	 * @param app_id
	 * @return
	 */
	public int queryCountByAppId(String app_id);
	
	/**
	 * 向预约查看表中插入数据
	 * @param appointmentView
	 */
	public void addAppointmentView(AppointmentView appointmentView);
	
	/**
	 * 根据装修公司id分页查询所有已经接受的预约
	 * @param company_id
	 * @return
	 */
	public Pager<AppointmentView> queryByPage(int pageNo, int pageSize, String company_id);
	
	/**
	 * 根据装修公司id查询记录表中有一共有多少条记录
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
}
