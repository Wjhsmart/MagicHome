package com.wsc.dao;

import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerColDAO {

	/**
	 * ��ҳ��ѯָ��Id��װ�޹�˾�ղ�
	 * @return
	 */
	public Pager<DesignerCol> queryByPage(int pageNo, int pageSize, String customerId);
	
	/**
	 * ����DesignerCol�����������
	 * @param DesignerCol
	 */
	public void addDesignerCol(DesignerCol designerCol);
}
