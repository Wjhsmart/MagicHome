package com.wsc.dao;

import com.wsc.bean.SupplyActivity;
import com.wsc.parentbean.Pager;

public interface SupplyActivityDAO {

	/**
	 * 分页查询所有装修公司案例
	 * @return
	 */
	public Pager<SupplyActivity> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 根据建材商id分页查询活动信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<SupplyActivity> queryBySupplyId(int pageNo, int pageSize, String supply_id);
}
