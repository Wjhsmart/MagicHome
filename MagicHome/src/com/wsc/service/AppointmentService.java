package com.wsc.service;

import com.wsc.bean.Appointment;
import com.wsc.parentbean.Pager;

public interface AppointmentService {

	/**
	 * ����Appointmet���������ݿ�����û�ԤԼ������
	 * @param appointment
	 */
	public void addAppointment(Appointment appointment);
	
	/**
	 * �����û������ֻ���ȥ��ѯԤԼ��
	 * @param name
	 * @param phone
	 * @return
	 */
	public Appointment queryAppointmentByNameAndPhone(String name, String phone);
	
	/**
	 * ��ҳ��ѯ����ԤԼ��Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Appointment> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ���ݴ��ݽ�����id��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName, String customerId);
	
	/**
	 * ���ݱ�����ѯ�����ж���������
	 * @param tableName
	 * @return
	 */
	public int queryCount(String tableName);
	
	/**
	 * ����idɾ��ָ�����е�����
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * ����id��ѯԤԼ��Ϣ
	 * @param id
	 * @return
	 */
	public Appointment queryAppointmentById(String id);
	
	/**
	 * ���ݹ�˾id��ѯװ�޹�˾��ԤԼ����
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
	
	/**
	 * ����װ�޹�˾id��ҳ��ѯ����ԤԼ��Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<Appointment> queryByCompanyId(int pageNo, int pageSize, String company_id);
}
