package com.wsc.bean;

import com.wsc.parentbean.ProviderBean;

/**
 * װ�޹�˾���̳���������
 * @author Administrator
 *
 */
public class Company extends ProviderBean {

	private boolean collected; // �Ƿ��ղ�

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
}
