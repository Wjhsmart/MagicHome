package com.wsc.parentbean;

import java.util.Date;

/**
 * 
 * ���࣬����javaBean�ĸ��࣬���������б��е��ֶ�
 * 
 * @author Administrator
 *
 */
public class BaseBean {

	private String id; // id ���
	private Date created_time; // ����ʱ��

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	@Override
	public String toString() {
		return "BaseBean [id=" + id + ", created_time=" + created_time + "]";
	}

}
