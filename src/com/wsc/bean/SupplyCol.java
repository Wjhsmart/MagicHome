package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * �������ղر��̳����ղ���
 * @author Administrator
 *
 */
public class SupplyCol extends CollectBean {

	private String supply_id; //�����̱��
	private Supply supply; // ������

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
