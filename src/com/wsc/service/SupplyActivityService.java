package com.wsc.service;

import com.wsc.bean.SupplyActivity;
import com.wsc.parentbean.Pager;

public interface SupplyActivityService {

	/**
	 * 分页查询所有建材商活动信息
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
