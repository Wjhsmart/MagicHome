package com.wsc.dao;

import com.wsc.bean.AppointmentView;
import com.wsc.parentbean.Pager;

public interface AppointmentViewDAO {

	/**
	 * ����ԤԼ��id��װ�޹�˾id��ѯԤԼ��¼����������ݣ��򷵻�true�� ���򷵻�false
	 * @param app_id
	 * @param company_id
	 * @return
	 */
	public boolean queryAppointmentViewByAppIdAndCompanyId(String app_id, String company_id);
	
	/**
	 * ����ԤԼ���id��ѯ���ж��ٸ�װ�޹�˾�鿴��ԤԼ
	 * @param app_id
	 * @return
	 */
	public int queryCountByAppId(String app_id);
	
	/**
	 * ��ԤԼ�鿴���в�������
	 * @param appointmentView
	 */
	public void addAppointmentView(AppointmentView appointmentView);
	
	/**
	 * ����װ�޹�˾id��ҳ��ѯ�����Ѿ����ܵ�ԤԼ
	 * @param company_id
	 * @return
	 */
	public Pager<AppointmentView> queryByPage(int pageNo, int pageSize, String company_id);
	
	/**
	 * ����װ�޹�˾id��ѯ��¼������һ���ж�������¼
	 * @param company_id
	 * @return
	 */
	public int queryCountByCompanyId(String company_id);
}
