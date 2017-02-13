package com.wsc.bean;

import com.wsc.parentbean.ProviderBean;

/**
 * 装修公司表，继承至服务类
 * @author Administrator
 *
 */
public class Company extends ProviderBean {

	private boolean collected; // 是否被收藏

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
}
