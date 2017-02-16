package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 建材商收藏表，继承至收藏类
 * @author Administrator
 *
 */
public class SupplyCol extends CollectBean {

	private String supply_id; //建材商编号
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
		return "SupplyCol [supply_id=" + supply_id + "]";
	}
	
	
}
