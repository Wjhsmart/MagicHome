package com.wsc.bean;

import java.util.Date;

import com.wsc.parentbean.UserBean;

public class Designer extends UserBean {

	private String address; // 地址
	private String des; // 描述
	private String head_icon; // 头像地址
	private String experience; // 工作经验
	private String style; // 擅长风格
	private String checked; // 是否审核 Y 审核通过 ， N 未审核
	private Date checked_time; // 审核时间
	private boolean collected; // 是否被收藏

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
