package com.wsc.service;

import com.wsc.bean.SupplyActivity;
import com.wsc.parentbean.Pager;

public interface SupplyActivityService {

	/**
	 * ��ҳ��ѯ���н����̻��Ϣ
	 * @return
	 */
	public Pager<SupplyActivity> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ���ݽ�����id��ҳ��ѯ���Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager<SupplyActivity> queryBySupplyId(int pageNo, int pageSize, String supply_id);
}
