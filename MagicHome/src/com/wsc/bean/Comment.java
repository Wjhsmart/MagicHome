package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

public class Comment extends BaseBean {

	private String customer_id; // 用户编号
	private String content; // 评论内容
	private Customer customer; // 用户

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [customer_id=" + customer_id + ", content=" + content + "]";
	}

}
