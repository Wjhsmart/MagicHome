package com.wsc.dao;

import com.wsc.bean.Appointment;
import com.wsc.parentbean.Pager;

public interface AppointmentDAO {

	/**
	 * 根据Appointment对象向数据库添加数据
	 * @param appointment
	 */
	public void addAppointment(Appointment appointment);
	
	/**
	 * 根据用户名和手机号去查询预约表
	 * @param name
	 * @param phone
	 * @return
	 */
	public Appointment queryAppointmentByNameAndPhone(String name, String phone);
	
	/**
	 * 分页查询所有预约信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Appointment> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 根据id查询预约信息
	 * @param id
	 * @return
	 */
	public Appointment queryAppointmentById(String id);
	
	/**
	 * 根据公司id查询装修公司的预约个数
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
	
	/**
	 * 根据装修公司id分页查询所有预约信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Appointment> queryByCompanyId(int pageNo, int pageSize, String company_id);
	
}
