package com.wsc.dao;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseColDAO {
	
	/**
	 * ��ҳ��ѯָ��Id��װ�޹�˾�ղ�
	 * @return
	 */
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ����DesignerCaseCol�����������
	 * @param DesignerCaseCol
	 */
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol);
}
