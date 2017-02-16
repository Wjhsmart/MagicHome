package com.wsc.service;

import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerColService {

	/**
	 * ��ҳ��ѯָ��Id���ղ�װ�޹�˾
	 * @return
	 */
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId);
	
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
	 * ����DesignerCol�����������
	 * @param DesignerCol
	 */
	public void addDesignerCol(DesignerCol designerCol);
}
