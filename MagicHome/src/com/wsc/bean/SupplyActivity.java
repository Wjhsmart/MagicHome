package com.wsc.bean;

import com.wsc.parentbean.ActivityBean;

/**
 * 建材商活动表， 继承至活动类
 * @author Administrator
 *
 */
public class SupplyActivity extends ActivityBean {

	private String supply_id; // 建材商编号
	private Supply supply; // 建材商

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public String getSupply_id() {
		return supply_id;
	}

	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}

	@Override
	public String toString() {
		return "SupplyActivity [supply_id=" + supply_id + "]";
	}
	
}
