package com.wsc.dao;

import com.wsc.bean.SupplyCol;
import com.wsc.parentbean.Pager;

public interface SupplyColDAO {

	/**
	 * ��ҳ��ѯָ��Id�Ľ������ղ�
	 * @return
	 */
	public Pager<SupplyCol> queryByPage(int pageNo, int pageSize, String customerId);
}
