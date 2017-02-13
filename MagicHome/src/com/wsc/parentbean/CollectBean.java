package com.wsc.parentbean;

/**
 * 收藏类，是所有收藏类的父类，继承至BaseBean
 * @author Administrator
 *
 */
public class CollectBean extends BaseBean {
	
	private String customer_id; // 用户编号

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "CollectBean [customer_id=" + customer_id + "]";
	}

}
