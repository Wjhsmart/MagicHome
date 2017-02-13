package com.wsc.bean;

import java.util.Date;

import com.wsc.parentbean.UserBean;

public class Designer extends UserBean {

	private String address; // ��ַ
	private String des; // ����
	private String head_icon; // ͷ���ַ
	private String experience; // ��������
	private String style; // �ó����
	private String checked; // �Ƿ���� Y ���ͨ�� �� N δ���
	private Date checked_time; // ���ʱ��
	private boolean collected; // �Ƿ��ղ�

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public String getHead_icon() {
		return head_icon;
	}

	public void setHead_icon(String head_icon) {
		this.head_icon = head_icon;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Date getChecked_time() {
		return checked_time;
	}

	public void setChecked_time(Date checked_time) {
		this.checked_time = checked_time;
	}

	@Override
	public String toString() {
		return "Designer [address=" + address + ", des=" + des + ", head_icon=" + head_icon + ", experience="
				+ experience + ", style=" + style + ", checked=" + checked + ", checked_time=" + checked_time + "]";
	}

}
