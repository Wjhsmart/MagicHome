package com.wsc.service;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseColService {

	/**
	 * ��ҳ��ѯָ��Id���ղ����ʦ
	 * @return
	 */
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ���ݴ��ݽ�����id��ѯ���ݽ����ı��ж���������
	 * @return
	 */
	public int queryCount(String tableName, String customerId);
	
	/**
	 * ����idɾ��ָ�����е�����
	 * @param id
	 * @param tableName
	 */
	public void deleteDataId(String id, String tableName);
	
	/**
	 * ����DesignerCaseCol�����������
	 * @param DesignerCaseCol
	 */
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol);
}
