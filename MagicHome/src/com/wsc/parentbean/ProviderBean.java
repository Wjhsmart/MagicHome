package com.wsc.parentbean;

import java.util.Date;

/**
 * 服务类，所有服务者的父类，继承至BaseBean
 * @author Administrator
 *
 */
public class ProviderBean extends UserBean {

	private String principal; // 负责人
	private String logo; // 公司logo图片路径
	private String address; // 地址
	private String tel; // 固定电话
	private Date open_date; // 成立日期
	private float longitude; // 经度
	private float latitude; // 纬度
	private String des; // 描述
	private String checked; // 是否审核  Y审核通过， N未审核
	private Date checked_time; // 审核时间
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getOpen_date() {
		return open_date;
	}
	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
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
		return "ProviderBean [principal=" + principal + ", logo=" + logo + ", address=" + address + ", tel=" + tel
				+ ", open_date=" + open_date + ", longitude=" + longitude + ", latitude=" + latitude + ", des=" + des
				+ ", checked=" + checked + ", checked_time=" + checked_time + "]";
	}
	
}
