package com.wsc.parentbean;

/**
 * �ղ��࣬�������ղ���ĸ��࣬�̳���BaseBean
 * @author Administrator
 *
 */
public class CollectBean extends BaseBean {
	
	private String customer_id; // �û����

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
