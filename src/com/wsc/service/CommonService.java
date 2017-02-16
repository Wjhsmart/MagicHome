package com.wsc.service;

import java.sql.Date;

public interface CommonService {

	/**
	 * ���ݴ��ݽ����������ѯָ�����ݱ����Ƿ���ڸ����ݣ����ڷ���true�������ڷ���false
	 * @param email
	 * @param tableName
	 * @return
	 */
	public boolean queryEmail(String email, String tableName);
	
	/**
	 * ��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName);
	
	/**
	 * ��������ɾ��ָ�����е�����
	 * @param email
	 */
	public void deleteData(String email, String tableName);
	
	/**
	 * ����״̬����ѯ���ݿ⣬��ѯ����˺�Ϊ��˵�ָ�������ĸ���
	 * @param status
	 * @return
	 */
	public int queryCountByChecked(String checked, String tableName);
	
	/**
	 * ���ݴ��ݽ����������ֶ��������������û�id���ղص�id���ղص�ʱ��
	 * @param tableName
	 * @param field
	 * @param customer_id
	 * @param col_id
	 * @param created_time
	 */
	public void colData(String tableName, String field, String customer_id, String col_id, Date created_time);
	
	/**
	 * ����id��ѯָ��������ָ���ֶεĸ���
	 * @param supply_id
	 * @return
	 */
	public int queryCountByTableId(String id, String field, String tableName);
	
}
