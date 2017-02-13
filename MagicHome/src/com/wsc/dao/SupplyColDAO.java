package com.wsc.dao;

import com.wsc.bean.SupplyCol;
import com.wsc.parentbean.Pager;

public interface SupplyColDAO {

	/**
	 * 分页查询指定Id的建材商收藏
	 * @return
	 */
	public Pager<SupplyCol> queryByPage(int pageNo, int pageSize, String customerId);
}
